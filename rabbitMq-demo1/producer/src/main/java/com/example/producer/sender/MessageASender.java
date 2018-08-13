package com.example.producer.sender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MessageASender {

    @Autowired
    AmqpTemplate rabbitTemplate;

    public void send(String value){
        String sendMessage = "[" +value + "]" + " => " + new Date();
        System.out.println("send message => " + sendMessage);
        rabbitTemplate.convertAndSend("MessageA-Queue", sendMessage);
    }

}
