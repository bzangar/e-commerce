package com.zangar.ecommerce.product;

import java.math.BigDecimal;

public record PurchaseResponse(
        Integer id,
        String name,
        String description,
        double quantity,
        BigDecimal price
) {
}
