package com.ecommerce.notification;

import java.beans.BeanProperty;
import java.util.function.Consumer;

// import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.ecommerce.notification.payload.OrderCreatedEvent;
import org.springframework.context.annotation.Bean;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderEventConsumer {
    // @RabbitListener(queues = "${rabbitmq.queue.name}")
    // public void handleOrderEvent(OrderCreatedEvent orderEvent) {
    // System.out.println("Received order event: " + orderEvent);

    // Long orderId = orderEvent.getOrderId();
    // String status = orderEvent.getStatus().toString();

    // System.out.println("Processing order event - Order ID: " + orderId + ",
    // Status: " + status);

    // // update database, send email, etc
    // }

    @Bean
    public Consumer<OrderCreatedEvent> orderCreated() {
        return event -> {
            log.info("Received order event: {}", event.getOrderId());
            log.info("Processing order event - User ID: {}", event.getUserId());
        };
    }
}
