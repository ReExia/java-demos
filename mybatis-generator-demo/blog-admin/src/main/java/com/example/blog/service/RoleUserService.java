package com.example.blog.service;

import com.example.blog.entity.RoleUser;
import com.example.blog.entity.User;

import java.util.List;

/**
 * 角色用户关联
 */
public interface RoleUserService {

    /**
     * 根据用户查询角色用户集合
     * @param user
     * @return
     */
    List<RoleUser> findByUser(User user);

    /**
     * 添加用户角色中间对象
     * @param roleUser
     * @return id
     */
    int add(RoleUser roleUser);


    /**
     * 根据用户id删除
     * @param userId
     */
    void deleteByUserId(String userId);


}
