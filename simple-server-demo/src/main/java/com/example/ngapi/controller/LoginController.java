package com.example.ngapi.controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;


@RestController
@RequestMapping("/user")
public class LoginController {

    // 这里不关注如何从前端传回用户密码，及如果去校验用户名及密码的正确性；
    // 这里假设已经通过验证并获取用户信息，即user；
    // 这个接口模拟将用户信息保存到session中；
    @CrossOrigin(value = "http://localhost:4200")
    @GetMapping(value = "/login")
    public Object login(HttpServletRequest req, HttpServletResponse resp){

        Map map = new HashMap();

        map.put("status",1000);
        map.put("message", "登陆成功");
        Map userMap = new HashMap();

        userMap.put("username", "setsuna");
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        userMap.put("token", token);
        userMap.put("imageUrl", "assets/img/avatar04.png");

        map.put("userMap",userMap);


        return map;
    }




}
