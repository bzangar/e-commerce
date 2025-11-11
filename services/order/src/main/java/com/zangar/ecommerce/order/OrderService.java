package com.zangar.ecommerce.order;

import com.zangar.ecommerce.customer.CustomerClient;
import com.zangar.ecommerce.exception.BusinessException;
import com.zangar.ecommerce.kafka.OrderConfirmation;
import com.zangar.ecommerce.kafka.OrderProducer;
import com.zangar.ecommerce.orderline.OrderLineRequest;
import com.zangar.ecommerce.orderline.OrderLineService;
import com.zangar.ecommerce.product.ProductClient;
import com.zangar.ecommerce.product.PurchaseRequest;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository repository;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;

    public Integer createOrder(@Valid OrderRequest request) {
        // check the customer --> OpenFeign
        var customer = customerClient.getCustomerById(request.customerId())
                .orElseThrow(()-> new BusinessException("Customer not found by this ID:: " + request.customerId()));

        // purchase the products --> product-ms
        var purchasedProducts = productClient.purchaseProducts(request.products());

        // persist order
        var order = repository.save(mapper.toOrder(request));

        // persist order line
        for(PurchaseRequest purchaseRequest: request.products()){
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }


        // todo start payment process

        // send the order confirmation --> notification-ms (kafka)
        orderProducer.sendOrderConfirmation(new OrderConfirmation(
                request.reference(),
                request.amount(),
                request.paymentMethod(),
                customer,
                purchasedProducts
        ));

        return order.getId();
    }

    public List<OrderResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponse findById(Integer orderId) {
        return repository.findById(orderId)
                .map(mapper::fromOrder)
                .orElseThrow(()-> new EntityNotFoundException(String.format("No order found with the provided ID: %d" + orderId)));
    }
}
