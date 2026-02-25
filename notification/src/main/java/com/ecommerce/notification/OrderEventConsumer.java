package com.ecommerce.notification;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.ecommerce.notification.payload.OrderCreatedEvent;

@Service
public class OrderEventConsumer {
    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void handleOrderEvent(OrderCreatedEvent orderEvent) {
        System.out.println("Received order event: " + orderEvent);

        Long orderId = orderEvent.getOrderId();
        String status = orderEvent.getStatus().toString();

        System.out.println("Processing order event - Order ID: " + orderId + ", Status: " + status);

        // update database, send email, etc
    }
}
