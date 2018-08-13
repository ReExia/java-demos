package com.example.blog.service.impl;

import com.example.blog.entity.RoleUser;
import com.example.blog.entity.User;
import com.example.blog.mapper.RoleUserMapper;
import com.example.blog.service.RoleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleUserServiceImpl implements RoleUserService {

    @Autowired
    RoleUserMapper roleUserMapper;

    @Override
    public List<RoleUser> findByUser(User user) {
        return null;
    }

    @Override
    public int add(RoleUser roleUser) {
        return 1;
    }

    @Override
    public void deleteByUserId(String userId) {
    }
}
