package com.example.rq.hello;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class HelloSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(){
        String value = "hello" + new Date();
        System.out.println("Sender : " + value);
        rabbitTemplate.convertAndSend("hello", value);
    }

}
