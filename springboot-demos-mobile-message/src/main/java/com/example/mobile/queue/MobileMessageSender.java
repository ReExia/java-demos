package com.example.mobile.queue;

import com.example.mobile.util.MobileMessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.MapMessage;
import javax.jms.Message;


@Component
public class MobileMessageSender{

    @Autowired
    private JmsTemplate jmsTemplate;

    public void send(String message) {
        jmsTemplate.convertAndSend("mobile-queue", message);
    }
}
