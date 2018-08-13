package com.example.testaop.service;

import com.example.testaop.dao.UserDao;
import com.example.testaop.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    /**
     * 添加用户
     * @param user
     */
    public void insert(User user){
        userDao.insert(user);
    }

    /**
     * 删除用户
     * @param user
     */
    public void delete(User user){
        userDao.delete(user);
    }

    /**
     * 更新用户
     * @param user
     */
    public void update(User user){
        userDao.update(user);
    }

    /**
     * 获取用户
     * @param id
     * @return
     */
    public User select(Integer id){
       return userDao.select(1);
    }

    public void throwCustomerException() throws Exception{
        throw new RuntimeException("异常");
    }


}
