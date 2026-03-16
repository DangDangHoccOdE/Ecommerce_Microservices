package com.demo.producer;

import java.util.Random;
import java.util.function.Supplier;

import org.springframework.messaging.Message;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.MimeTypeUtils;

@Configuration
public class KafkaProducerStreams {
    @Bean
    public Supplier<RiderLocation> sendRiderLocation() {
        Random random = new Random();
        return () -> {
            String riderId = "rider" + random.nextInt(100);
            RiderLocation riderLocation = new RiderLocation(riderId, 332.112, -444.4194);
            System.out.println("Sending Rider Location: " + riderLocation.getLongitude());
            return riderLocation;
        };
    }

    @Bean
    public Supplier<Message<String>> sendRiderStatus() {
        Random random = new Random();
        return () -> {
            String riderId = "rider" + random.nextInt(20);
            String status = random.nextBoolean() ? "ride started"
                    : "ride completed";
            System.out.println("Sending: " + status);
            return MessageBuilder.withPayload(riderId + ":" + status)
                    .setHeader(KafkaHeaders.KEY, riderId.getBytes())
                    .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.TEXT_PLAIN)
                    .build();
        };
    }

}