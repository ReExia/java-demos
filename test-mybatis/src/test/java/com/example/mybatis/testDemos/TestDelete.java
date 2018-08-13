package com.example.mybatis.testDemos;

import com.example.mybatis.dao.RoleMapper;
import com.example.mybatis.pojo.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDelete {

    @Autowired
    RoleMapper roleMapper;

    @Test
    public void test1(){
        Role role = new Role();
        role.setRoleId(7);

        roleMapper.deleteRoleById(role);
    }

}
