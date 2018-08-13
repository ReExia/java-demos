package com.example.consumer.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "MessageA-Queue")
public class MessageAReceiver {

    @RabbitHandler
    public void receive(String value){
        System.out.println("receive message => " + value);
    }

}
