package com.example.producer.mqConfig;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Bean
    public Queue messageA(){
        return new Queue("MessageA-Queue");
    }

}
