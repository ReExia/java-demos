package com.example.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.api.facade.IUserFacade;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    @Reference
    IUserFacade userFacade;

    @RequestMapping("/user")
    public Object userInfo(){
       return userFacade.userInfo();
    }

    @RequestMapping("/account")
    public Object accountInfo(){
        return userFacade.accountInfo();
    }

}
