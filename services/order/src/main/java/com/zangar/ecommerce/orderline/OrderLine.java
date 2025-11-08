package com.zangar.ecommerce.orderline;

import com.zangar.ecommerce.order.Order;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class OrderLine {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name="order_id")
    private Order order;

    private Integer product_id;
    private double quantity;

}
