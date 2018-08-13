package com.example.blog.service.impl;

import com.example.blog.entity.Upvote;
import com.example.blog.mapper.UpvoteMapper;
import com.example.blog.service.UpvoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpvoteServiceImpl implements UpvoteService {

    @Autowired
    UpvoteMapper upvoteMapper;

    @Override
    public Upvote findByUserIdAndConId(Upvote upvote) {
        return null;
    }

    @Override
    public int add(Upvote upvote) {
        return 1;
    }

    @Override
    public Upvote getByUserId(Upvote upvote) {
        return null;
    }

    @Override
    public void update(Upvote upvote) {
    }
}
