package com.example.mybatis.testDemos;

import com.example.mybatis.dao.RoleMapper;
import com.example.mybatis.dao.UserMapper;
import com.example.mybatis.params.PageParams;
import com.example.mybatis.params.RoleParams;
import com.example.mybatis.pojo.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSelect {

    @Autowired
    UserMapper userMapper;

    @Autowired
    RoleMapper roleMapper;

    /**
     * 简单查询
     * 以pojo类作为结果集返回
     */
    @Test
    public void test1(){
        int count = userMapper.countUserByFirstName("张");
        System.out.println("count is : " + count);
    }

    @Test
    public void test2(){
        Role role = roleMapper.getRoleById(1);
        System.out.println(role.toString());
    }

    /**
     * 传递多个参数
     * 优点:适用于几乎所有场景
     * 缺点:业务性，可读性太差
     */
    @Test
    public void test3(){

        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("roleName", "经理");
        queryMap.put("note", "2");

        List<Role> roles = roleMapper.findRolesByMap(queryMap);

        roles.forEach(role -> System.out.println(role.toString()));
    }

    /**
     * 优点:可读性强
     * 缺点:参数多了看起来就会变得比较恶心
     */
    @Test
    public void test4(){

        List<Role> roles = roleMapper.findRolesByAnnontation("经理", "2");

        roles.forEach(role -> System.out.println(role.toString()));
    }

    /**
     * 优点:可读性高，代码简洁
     * 缺点:并不会减少代码量,需要新建xxxParam类
     */
    @Test
    public void test5(){

        RoleParams roleParams = new RoleParams();
        roleParams.setRoleName("经理");
        roleParams.setNote("2");

        List<Role> roles = roleMapper.findRolesByBean(roleParams);

        roles.forEach(role -> System.out.println(role.toString()));
    }

    /**
     * 混合参数，需要明确参数的合理性
     */
    @Test
    public void test6(){

        RoleParams roleParams = new RoleParams();
        roleParams.setRoleName("经理");

        PageParams pageParams = new PageParams();
        pageParams.setLimit(3);
        pageParams.setLimit(6);

        List<Role> roles = roleMapper.findByMix(roleParams, pageParams);

        roles.forEach(role -> System.out.println(role.toString()));
    }

    /**
     * 使用map作为结果集
     */
    @Test
    public void test7(){
        Map<String, Object> map = roleMapper.selectRoleAsMap(3);
        String roleName = (String) map.get("roleName");
        System.out.println(roleName);
    }




}
