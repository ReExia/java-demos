package com.example.producer.controller;

import com.example.producer.sender.MessageASender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendMessageAController {

    @Autowired
    MessageASender messageASender;

    @GetMapping("/send")
    public String sendMessage(){

        messageASender.send("项目 producer 自定义发送的消息");
        return "消息发送完毕";
    }

}
