package com.demo.transaction;


import com.demo.transaction.mapper.RoleMapper;
import com.demo.transaction.pojo.Role;
import com.demo.transaction.service.RoleListService;
import com.demo.transaction.service.RoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-cfg.xml" })
public class Test1 {

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    RoleListService roleListService;

    @Test
    public void test1(){

        roleListService.insertRoleList(createRoleList());
    }


    public List<Role> createRoleList(){

        List<Role> roles = new ArrayList<>();

        for (int i = 0 ; i < 10 ; i++){

            String afterFixx = UUID.randomUUID().toString().replace("_","");
            String roleName = String.format("role_name_%s", afterFixx);
            Role role = new Role();
            role.setRoleName(roleName);
            role.setNote(afterFixx);

            roles.add(role);
        }
        return roles;
    }

}
