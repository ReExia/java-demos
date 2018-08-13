package com.example.blog.service;

import com.example.blog.entity.Role;

/**
 * 角色
 */
public interface RoleService {

    /**
     * 根据id查询角色
     * @param id
     * @return
     */
    Role findById(String id);

    /**
     * 添加角色
     * @param role
     * @return
     */
    int add(Role role);

}
