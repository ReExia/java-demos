package com.example.solr;

import com.example.solr.entity.Person;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.SolrParams;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.solr.repository.query.SolrQueryMethod;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SolrApplicationTests {

    @Autowired
    SolrClient solrClient;




    /**
     * 增加
     * @throws IOException
     * @throws SolrServerException
     */
    @Test
    public void test1() throws IOException, SolrServerException {

            SolrInputDocument document = new SolrInputDocument();
            Person person = getPerson();
            document.addField("person_name", person.getPersonName());
            document.addField("person_sex", person.getPersonSex());
            document.addField("person_address", person.getPersonAddress());
            document.addField("person_code", person.getPersonCode());
            solrClient.add(document);
            solrClient.commit();
    }

    /**
     * 删除
     * @throws IOException
     * @throws SolrServerException
     */
    @Test
    public void test2() throws IOException, SolrServerException {

        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery("person_name:*");

        solrQuery.setStart(1);
        solrQuery.setRows(40);

        QueryResponse query = solrClient.query(solrQuery);
        SolrDocumentList results = query.getResults();
        List<Person> personList = new ArrayList<>();
        for (SolrDocument result : results) {
            Person person = new Person();
            person.setPersonCode(String.valueOf(result.get("person_code")));
            person.setPersonSex(String.valueOf(result.get("person_sex")));
            person.setPersonName(String.valueOf(result.get("person_address")));
            person.setPersonAddress(String.valueOf(result.get("person_code")));
            personList.add(person);
        }

        personList.forEach(person -> System.out.println(person.toString()));

    }

    /**
     * 修改
     * @throws IOException
     * @throws SolrServerException
     */
    @Test
    public void test3() throws IOException, SolrServerException {
    }

    public Person getPerson() {
        String str = UUID.randomUUID().toString().replace("-", "");
        Person person = new Person();
        person.setPersonCode(str);
        person.setPersonName(str);
        person.setPersonAddress("地球");
        person.setPersonSex("外星人");
        return person;
    }

}
