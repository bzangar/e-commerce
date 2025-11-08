package com.zangar.ecommerce.order;

import com.zangar.ecommerce.order.customer.CustomerClient;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerClient customerClient;

    public Integer createOrder(@Valid OrderRequest request) {
        // check the customer --> OpenFeign

        // purchase the products --> product-ms

        // persist order

        // persist order line

        // start payment process

        // send the order confirmation --> notification-ms (kafka)

        return null;
    }
}
