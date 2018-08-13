package com.example.blog.service;

import com.example.blog.entity.UserInfo;

/**
 * 用户信息
 */
public interface UserInfoService {

    /**
     * 根据用户id查找用户详细信息
     * @param id
     * @return
     */
    UserInfo findByUserId(String id);

    /**
     * 更新用户详细信息
     * @param userInfo
     */
    void update(UserInfo userInfo);

    /**
     * 添加用户详细新
     * @param userInfo
     */
    void add(UserInfo userInfo);

}
