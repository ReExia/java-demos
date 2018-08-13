package com.example.elasticsearch.demo03;

import com.example.elasticsearch.entity.Emp;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AbstractAggregationBuilder;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.bucket.histogram.Histogram;
import org.elasticsearch.search.aggregations.bucket.terms.LongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.avg.Avg;
import org.elasticsearch.search.aggregations.metrics.avg.AvgAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.avg.InternalAvg;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.ResultsExtractor;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmpAggTest {

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
     * （1）首先按照country国家来进行分组
     * （2）然后在每个country分组内，再按照入职年限进行分组
     * （3）最后计算每个分组内的平均薪资
     */
    @Test
    public void agg(){
        Client client = elasticsearchTemplate.getClient();
        SearchResponse searchResponse = client.prepareSearch("company")
                .addAggregation(AggregationBuilders.terms("group_by_country").field("country")
                        .subAggregation(AggregationBuilders
                                .dateHistogram("group_by_join_date")
                                .field("joinDate")
                                //直方图间隔
                                .dateHistogramInterval(DateHistogramInterval.YEAR)
                                .subAggregation(AggregationBuilders.avg("avg_salary").field("salary")))
                )
                .execute().actionGet();

        Map<String, Aggregation> aggrMap = searchResponse.getAggregations().asMap();

        StringTerms groupByCountry = (StringTerms) aggrMap.get("group_by_country");
        Iterator<StringTerms.Bucket> groupByCountryBucketIterator = groupByCountry.getBuckets().iterator();
        while(groupByCountryBucketIterator.hasNext()) {
            StringTerms.Bucket groupByCountryBucket = groupByCountryBucketIterator.next();
            System.out.println(groupByCountryBucket.getKey() + ":" + groupByCountryBucket.getDocCount());

            Histogram groupByJoinDate = (Histogram) groupByCountryBucket.getAggregations().asMap().get("group_by_join_date");
            Iterator<org.elasticsearch.search.aggregations.bucket.histogram.Histogram.Bucket> groupByJoinDateBucketIterator = (Iterator<Histogram.Bucket>) groupByJoinDate.getBuckets().iterator();
            while(groupByJoinDateBucketIterator.hasNext()) {
                org.elasticsearch.search.aggregations.bucket.histogram.Histogram.Bucket groupByJoinDateBucket = groupByJoinDateBucketIterator.next();
                System.out.println(groupByJoinDateBucket.getKey() + ":" +groupByJoinDateBucket.getDocCount());

                Avg avg = (Avg) groupByJoinDateBucket.getAggregations().asMap().get("avg_salary");
                System.out.println(avg.getValue());
            }
        }
    }

    /**
     * （1）首先按照country国家来进行分组
     * （2）然后在每个country分组内，再按照入职年限进行分组
     * （3）最后计算每个分组内的平均薪资
     * GET /company/emp/_search
     * {
     *   "aggs" : {
     *     "group_by_country" : {
     *       "terms" : { "field" : "country" },
     *       "aggs": {
     *         "group_by_join_date": {
     *           "date_histogram": {
     *             "field": "joinDate",
     *             "interval": "year"
     *           },
     *           "aggs": {
     *             "avg_salary": {
     *               "avg": {
     *                 "field": "salary"
     *               }
     *             }
     *           }
     *         }
     *       }
     *     }
     *   }
     * }
     */
    @Test
    public void agg2(){
        //1.按照 country 国家 分组
        TermsAggregationBuilder termsAggregationBuilder = AggregationBuilders.terms("group_by_country")
                .field("country")
                //再按照入职年限 joinDate 分组
                .subAggregation(
                        AggregationBuilders
                                .dateHistogram("group_by_join_date")
                                .field("joinDate")
                                //按年分组
                                .dateHistogramInterval(DateHistogramInterval.YEAR)
                                //计算每组平均值
                                .subAggregation(AggregationBuilders.avg("avg_salary").field("salary"))
                );

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withIndices("company")
                .withTypes("emp")
                .addAggregation(termsAggregationBuilder)
                .build();
        SearchResponse searchResponse = elasticsearchTemplate.query(searchQuery, new ResultsExtractor<SearchResponse>() {
            @Override
            public SearchResponse extract(SearchResponse searchResponse) {
                return searchResponse;
            }
        });

        StringTerms countryTerms = (StringTerms) searchResponse.getAggregations().asMap().get("group_by_country");

        //第一层,按照country分组的
        Iterator<StringTerms.Bucket> countryTermsiterator = countryTerms.getBuckets().iterator();

        while (countryTermsiterator.hasNext()){
            System.out.println("---------------------------A Country Start -----------------------------------------");
            StringTerms.Bucket countryBucket = countryTermsiterator.next();
            //打印 country 分组的
            System.out.println(countryBucket.getKey() + ":" + countryBucket.getDocCount());

            Histogram histogram = (Histogram) countryBucket.getAggregations().asMap().get("group_by_join_date");
            Iterator<? extends Histogram.Bucket> joinDateHistogram = histogram.getBuckets().iterator();
            while (joinDateHistogram.hasNext()){
                Histogram.Bucket histogramBucket = joinDateHistogram.next();
                //打印日期直方分组的
                System.out.println(histogramBucket.getKey() + ":" + histogramBucket.getDocCount());

                Avg avg = (Avg) histogramBucket.getAggregations().asMap().get("avg_salary");
                //打印平均值
                System.out.println(avg.getName() + ":" + avg.getValueAsString());
            }
            System.out.println("---------------------------A Country End -----------------------------------------");
        }

    }

    /**
     * 按照国家分组
     * GET /company/emp/_search
     * {
     *   "aggs": {
     *     "group_by_date": {
     *       "terms": { "field": "country" }
     *     }
     *   }
     * }
     */
    @Test
    public void aggByCountry(){

        TermsAggregationBuilder aggregationBuilder = AggregationBuilders.terms("group_by_country").field("country");

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withIndices("company")
                .withTypes("emp")
                .addAggregation(aggregationBuilder)
                .build();

        SearchResponse searchResponse = elasticsearchTemplate.query(searchQuery, new ResultsExtractor<SearchResponse>() {
            @Override
            public SearchResponse extract(SearchResponse searchResponse) {
                return searchResponse;
            }
        });

        StringTerms aggByCountry = (StringTerms) searchResponse.getAggregations().asMap().get("group_by_country");
        Iterator<StringTerms.Bucket> aggByCountryIterator = aggByCountry.getBuckets().iterator();

        while (aggByCountryIterator.hasNext()){
            StringTerms.Bucket bucket = aggByCountryIterator.next();
            //打印记录
            System.out.println(bucket.getKey() + ":" + bucket.getDocCount());
        }
    }

    /**
     * 按照入职时间分组
     * GET /company/emp/_search
     * {
     *   "aggs": {
     *     "group_by_date": {
     *       "terms": { "field": "joinDate" }
     *     }
     *   }
     * }
     */
    @Test
    public void aggByJoinDate(){
        TermsAggregationBuilder termsAggregationBuilder = AggregationBuilders.terms("group_by_joinDate").field("joinDate");
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withIndices("company")
                .withTypes("emp")
                .addAggregation(termsAggregationBuilder)
                .build();

        SearchResponse searchResponse = elasticsearchTemplate.query(searchQuery, new ResultsExtractor<SearchResponse>() {
            @Override
            public SearchResponse extract(SearchResponse searchResponse) {
                return searchResponse;
            }
        });

        LongTerms longTerms = (LongTerms) searchResponse.getAggregations().asMap().get("group_by_joinDate");

        longTerms.getBuckets().forEach(bucket -> System.out.println(
                bucket.getKey() + ":" + bucket.getDocCount()
        ));
    }

    /**
     * 计算所有平均数
     * {
     *    "aggs" : {
     *           "avg_price" : {
     *               "avg" : { "field" : "salary" }
     *       }
     *    }
     * }
     */
    @Test
    public void aggAvg(){

        AvgAggregationBuilder aggregationBuilder = AggregationBuilders.avg("all_svg").field("salary");
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withIndices("company")
                .withTypes("emp")
                .addAggregation(aggregationBuilder)
                .build();
        SearchResponse searchResponse = elasticsearchTemplate.query(searchQuery, new ResultsExtractor<SearchResponse>() {
            @Override
            public SearchResponse extract(SearchResponse searchResponse) {
                return searchResponse;
            }
        });

        InternalAvg internalAvg = (InternalAvg) searchResponse.getAggregations().asMap().get("all_svg");
        System.out.println("avg is : " + internalAvg.getValue());
    }

    public String getId() {
        return UUID.randomUUID().toString();
    }

}
