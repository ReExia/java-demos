package com.salty.mongo;

import com.salty.mongo.document.Article;
import com.salty.mongo.repository.ArticleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

/**
 * 测试repository
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoRepositoryTests {



	@Autowired
	ArticleRepository articleRepository;

	@Test
	public void contextLoads() {
	}


	@Test
	public void testFind(){
		Iterable<Article> all = articleRepository.findAll();
		all.forEach(article -> System.out.println(article.toString()));
	}

	/*System.out.println(articleRepository.count());
		Iterator<Article> iter = articleRepository.findAll().iterator();
		while (iter.hasNext()) {
			Article article = iter.next();
			System.out.println(article.getTitle());
		}*/

	//List<Article> list = articleRepository.findByTitle("猿天地实战Mongodb课程0");
	//List<Article> list = articleRepository.findByTitle("猿天地实战Mongodb课程0", new PageRequest(1, 2));
	//List<Article> list = articleRepository.findByTitle("猿天地实战Mongodb课程0", new Sort(new Order(Direction.ASC, "visitCount")));
//	List<Article> list = articleRepository.findTop2ByTitle("猿天地实战Mongodb课程0");
//		for (Article article : list) {
//		System.out.println(article.getTitle() + "\t" + article.getVisitCount());

	@Test
	public void testFindByTitleAndVisitCount(){
		List<Article> articles = articleRepository.findByTitleAndVisitCount("springboot配置", 43L);
		articles.forEach(article -> System.out.println(article.toString()));
	}

	@Test
	public void testfindByTitlePage(){
		List<Article> articles = articleRepository.findByTitle("springboot配置", new PageRequest(1, 3));
		articles.forEach(article -> System.out.println(article.toString()));
	}

	@Test
	public void testfindByTitleSort(){
		List<Article> articles = articleRepository.findByTitle("springboot配置", new Sort(new Sort.Order(Sort.Direction.ASC, "addTime")));
		articles.forEach(article -> System.out.println(article.getTitle() + ":" + article.getAddTime()));
	}

	@Test
	public void testFindTop2ByTitle(){
		List<Article> articles = articleRepository.findTop2ByTitle("springboot配置");
		articles.forEach(article -> System.out.println(article.toString()));
	}








}
