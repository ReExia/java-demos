package com.example.permission;

import com.example.permission.entity.Role;
import com.example.permission.mapper.CustomerMapper;
import com.example.permission.mapper.RoleMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShiroPermissionApplicationTests {

    @Resource
    RoleMapper roleMapper;

    @Test
    public void contextLoads() {
        Role role = new Role();
        role.setId("sasasasssxssass");
        role.setRoleName("jack");
        role.setSn("xxxxxx");
        roleMapper.insert(role);
    }

}
