package com.example.mobile.queue;

import com.example.mobile.util.MobileMessageUtil;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MobileMessageReceiver {

    @JmsListener(destination = "mobile-queue")
    public void receive(String message) {
        String code = UUID.randomUUID().toString().replaceAll("-", "").substring(0,3);
        System.out.println("message is : " + message);
        MobileMessageUtil.sendMessages(code, message);
    }

}
