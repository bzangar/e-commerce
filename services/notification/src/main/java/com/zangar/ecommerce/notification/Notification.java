package com.zangar.ecommerce.notification;

import com.zangar.ecommerce.kafka.order.OrderConfirmation;
import com.zangar.ecommerce.kafka.payment.PaymentNotificationRequest;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Document
public class Notification {
    @Id
    @Generated
    private Integer id;

    private NotificationType notificationType;
    private LocalDateTime notificationDate;
    private OrderConfirmation orderConfirmation;
    private PaymentNotificationRequest paymentNotificationRequest;
}
