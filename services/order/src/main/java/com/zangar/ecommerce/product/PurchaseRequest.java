package com.zangar.ecommerce.product;

import jakarta.validation.constraints.*;

public record PurchaseRequest(
        @NotNull(message = "Product is mandatory")
        Integer productId,

         @Positive(message = "Product quantity should be positive")
         double quantity
) {
}
