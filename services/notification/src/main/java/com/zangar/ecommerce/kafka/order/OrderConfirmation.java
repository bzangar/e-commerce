package com.zangar.ecommerce.kafka.order;

import com.zangar.ecommerce.kafka.payment.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal total_amount,
        PaymentMethod paymentMethod,
        Customer customer,
        List<Product> products
) {
}
