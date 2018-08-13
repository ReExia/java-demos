package com.example.elasticsearch;

import com.example.elasticsearch.entity.Article;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticsearchApplicationTests {

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @Before
    public void testCreateIndex() {
        //elasticsearchTemplate.deleteIndex(ArticleDocument.class);
        //elasticsearchTemplate.createIndex(ArticleDocument.class);
        elasticsearchTemplate.putMapping(Article.class);
        //elasticsearchTemplate.refresh(ArticleDocument.class);
    }

    @Test
    public void testPut() throws JsonProcessingException {

        Article article = new Article();
        article.setTitle("阿长与山海经");
        article.setAuthor("周树人");
        List<String> comment = new ArrayList();
        comment.add("好评");
        comment.add("五星好评");
        comment.add("非常好");
        article.setComment(comment);
        article.setCreateDate(new Date());
        article.setUpdateDate(new Date());

        IndexQuery indexQuery = new IndexQuery();
        indexQuery.setId("1");
        indexQuery.setIndexName("blog_article");
        indexQuery.setType("article");
        indexQuery.setSource(objectMapper.writeValueAsString(article));

        elasticsearchTemplate.index(indexQuery);
    }

}
