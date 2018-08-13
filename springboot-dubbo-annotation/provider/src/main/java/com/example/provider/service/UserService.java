package com.example.provider.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    public Object userInfo(){
        Map map = new HashMap();
        map.put("username", "张三");
        map.put("age", 18);
        map.put("sex", "外星人");
        return map;
    }

}
