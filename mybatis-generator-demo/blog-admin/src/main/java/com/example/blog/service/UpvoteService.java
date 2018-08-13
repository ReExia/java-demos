package com.example.blog.service;

import com.example.blog.entity.Upvote;

/**
 * 点赞
 */
public interface UpvoteService {

    /**
     * 根据用户id和文章id查询
     * @param upvote
     * @return
     */
    Upvote findByUserIdAndConId(Upvote upvote);

    /**
     * 添加upvote
     * @param upvote
     * @return
     */
    int add(Upvote upvote);

    /**
     * 根据用户id查询最后一次登录的Upvote
     * @param upvote
     * @return
     */
    Upvote getByUserId(Upvote upvote);

    /**
     * 更新Upvote
     * @param upvote
     */
    void update(Upvote upvote);

}
