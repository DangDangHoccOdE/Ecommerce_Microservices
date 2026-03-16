package com.demo.producer;

// import org.springframework.kafka.core.KafkaTemplate;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;

// @RestController
// @RequestMapping("/api")
// public class KafkaController {

// private final KafkaTemplate<String, RiderLocation> kafkaTemplate;

// public KafkaController(KafkaTemplate<String, RiderLocation> kafkaTemplate) {
// this.kafkaTemplate = kafkaTemplate;
// }

// @PostMapping("/send")
// public String sendMessage(@RequestParam String message) {
// RiderLocation riderLocation = new RiderLocation("rider123", 37.7749,
// -122.4194);
// kafkaTemplate.send("my-new-topic", riderLocation);
// return "Message sent to Kafka topic: " + riderLocation.getLongitude();
// }
// }
