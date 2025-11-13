package com.zangar.ecommerce.kafka;

import com.zangar.ecommerce.email.EmailService;
import com.zangar.ecommerce.kafka.order.OrderConfirmation;
import com.zangar.ecommerce.kafka.payment.PaymentConfirmation;
import com.zangar.ecommerce.notification.Notification;
import com.zangar.ecommerce.notification.NotificationRepository;
import com.zangar.ecommerce.notification.NotificationType;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationRepository notificationRepository;
    private final EmailService emailService;

    @KafkaListener(topics = "payment-topic")
    public void sendPaymentSuccesNotification(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info("Consuming payment confirmation from payment-topic");
        notificationRepository.save(
                Notification.builder()
                        .notificationType(NotificationType.PAYMENT_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .paymentConfirmation(paymentConfirmation)
                        .build()
        );

        // todo sending to email
        String customerName = paymentConfirmation.customerFirstName() + " " + paymentConfirmation.customerLastName();
        emailService.sendPaymentSuccesEmail(paymentConfirmation.customerEmail(),
                customerName,
                paymentConfirmation.amount(),
                paymentConfirmation.orderReference());
    }

    @KafkaListener(topics = "order-topic")
    public void sendOrderNotification(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info("Consuming payment confirmation from order-topic");
        notificationRepository.save(
                Notification.builder()
                        .notificationType(NotificationType.ORDER_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .orderConfirmation(orderConfirmation)
                        .build()
        );

        // todo sending to email
        String customerName = orderConfirmation.customer().firstname() + " " + orderConfirmation.customer().lastname();
        emailService.sendOrderConfirmationEmail(orderConfirmation.customer().email(),
                customerName,
                orderConfirmation.total_amount(),
                orderConfirmation.orderReference(),
                orderConfirmation.products());
    }
}
