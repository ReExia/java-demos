package com.example.blog;


import com.example.blog.entity.Comment;
import com.example.blog.entity.RoleUser;
import com.example.blog.mapper.CommentMapper;
import com.example.blog.mapper.RoleUserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogAdminApplicationTests {

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    RoleUserMapper roleUserMapper;
    @Test
    public void test1(){
        RoleUser roleUser = roleUserMapper.selectByPrimaryKey("1");
        System.out.println(roleUser.getId());
    }


}
