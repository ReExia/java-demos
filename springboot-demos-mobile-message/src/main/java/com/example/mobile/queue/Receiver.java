package com.example.mobile.queue;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    @JmsListener(destination = "test-queue")
    public void receive(String message) {
        System.out.println("收到的 message 是：" + message);
    }

}
