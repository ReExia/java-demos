package com.example.mobile.controller;

import com.example.mobile.queue.MobileMessageSender;
import com.example.mobile.queue.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;
import java.util.UUID;

@RestController
public class MobileController {

    @Autowired
    Sender sender;

    @Autowired
    MobileMessageSender mobileMessageSender;


    @GetMapping("/send/{phone}")
    public void sendMessage(@PathVariable("phone") String phone){
        mobileMessageSender.send(phone);
    }

    /**
     * 测试队列
     */
    @GetMapping("/mq")
    public void mq(){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0; i < 100; i++) {
            sender.send("发送消息");
        }
        stopWatch.stop();
        System.out.println("发送消息耗时: " + stopWatch.getTotalTimeMillis());
    }

}
