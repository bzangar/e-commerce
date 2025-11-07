package com.zangar.product;

import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequest(
        @NotNull(message = "Product ID is mandatory")
        Integer productId,

        @NotNull(message = "Product ID is mandatory")
        double quantity
) {
}
