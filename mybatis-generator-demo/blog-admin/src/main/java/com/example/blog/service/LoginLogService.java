package com.example.blog.service;

import com.example.blog.entity.LoginLog;

import java.util.List;

/**
 * 登陆日志
 */
public interface LoginLogService {

    /**
     * 添加登录日志
     * @param loginLog
     * @return
     */
    int add(LoginLog loginLog);

    /**
     * 查询所有日志
     * @return
     */
    List<LoginLog> findAll();

    /**
     * 根据用户id查询日志集合
     * @param userId
     * @return
     */
    List<LoginLog> findByUserId(String userId);


}
