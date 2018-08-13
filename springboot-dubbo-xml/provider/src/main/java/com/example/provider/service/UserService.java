package com.example.provider.service;


import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    public Object userInfo(){
        Map map = new HashMap();
        map.put("name,", "张三");
        map.put("age", "12");
        map.put("sex", "男");
        return map;
    }

}
