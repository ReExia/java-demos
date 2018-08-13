package com.example.mybatis.dao;

import com.example.mybatis.pojo.Role;
import com.example.mybatis.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface UserMapper {

    int countUserByFirstName(String firstName);

    List<Role> findRolesByMap(Map<String, Object> map);

    void insertUser(User user);

    void insertUserBatch(@Param("userList") List<User> userList);
}
