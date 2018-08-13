package com.example.blog.service.impl;

import com.example.blog.entity.Role;
import com.example.blog.mapper.RoleMapper;
import com.example.blog.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Override
    public Role findById(String id) {
        return null;
    }

    @Override
    public int add(Role role) {
        return 1;
    }
}
