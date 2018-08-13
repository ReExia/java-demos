package com.example.testredis.testDemo;

import com.example.testredis.pojo.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test3 {

    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 使用这种方式写入需要序列化写入对象
     */
    @Test
    public void test1(){
        Role role = new Role();
        role.setRoleId("1");
        role.setRoleName("管理员");
        redisTemplate.opsForValue().set("role1",role);
    }

    @Test
    public void test2(){
        Role role = (Role) redisTemplate.opsForValue().get("role1");
        System.out.println(role.toString());
    }


    /**
     * 使用template存储字符串
     */
    @Test
    public void test3(){
        Role role = new Role();
        role.setRoleId("2");
        role.setRoleName("管理员2");

        redisTemplate.opsForValue().set("role2",role.toString());
    }

    @Test
    public void test4(){
        String result = (String) redisTemplate.opsForValue().get("role2");
        System.out.println(result);
    }

}
