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
public class TestInsert {

    @Autowired
    RoleMapper roleMapper;

    @Test
    public void test1(){

        Role role = new Role();
        role.setRoleName("老板2");
        role.setNote("22");

        roleMapper.insertRole(role);

    }

    /**
     * 主键回填
     */
    @Test
    public void test2(){

        Role role = new Role();
        role.setRoleName("老板2");
        role.setNote("22");

        roleMapper.insertRoleReturnKey(role);

        System.out.println(role.toString());
    }

    @Test
    public void test3(){
        Role role = new Role();
        role.setRoleName("老板3");
        role.setNote("33");

        roleMapper.insertRoleReturnCustomerKey(role);
        System.out.println(role.toString());
    }


    /**
     * 使用sql属性拼接
     */
    @Test
    public void test4(){
        Role role = new Role();
        role.setRoleName("老板4");
        role.setNote("44");

        roleMapper.insertRoleUseSqlById(role);
        System.out.println(role.toString());
    }



}
