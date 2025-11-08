package com.zangar.ecommerce.order;

import com.zangar.ecommerce.product.PuschaseRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
        Integer id,

        String reference,

        @Positive(message = "Amount should be positive")
        BigDecimal amount,

        @NotNull(message = "Payment method is required")
        PaymentMethod paymentMethod,

        @NotNull(message = "Customer should be present")
        @NotBlank(message = "Customer should be present")
        @NotEmpty(message = "Customer should be present")
        String customerId,

        @NotEmpty(message="You should purchase at least one product")
        List<PuschaseRequest> products
) {
}
