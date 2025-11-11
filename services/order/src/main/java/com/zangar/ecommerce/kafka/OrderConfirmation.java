package com.zangar.ecommerce.kafka;

import com.zangar.ecommerce.customer.CustomerResponse;
import com.zangar.ecommerce.order.PaymentMethod;
import com.zangar.ecommerce.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal total_Amount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
