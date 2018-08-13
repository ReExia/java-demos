package com.example.blog.service;

import com.example.blog.entity.RoleResource;

import java.util.List;

/**
 * 角色资源关联
 */
public interface RoleResourceService {

    /**
     * 添加roleRource
     * @param roleResource
     */
    void add(RoleResource roleResource);

    /**
     * 根据id查询RoleResource
     * @param id
     * @return
     */
    RoleResource findById(String id);

    /**
     * 根据角色id查询角色资源集合
     * @param roleId
     * @return
     */
    List<RoleResource> findByRoleId(String roleId);

    /**
     * 根据id删除RoleResource
     * @param id
     */
    void deleteById(String id);


}
