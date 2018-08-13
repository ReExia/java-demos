package com.example.permission;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.permission.mapper")
public class ShiroPermissionApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShiroPermissionApplication.class, args);
    }
}
