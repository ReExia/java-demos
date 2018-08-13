package com.example.elasticsearch.demo03;

import com.example.elasticsearch.entity.Article;
import com.example.elasticsearch.entity.Emp;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmpTest {

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void testCreateIndex() {
        //elasticsearchTemplate.createIndex(Emp.class);
        //elasticsearchTemplate.deleteIndex(Emp.class);
        //elasticsearchTemplate.createIndex(Emp.class);
        //elasticsearchTemplate.putMapping(Emp.class);
    }

    /**
     * 创建一个员工
     *
     * @throws JsonProcessingException
     */
    @Test
    public void createEmp() throws JsonProcessingException {

        IndexQuery indexQuery = new IndexQuery();
        indexQuery.setIndexName("company");
        indexQuery.setType("emp");
        indexQuery.setId("1");

        Emp emp = new Emp();
        emp.setEmpId(getId());
        emp.setName("jack");
        emp.setAge(27);
        emp.setPosition("technique");
        emp.setCountry("china");
        emp.setJoinDate(new Date());
        emp.setSalary(10000L);

        indexQuery.setSource(objectMapper.writeValueAsString(emp));
        elasticsearchTemplate.index(indexQuery);
    }

    /**
     * 按id获取一个员工
     */
    @Test
    public void getEmp() {
        QueryBuilder queryBuilder = QueryBuilders.idsQuery().addIds("1");
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withIndices("company")
                .withTypes("emp")
                .withQuery(queryBuilder)
                .build();
        List<Emp> emps = elasticsearchTemplate.queryForList(searchQuery, Emp.class);
        emps.forEach(System.out::println);
    }

    /**
     * 按id删除一个员工,注意这里的id是documentId
     */
    @Test
    public void deleteEmp() {
        DeleteQuery deleteQuery = new DeleteQuery();
        QueryBuilder queryBuilder = QueryBuilders.idsQuery().addIds("1");
        deleteQuery.setQuery(queryBuilder);
        deleteQuery.setIndex("company");
        deleteQuery.setType("emp");
        elasticsearchTemplate.delete(deleteQuery);
    }

    @Test
    public void updateEmp() throws IOException {

        UpdateRequest updateRequest = new UpdateRequest("company", "emp", "1")
                .doc(
                        XContentFactory
                                .jsonBuilder()
                                .startObject()
                                .field("position", "technique manager")
                                .endObject()
                );

        UpdateQuery updateQuery = new UpdateQueryBuilder()
                .withIndexName("company")
                .withType("emp")
                .withId("1")
                .withUpdateRequest(updateRequest)
                .withClass(Emp.class)
                .withDoUpsert(true)
                .build();

        elasticsearchTemplate.update(updateQuery);
    }

    public String getId() {
        return UUID.randomUUID().toString();
    }

}
