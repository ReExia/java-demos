package com.example.shiroweb;

import com.example.shiroweb.shiro.HmacSHA256Utils;

import java.util.HashMap;
import java.util.Map;

public class TestPwd {

    public static void main(String[] args) {
        //实际中，此代码是由客户端进行编写.
        //8fe3fbc9c67958bd835a547bee75c91d803677cc825f6f8e3fecb5d46e076776
        String key = "setsuna1234";
        Map<String,String> map = new HashMap<String,String>();
        map.put("username","admin");
        map.put("params1","love");
        map.put("params2","girl");
        System.out.println(HmacSHA256Utils.digest(key, map));
    }

}
