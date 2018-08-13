package com.example.blog.service.impl;

import com.example.blog.entity.UserInfo;
import com.example.blog.mapper.UserInfoMapper;
import com.example.blog.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    public UserInfo findByUserId(String id) {
        return null;
    }

    @Override
    public void update(UserInfo userInfo) {
    }

    @Override
    public void add(UserInfo userInfo) {
    }
}
