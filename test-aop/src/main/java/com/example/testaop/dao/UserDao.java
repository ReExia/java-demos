package com.example.testaop.dao;

import com.example.testaop.pojo.User;
import org.springframework.stereotype.Component;

@Component
public class UserDao {

    /**
     * 添加用户
     * @param user
     */
    public void insert(User user){
        System.out.println("insert user : ");
    }

    /**
     * 删除用户
     * @param user
     */
    public void delete(User user){
        System.out.println("delete user : ");
    }

    /**
     * 更新用户
     * @param user
     */
    public void update(User user){
        System.out.println("update user : " );
    }

    /**
     * 获取用户
     * @param id
     * @return
     */
    public User select(Integer id){
        User user = new User();
        user.setId("1");
        user.setAge(18);
        user.setUsername("张三");
        user.setSex("男");
        return user;
    }

}
