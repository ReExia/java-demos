package com.salty.mongo.dbref;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class DbRefTest {
	
	private static MongoTemplate mongoTemplate;
	
	static {
		mongoTemplate = (MongoTemplate) new ClassPathXmlApplicationContext("classpath:spring.xml").getBean("mongoTemplate");
	}
	
	public static void main(String[] args) {
		/*ClassInfo classInfo = new ClassInfo("mongodb 1班");
		mongoTemplate.save(classInfo);
		mongoTemplate.save(new Student("zhangsan", classInfo));*/
		//ClassInfo classInfo = mongoTemplate.findOne(Query.query(Criteria.where("id").is("58dbbb008ca9d301fb6e1945")), ClassInfo.class);
		//mongoTemplate.save(new Student("zhangsan2", classInfo));
		
		Student stu = mongoTemplate.findOne(Query.query(Criteria.where("id").is("58dbbb688ca9d30201434e9e")), Student.class);
		System.out.println(stu.getName() + "在" + stu.getClassInfo().getName() + "学习");
	}
	
}
