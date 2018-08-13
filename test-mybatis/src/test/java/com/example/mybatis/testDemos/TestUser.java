package com.example.mybatis.testDemos;

import com.example.mybatis.dao.UserMapper;
import com.example.mybatis.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUser {

    @Autowired
    UserMapper userMapper;

    @Test
    public void test1(){

        User user = new User();
        user.setUserName("张三");
        user.setFirstName("张");
        user.setLastName("三");
        user.setUserSex("男");

        userMapper.insertUser(user);
    }

    @Test
    public void test2(){
        userMapper.insertUserBatch(createUserList());
    }

    /**
     * 创建用户列表
     * @return
     */
    public List<User> createUserList(){

        List<User> users = new ArrayList<>();
        for (int i = 0 ; i < 20 ;i++){

            String firstName = "张" + UUID.randomUUID().toString().replace("-", "");
            String lastName = "三" + UUID.randomUUID().toString().replace("_", "");
            String username = firstName + lastName;
            String userSex = "男";

            User user = new User();
            user.setUserName(username);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setUserSex(userSex);

            users.add(user);
        }
        return users;
    }

}
