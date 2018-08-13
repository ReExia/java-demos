package com.example.blog.service.impl;

import com.example.blog.entity.LoginLog;
import com.example.blog.mapper.LoginLogMapper;
import com.example.blog.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginLogServiceImpl implements LoginLogService {

    @Autowired
    LoginLogMapper loginLogMapper;


    @Override
    public int add(LoginLog loginLog) {
        return 1;
    }

    @Override
    public List<LoginLog> findAll() {
        return null;
    }

    @Override
    public List<LoginLog> findByUserId(String userId) {
        return null;
    }
}
