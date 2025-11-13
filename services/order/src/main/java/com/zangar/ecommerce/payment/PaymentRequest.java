package com.zangar.ecommerce.payment;

import com.zangar.ecommerce.customer.CustomerResponse;
import com.zangar.ecommerce.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
