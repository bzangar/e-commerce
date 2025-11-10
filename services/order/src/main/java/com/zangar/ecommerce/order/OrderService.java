package com.zangar.ecommerce.order;

import com.zangar.ecommerce.customer.CustomerClient;
import com.zangar.ecommerce.exception.BusinessException;
import com.zangar.ecommerce.orderline.OrderLineRequest;
import com.zangar.ecommerce.orderline.OrderLineService;
import com.zangar.ecommerce.product.ProductClient;
import com.zangar.ecommerce.product.PurchaseRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository repository;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;

    public Integer createOrder(@Valid OrderRequest request) {
        // check the customer --> OpenFeign
        var customer = customerClient.getCustomerById(request.customerId())
                .orElseThrow(()-> new BusinessException("Customer not found by this ID:: " + request.customerId()));

        // purchase the products --> product-ms
        productClient.purchaseProducts(request.products());

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

        return null;
    }
}
