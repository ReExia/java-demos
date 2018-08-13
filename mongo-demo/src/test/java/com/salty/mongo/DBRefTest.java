package com.salty.mongo;

import com.salty.mongo.dbref.ClassInfo;
import com.salty.mongo.dbref.Student;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DBRefTest {


    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    public void testDbRefTest(){

        //ClassInfo classInfo = new ClassInfo("1班");
        //classInfo.setId(randomId());
        //mongoTemplate.save(classInfo);
        //mongoTemplate.save(new Student("张三", classInfo));
        Student student = mongoTemplate.findById(new ObjectId("5abc9916007ae26a24217827"), Student.class);

        System.out.println(student.toString());
    }

    public String randomId(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
