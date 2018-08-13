package com.example.blog.service;

import com.example.blog.entity.Comment;

import java.util.List;

/**
 * 评论
 */
public interface CommentService {

    /**
     * 添加评论
     * @param comment
     * @return
     */
    int add(Comment comment);

    /**
     * 更新评论
     * @param comment
     */
    void update(Comment comment);

    /**
     * 通过文章id查找所有评论
     * @param contentId
     * @return
     */
    List<Comment> findAllByContentId(String contentId);

    /**
     * 根据id查询评论
     * @param id
     * @return
     */
    Comment findById(String id);

    /**
     * 根据文章id查询所有父评论
     * @param contentId
     * @return
     */
    List<Comment> findAllParentComment(String contentId);

    /**
     * 根据文章id和评论ids查询所有子评论
     * @param contentId
     * @param children
     * @return
     */
    List<Comment> findAllChildrenComment(String contentId, String children);

    /**
     * 根据id删除
     * @param id
     */
    void deleteById(String id);

    /**
     * 批量删除子评论
     * @param children
     */
    void deleteChildrenComment(String children);


}
