package com.example.blog.service.impl;

import com.example.blog.entity.UserContent;
import com.example.blog.mapper.UserContentMapper;
import com.example.blog.service.UserContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserContentServiceImpl implements UserContentService {

    @Autowired
    UserContentMapper userContentMapper;

    @Override
    public void addContent(UserContent content) {

    }

    @Override
    public List<UserContent> findByUserId(String userId) {
        return null;
    }

    @Override
    public List<UserContent> findAll() {
        return null;
    }

    @Override
    public UserContent findById(String id) {
        return null;
    }

    @Override
    public void updateById(UserContent content) {
    }
}
