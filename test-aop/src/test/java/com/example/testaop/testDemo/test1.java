package com.example.testaop.testDemo;

import com.example.testaop.pojo.User;
import com.example.testaop.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class test1 {

    @Autowired
    UserService userService;

    @Test
    public void test1(){
        userService.select(1);
    }

    @Test
    public void test2(){
        User user = new User();
        user.setUsername("张三");
        user.setId("2");
        user.setSex("男");
        user.setAge(18);

        userService.insert(user);
    }

}
