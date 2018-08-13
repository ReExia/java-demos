package com.example.consumer.controller;

import com.example.api.facade.IUserFacade;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
public class UserController {

    @Resource
    IUserFacade userFacade;

    @RequestMapping("/user")
    public Object userInfo(){
       return userFacade.userInfo();
    }

    @RequestMapping("/account")
    public Object account(){
        return userFacade.accountInfo();
    }

}
