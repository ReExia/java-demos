package com.salty.mongo;

import com.salty.mongo.document.Article;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Array;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoApplicationTests {


	@Autowired
	MongoTemplate mongoTemplate;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testConnect(){
		mongoTemplate.getCollectionNames().forEach(System.out :: println);
	}

	@Test
	public void testInsert(){

		List<Article> data = new ArrayList<Article>();
		for(int i = 0 ; i < 5 ; i++){

			Article article = new Article();
			//生成id
			String id = UUID.randomUUID().toString().replaceAll("-","");
			article.setId(id);
			article.setTitle("springboot配置");
			article.setTags(Arrays.asList("java","ruby","php"));
			article.setVisitCount((long) new Random().nextInt(300));
			article.setAddTime(new Date());

			data.add(article);
		}
		mongoTemplate.insert(data, Article.class);
	}

	@Test
	public void testUpdate(){
		//example1 一般更新
		//Query query = Query.query(new Criteria("id").is("a16cb1080a2142818d00c3fe5ea4c5a8"));
		//Update update = Update.update("title", "测试更新");
		//mongoTemplate.updateFirst(query, update, Article.class);

		//example2
		//Query query = Query.query(new Criteria(("id")).is("21862816"));
		//Update update = Update.update("title", "如果不存在就插入一条");
		//mongoTemplate.upsert(query, update, Article.class);

		//example3 删除key tags
		Query query = new Query(new Criteria("id").is("abac1bf0080e4b33bfe87c7aadc98d83"));
		Update update = new Update().unset("tags");
		//mongoTemplate.updateFirst(query, update , "article_info"); 也可以直接写集合名称
		mongoTemplate.updateFirst(query, update, Article.class);
	}

	@Test
	public void testDelete(){

		//example1 一般删除
		//Query query = new Query(Criteria.where("id").is("272b633c85914c378679c20c260fa1ef"));
		//mongoTemplate.remove(query, Article.class);

		//example2 删除一条，并且返回这条数据
		//Query query = new Query(Criteria.where("id").is("9ce518ce48dd455687f1a74cafb80b81"));
		//Article article = mongoTemplate.findAndRemove(query, Article.class);
		//System.out.println(article.toString());

		//example3 删除所有标题
		//List<Article> articles = mongoTemplate.findAllAndRemove(Query.query(Criteria.where("title").is("springboot配置")), Article.class);

		//example4 删除集合的2种方式
		//mongoTemplate.dropCollection(Article.class);
		//mongoTemplate.dropCollection("article_info");

		//删除数据库
		//mongoTemplate.getDb().drop();
	}

	@Test
	public void testFind(){
		//example1 数量查询
		//Query query = new Query(Criteria.where("visitCount").is(43));
		//long count = mongoTemplate.count(query, Article.class);
		//System.out.println("count : " + count);

		//example2 查询第一条
		//Article article = mongoTemplate.findOne(Query.query(Criteria.where("visitCount").is(43)), Article.class);
		//System.out.println(article.toString());

		//example3 查询所有
		//List<Article> list = mongoTemplate.findAll(Article.class);
		//list.forEach(article -> System.out.println(article.toString()));

		//example4 带条件查询
		//Query query = Query.query(Criteria.where("visitCount").is(43));
		//List<Article> articles = mongoTemplate.find(query, Article.class);
		//articles.forEach(article -> System.out.println(article.toString()));

		//example5 如果在大数据量的情况下，最好是排序，记住你上次最后一条数据的ID,然后就是大于上次的ID,然后limit 页数
		//Query query = Query.query(Criteria.where("visitCount").lt(100)).skip(0).limit(10);
		//List<Article> articles = mongoTemplate.find(query, Article.class);
		//articles.forEach(article -> System.out.println(article.toString()));

		//example6 根据ID查询
		//Article article = mongoTemplate.findById("a16cb1080a2142818d00c3fe5ea4c5a8", Article.class);
		//System.out.println(article.toString());

		//example7 in查询
		//Query query = Query.query(Criteria.where("VisitCount").in(100, 300, 400, 43));
		//List<Article> articles = mongoTemplate.find(query, Article.class);
		//articles.forEach(article -> System.out.println(article.toString()));

		//exmple8 or查询
		Query query = Query.query(new Criteria().orOperator(
				Criteria.where("VisitCount").is(100),
				Criteria.where("VisitCount").is(400),
				Criteria.where("VisitCount").is(300)
		));
		List<Article> articles = mongoTemplate.find(query, Article.class);
		articles.forEach(article -> System.out.println(article.toString()));
	}


}
