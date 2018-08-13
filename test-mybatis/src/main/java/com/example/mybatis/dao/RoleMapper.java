package com.example.mybatis.dao;

import com.example.mybatis.params.PageParams;
import com.example.mybatis.params.RoleParams;
import com.example.mybatis.pojo.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface RoleMapper {

     Role getRoleById(Integer roleId);

     List<Role> findRolesByMap(Map<String, Object> map);

     List<Role> findRolesByAnnontation(@Param("roleName") String roleName, @Param("note") String note);

     List<Role> findRolesByBean(RoleParams roleParams);

    List<Role> findByMix(@Param("roleParams") RoleParams roleParams, @Param("pageParams")PageParams pageParams);

    Role getRolesByResultMap(Integer roleId);

    void insertRole(Role role);

    void insertRoleReturnKey(Role role);

    void insertRoleReturnCustomerKey(Role role);

    void updateRoleById(Role role);

    void deleteRoleById(Role role);

    void insertRoleUseSqlById(Role role);

    Map<String, Object> selectRoleAsMap(Integer id);

}
