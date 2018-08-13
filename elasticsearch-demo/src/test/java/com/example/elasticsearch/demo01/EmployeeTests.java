package com.example.elasticsearch.demo01;

import com.example.elasticsearch.entity.Employee;
import com.example.elasticsearch.entity.EmployeeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;


@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeTests {

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void testCreateIndex() {
        //创建索引
        elasticsearchTemplate.createIndex(Employee.class);
        //删除索引
        //elasticsearchTemplate.deleteIndex(Employee.class);
    }

    @Test
    public void testInsert() throws JsonProcessingException {

        //创建实体类
        EmployeeInfo info = new EmployeeInfo();
        info.setBio("curious and modest");
        info.setAge(30);
        info.setInterests(new String[]{"bike", "climb"});
        Employee employee = new Employee();
        employee.setEmail("zhangsan@sina.com");
        employee.setFirstName("si");
        employee.setLastName("zhang");
        employee.setInfo(info);
        employee.setJoinDate(new Date());

        //不设置id存入时会默认生成AWUXGi_cWdC5Tuh-9fUt
        //也可以使用建造者new IndexQueryBuilder()
        IndexQuery indexQuery = new IndexQuery();
        indexQuery.setIndexName("person_collection");
        indexQuery.setType("employee");
        indexQuery.setId("3");
        indexQuery.setSource(objectMapper.writeValueAsString(employee));
        //插入文档,如果id一样视为更新,新文档，某个字段如果是空,会覆盖原来有值的字段
        elasticsearchTemplate.index(indexQuery);
    }

    @Test
    public void testUpdate() throws IOException {

        //创建实体类
        EmployeeInfo info = new EmployeeInfo();
        info.setBio("curious and modest");
        info.setAge(33);
        info.setInterests(new String[]{"bike", "climb"});
        Employee employee = new Employee();
        employee.setEmail("zhangsan@sina.com");
        employee.setFirstName("san");
        employee.setLastName("zhang");
        employee.setInfo(info);
        employee.setJoinDate(new Date());

        //创建文档对象,必须设置上id才可以插入
        UpdateQuery updateQuery = new UpdateQuery();
        updateQuery.setIndexName("person_collection");
        updateQuery.setType("employee");
        updateQuery.setId("2");

        //原文档值
        IndexRequest indexRequest = new IndexRequest("person_collection", "employee","2")
                .source(
                        XContentFactory.jsonBuilder()
                                .startObject()
                                .field("email", "zhangsan@sina.com")
                                .endObject()
                );

        //更新的值
        UpdateRequest updateRequest = new UpdateRequest("person_collection", "employee", "2")
                .doc(
                        XContentFactory.jsonBuilder()
                                .startObject()
                                .field("email", "rose@sina.com")
                                .endObject()
                )
                .upsert(indexRequest);

        updateQuery.setUpdateRequest(updateRequest);
        //更新
        elasticsearchTemplate.update(updateQuery);
    }

    /**
     * 什么都不设置就删除全部
     * @throws JsonProcessingException
     */
    @Test
    public void testDelete() throws JsonProcessingException {

        DeleteQuery deleteQuery = new DeleteQuery();
        deleteQuery.setIndex("person_collection");
        deleteQuery.setType("employee");

        //设置删除条件,按id删除
        QueryBuilder queryBuilder = new IdsQueryBuilder();
        String[] ids = new String[]{"1"};
        ((IdsQueryBuilder) queryBuilder).addIds(ids);
        deleteQuery.setQuery(queryBuilder);

        elasticsearchTemplate.delete(deleteQuery);
    }

    /**
     * 测试按id查找
     */
    @Test
    public void testSelect(){
        GetQuery getQuery = new GetQuery();
        getQuery.setId("2");

        Employee employee = elasticsearchTemplate.queryForObject(getQuery, Employee.class);
        System.out.println(employee.toString());
    }

    @Test
    public void testSelectAll(){
        //包含firstName 或者 zhang的记录,通配符可用
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.queryStringQuery("firstName rose")).build();
        List<Employee> employees = elasticsearchTemplate.queryForList(searchQuery, Employee.class);
        employees.forEach(System.out::println);
    }


}
