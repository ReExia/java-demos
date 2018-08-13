package com.example.provider.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AccountService {

    public Object accuntInfo(){
        Map map = new HashMap();
        map.put("accountId", "这尼玛居然");
        map.put("password", "******");
        return map;
    }

}
