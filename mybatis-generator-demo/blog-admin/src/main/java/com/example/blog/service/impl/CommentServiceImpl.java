package com.example.blog.service.impl;

import com.example.blog.entity.Comment;
import com.example.blog.mapper.CommentMapper;
import com.example.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentMapper commentMapper;

    @Override
    public int add(Comment comment) {
        return commentMapper.insert(comment);

    }

    @Override
    public void update(Comment comment) {
        commentMapper.updateByPrimaryKey( comment );
    }

    @Override
    public List<Comment> findAllByContentId(String contentId) {
        return null;
    }

    @Override
    public Comment findById(String id) {
        return commentMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Comment> findAllParentComment(String contentId) {
        return null;
    }

    @Override
    public List<Comment> findAllChildrenComment(String contentId, String children) {
        return null;
    }

    @Override
    public void deleteById(String id) {
        commentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteChildrenComment(String children) {

    }
}
