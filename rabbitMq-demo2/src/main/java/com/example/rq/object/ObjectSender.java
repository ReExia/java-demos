package com.example.rq.object;

import com.example.rq.entity.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ObjectSender {

    @Autowired
    AmqpTemplate rabbitTemplate;

    public void send(User user){
        System.out.println("Sender object: " + user.toString());
        rabbitTemplate.convertAndSend("object", user);
    }

}
