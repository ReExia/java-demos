package com.example.elasticsearch.demo03;

import com.example.elasticsearch.entity.Emp;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmpSearchTest {

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void testCreateIndex() {
        //elasticsearchTemplate.createIndex(Emp.class);
        elasticsearchTemplate.deleteIndex(Emp.class);
        //elasticsearchTemplate.createIndex(Emp.class);
        //elasticsearchTemplate.putMapping(Emp.class);
    }

    /**
     * 执行搜索操作
     * @param
     */
    @Test
    public void executeSearch() {
        Client client = elasticsearchTemplate.getClient();
        SearchResponse response = client.prepareSearch("company")
                .setTypes("emp")
                .setQuery(QueryBuilders.matchQuery("position", "technique"))
                .setPostFilter(QueryBuilders.rangeQuery("age").from(30).to(40))
                .setFrom(1).setSize(3)
                .get();

        SearchHit[] searchHits = response.getHits().getHits();
        for(int i = 0; i < searchHits.length; i++) {
            System.out.println(searchHits[i].getSourceAsString());
        }
    }
    
    @Test
    public void executeSearch2(){
        QueryBuilder queryBuilder = QueryBuilders.matchQuery("position", "technique");
        QueryBuilder queryBuilder1 = QueryBuilders.rangeQuery("age").from(30).to(50);
        Pageable pageable = PageRequest.of(1,3);
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                //.withQuery(queryBuilder)
                //.withFilter(queryBuilder1)
                .withPageable(pageable)
                .build();
        AggregatedPage<Emp> emps = elasticsearchTemplate.queryForPage(searchQuery, Emp.class);
        emps.forEach(System.out::println);
    }
    
    /**
     * 初始化数据
     * @throws IOException
     */
    @Test
    public void prepareData() throws IOException {
        Client client = elasticsearchTemplate.getClient();
        client.prepareIndex("company", "emp", "1")
                .setSource(XContentFactory.jsonBuilder()
                        .startObject()
                        .field("empId", getId())
                        .field("name", "jack")
                        .field("age", 27)
                        .field("position", "technique software")
                        .field("country", "china")
                        .field("joinDate", "2017-01-01")
                        .field("salary", 10000)
                        .endObject())
                .get();

        client.prepareIndex("company", "emp", "2")
                .setSource(XContentFactory.jsonBuilder()
                        .startObject()
                        .field("empId", getId())
                        .field("name", "marry")
                        .field("age", 35)
                        .field("position", "technique manager")
                        .field("country", "china")
                        .field("joinDate", "2017-01-01")
                        .field("salary", 12000)
                        .endObject())
                .get();

        client.prepareIndex("company", "emp", "3")
                .setSource(XContentFactory.jsonBuilder()
                        .startObject()
                        .field("empId", getId())
                        .field("name", "tom")
                        .field("age", 32)
                        .field("position", "senior technique software")
                        .field("country", "china")
                        .field("joinDate", "2016-01-01")
                        .field("salary", 11000)
                        .endObject())
                .get();

        client.prepareIndex("company", "emp", "4")
                .setSource(XContentFactory.jsonBuilder()
                        .startObject()
                        .field("empId", getId())
                        .field("name", "jen")
                        .field("age", 25)
                        .field("position", "junior finance")
                        .field("country", "usa")
                        .field("joinDate", "2016-01-01")
                        .field("salary", 7000)
                        .endObject())
                .get();

        client.prepareIndex("company", "emp", "5")
                .setSource(XContentFactory.jsonBuilder()
                        .startObject()
                        .field("empId", getId())
                        .field("name", "mike")
                        .field("age", 37)
                        .field("position", "finance manager")
                        .field("country", "usa")
                        .field("joinDate", "2015-01-01")
                        .field("salary", 15000)
                        .endObject())
                .get();
    }


    public String getId() {
        return UUID.randomUUID().toString();
    }

}
