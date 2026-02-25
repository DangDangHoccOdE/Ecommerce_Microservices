package com.ecommerce.order.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;


@RestController
@RefreshScope
public class MessageController {
    @Value("${app.message}")
    private String message;

    @GetMapping("/message")
    @RateLimiter(name = "rateBreaker", fallbackMethod = "fallbackRetry")
    public String getMessaage() {
        return message;
    }
    
    public String fallbackRetry(Exception e) {
        return "Fallback message: " + e.getMessage();
    }
}
