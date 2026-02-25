package com.ecommerce.order.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {
    @Value("${rabbitmq.exchange.name}")
    public String exchangeName;

    @Value("${rabbitmq.queue.name}")
    public String queueName;

    @Value("${rabbitmq.routing.key}")
    public String routingKey;

    @Bean
    public Queue queue() {
        return QueueBuilder.durable(queueName).build();
    }
    // durable để đảm bảo rằng hàng đợi sẽ tồn tại ngay cả khi RabbitMQ khởi động lại

    @Bean
    public TopicExchange exchange() {
        return ExchangeBuilder.topicExchange(exchangeName)
                .durable(true)
                .build();
    }
    // Tạo một exchange kiểu topic, cho phép routing key có thể chứa ký tự đại diện

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue())
                .to(exchange())
                .with(routingKey);
    }
    // Nếu product gửi mesage với routing key "order.created", thì message sẽ được gửi đến hàng đợi "order.queue"

    @Bean
    public AmqpAdmin amqpAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin admin = new RabbitAdmin(connectionFactory);
        admin.setAutoStartup(true);
        return admin;
    }
    // RabbitAdmin sẽ tự động tạo exchange, queue và binding khi ứng dụng khởi động

    @Bean
    public MessageConverter messageConverter() {
        return new JacksonJsonMessageConverter();
    }
    // Rabbit mặc định gửi bytes, nhưng chúng ta muốn gửi JSON, nên sử dụng Jackson2JsonMessageConverter để tự động chuyển đổi giữa object và JSON

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        template.setExchange(exchangeName);
        return template;
    }
    // RabbitTemplate là lớp chính để gửi và nhận message. 
    // Chúng ta cấu hình nó để sử dụng exchange đã tạo và message converter để tự động chuyển đổi giữa object và JSON
}