package com.example.shirodemo1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
//@EnableCaching
public class ShiroDemo1Application {

    public static void main(String[] args) {
        SpringApplication.run(ShiroDemo1Application.class, args);
    }
}
