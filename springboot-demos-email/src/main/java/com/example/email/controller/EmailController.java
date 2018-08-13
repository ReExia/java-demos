package com.example.email.controller;

import com.example.email.util.EmailUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class EmailController {

    //http://localhost:8080/mail/xxx@qq.com
    @GetMapping("/mail/{email}")
    public void  sendMail(@PathVariable("email") String email){
        EmailUtil.sendEmailMessage(email, UUID.randomUUID().toString());
        System.out.println("发送到 : " + email);
    }

}
