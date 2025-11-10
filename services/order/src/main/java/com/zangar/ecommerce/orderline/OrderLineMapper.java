package com.zangar.ecommerce.orderline;

import com.zangar.ecommerce.order.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {
    public OrderLine toOrderLine(OrderLineRequest request) {
        return OrderLine.builder()
                .id(request.id())
                .quantity(request.quantity())
                .product_id(request.productId())
                .order(
                        Order.builder()
                            .id(request.orderId())
                            .build()
                )
                .build();
    }
}
