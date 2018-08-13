package com.example.rq;

import com.example.rq.entity.User;
import com.example.rq.object.ObjectSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestObject {

    @Autowired
    ObjectSender objectSender;

    @Test
    public void test1() throws InterruptedException {
        User user = new User();
        user.setName("张三");
        user.setPass("zhangsan pass");

        objectSender.send(user);
        Thread.sleep(1000l);
    }

}
