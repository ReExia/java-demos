package com.example.blog.service;

import com.example.blog.entity.UserContent;

import java.util.List;

/**
 * 文章
 */
public interface UserContentService {


    /**
     * 添加文章
     * @param content
     */
    void addContent(UserContent content);

    /**
     * 根据用户id查询文章集合
     * @param userId
     * @return
     */
    List<UserContent> findByUserId(String userId);

    /**
     * 查询所有文章
     * @return
     */
    List<UserContent> findAll();

    /**
     * 根据文章id查找文章
     * @param id
     * @return
     */
    UserContent findById(String id);
    /**
     * 根据文章id更新文章
     * @param content
     * @return
     */
    void updateById(UserContent content);

}
