package com.zangar.ecommerce.payment;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {

    public Payment toPayment(PaymentRequest request) {

        return Payment.builder()
                .amount(request.amount())
                .paymentMethod(request.paymentMethod())
                .order_id(request.orderId())
                .build();
    }
}
