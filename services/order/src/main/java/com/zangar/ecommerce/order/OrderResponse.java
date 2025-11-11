package com.zangar.ecommerce.order;

import java.math.BigDecimal;

public record OrderResponse(
        Integer id,
        String reference,
        PaymentMethod paymentMethod,
        BigDecimal amount,
        String customerId
) {
}
