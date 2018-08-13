package com.example.elasticsearch.demo02;

import com.example.elasticsearch.entity.Product;
import com.example.elasticsearch.entity.ProductTag;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.LongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.ResultsExtractor;
import org.springframework.data.elasticsearch.core.query.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder.Field;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 1、query string search
 * 2、query DSL
 * 3、query filter
 * 4、full-text search
 * 5、phrase search
 * 6、highlight search
 *
 * term是代表完全匹配，也就是精确查询，搜索前不会再对搜索词进行分词，所以我们的搜索词必须是文档分词集合中的一个
 * TermsBuilder:构造聚合函数
 * AggregationBuilders:创建聚合函数工具类
 * BoolQueryBuilder:拼装连接(查询)条件
 * QueryBuilders:简单的静态工厂”导入静态”使用。主要作用是查询条件(关系),如区间\精确\多值等条件
 * NativeSearchQueryBuilder:将连接条件和聚合函数等组合
 * SearchQuery:生成查询
 * elasticsearchTemplate.query:进行查询
 * Aggregations:Represents a set of computed addAggregation.代表一组添加聚合函数统计后的数据
 * Bucket:满足某个条件(聚合)的文档集合
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductTests {

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void testCreateIndex() {
        elasticsearchTemplate.createIndex(Product.class);
        //elasticsearchTemplate.deleteIndex(Product.class);
    }

    /**
     * 插入两条记录
     */
    @Test
    public void testInsert() throws JsonProcessingException {

        IndexQuery indexQuery = new IndexQuery();
        indexQuery.setIndexName("shop");
        indexQuery.setType("product");

        Product product1 = new Product();
        product1.setProductName("高露洁牙膏6");
        product1.setDesc("高效美白");
        product1.setPrice(40);
        product1.setProducer("高露洁棕榄(Colgate-Palmolive)");
        ProductTag productTag = new ProductTag();
        productTag.setValues(new String[]{"美白", "防蛀牙"});
        product1.setTags(productTag);

        Product product2 = new Product();
        product2.setProductName("中华牙膏7");
        product2.setDesc("草本植物");
        product2.setPrice(33);
        product2.setProducer("中华牙膏品牌");
        ProductTag productTag2 = new ProductTag();
        productTag.setValues(new String[]{"清新"});
        product2.setTags(productTag2);

        //插入第一条
        indexQuery.setSource(objectMapper.writeValueAsString(product1));
        indexQuery.setId("9");
        elasticsearchTemplate.index(indexQuery);

        //插入第二条
        indexQuery.setSource(objectMapper.writeValueAsString(product2));
        indexQuery.setId("10");
        elasticsearchTemplate.index(indexQuery);
    }


    /**
     * 1. query string search
     * public SearchRequestBuilder prepareSearch(String... indices) {
     * return (new SearchRequestBuilder(this, SearchAction.INSTANCE)).setIndices(indices);
     * }
     */
    @Test
    public void testQueryStringSearch() throws ExecutionException, InterruptedException, JsonProcessingException {

        //创建检索条件
        QueryStringQueryBuilder queryStringQueryBuilder = QueryBuilders.queryStringQuery("牙膏");


        /**
         *  设置Indices
         *  源码中的
         *  public SearchRequestBuilder prepareSearch(String... indices) {
         *         return (new SearchRequestBuilder(this, SearchAction.INSTANCE)).setIndices(indices);
         *     }
         */
        SearchRequestBuilder searchRequestBuilder = elasticsearchTemplate.getClient().prepareSearch("shop");

        //searchRequestBuilder.setIndices("shop");
        searchRequestBuilder.setTypes("product");
        searchRequestBuilder.setQuery(queryStringQueryBuilder);
        searchRequestBuilder.setSearchType(SearchType.QUERY_THEN_FETCH);
        searchRequestBuilder.addSort("price", SortOrder.ASC);

        SearchResponse searchResponse = searchRequestBuilder.execute().actionGet();
        SearchHits hits = searchResponse.getHits();

        for (SearchHit hit : hits) {
            Map<String, Object> source = hit.getSource();
            String s = objectMapper.writeValueAsString(source);
            System.out.println(s);
        }
    }

    @Test
    public void testQueryStringSearch2() {
        QueryBuilder queryBuilder = QueryBuilders.queryStringQuery("牙膏");

        FieldSortBuilder fieldSortBuilder = SortBuilders
                .fieldSort("price").order(SortOrder.ASC);

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .withSort(fieldSortBuilder)
                .build();
        List<Product> products = elasticsearchTemplate.queryForList(searchQuery, Product.class);
        products.forEach(System.out::println);
    }


    /**
     * 2、query DSL
     * DSL：Domain Specified Language,特定领域的语言
     * http request body：请求体，可以用json的格式来构建查询语法，比较方便，可以构建各种复杂的语法，比query string search肯定强大多了
     */
    @Test
    public void testQueryDsl() throws JsonProcessingException {
        QueryBuilder queryBuilder = QueryBuilders.matchQuery("desc", "高效美白");
        SearchResponse response = elasticsearchTemplate.getClient()
                .prepareSearch("shop")
                .setTypes("product")
                .setQuery(queryBuilder)
                .get();
        SearchHits hits = response.getHits();
        for (SearchHit hit : hits) {
            Map<String, Object> source = hit.getSource();
            String s = objectMapper.writeValueAsString(source);
            System.out.println(s);
        }
    }

    /**
     * 3.query filter
     */
    @Test
    public void testQueryFilter() {

        //大于gt 小于lt
        QueryBuilder queryBuilder = QueryBuilders.rangeQuery("price")
                .from("33")
                .to("40")
                //包含上界
                .includeUpper(true)
                //包含下界
                .includeLower(false);
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withFilter(queryBuilder).build();

        List<Product> products = elasticsearchTemplate.queryForList(searchQuery, Product.class);
        products.forEach(System.out::println);
    }

    /**
     * 4.full-text search（全文检索）
     * 模糊检索
     */
    @Test
    public void testFullTextSearch() {
        QueryStringQueryBuilder queryBuilder = QueryBuilders.queryStringQuery("高");
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(queryBuilder).build();
        List<Product> products = elasticsearchTemplate.queryForList(searchQuery, Product.class);
        products.forEach(System.out::println);
    }

    /**
     * 5、phrase search（短语搜索）
     */
    @Test
    public void testPhraseSearch() {
        MatchPhrasePrefixQueryBuilder queryBuilder = QueryBuilders.matchPhrasePrefixQuery("producer", "中华");
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(queryBuilder).build();
        List<Product> products = elasticsearchTemplate.queryForList(searchQuery, Product.class);
        products.forEach(System.out::println);
    }

    /**
     * 6、highlight search（高亮搜索结果）
     */
    @Test
    public void testHighLightSearch() {

        QueryStringQueryBuilder queryBuilder = QueryBuilders.queryStringQuery("*");
        Field field = new Field("producer");
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .withHighlightFields(new Field[]{field})
                .build();
        List<Product> products = elasticsearchTemplate.queryForList(searchQuery, Product.class);
        products.forEach(System.out::println);
    }

    /**
     * 上述方法返回的直接就是对象,需要返回高亮字段
     */
    @Test
    public void testHightSearch2() throws JsonProcessingException {
        //设置查询条件
        QueryStringQueryBuilder queryBuilder = QueryBuilders.queryStringQuery("*");
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("producer");
        SearchResponse searchResponse = elasticsearchTemplate.getClient()
                .prepareSearch("shop")
                .setQuery(queryBuilder)
                .highlighter(highlightBuilder)
                .get();
        SearchHits hits = searchResponse.getHits();
    }

    /**
     * 第一个分析需求：计算每个tag下的商品数量
     * select * as product_count from product group by tags;
     */
    @Test
    public void testCountProductByTags() throws JsonProcessingException {
        QueryStringQueryBuilder queryBuilder = QueryBuilders.queryStringQuery("*");

        //按照价格分组
        TermsAggregationBuilder termsAggregationBuilder = AggregationBuilders.terms("price_group").field("price");
        //AvgAggregationBuilder termsAggregationBuilder = AggregationBuilders.avg("price_avg").field("price");
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .addAggregation(termsAggregationBuilder)
                .build();
        /**
         * 分组
         */
        Aggregations aggregations = elasticsearchTemplate.query(searchQuery, new ResultsExtractor<Aggregations>() {
            @Override
            public Aggregations extract(SearchResponse response) {
                return response.getAggregations();
            }
        });

        LongTerms longTerms = (LongTerms) aggregations.asMap().get("price_group");

        List<LongTerms.Bucket> buckets = longTerms.getBuckets();

        Map<String, Long> map = new HashMap<>();
        for (LongTerms.Bucket bucket : buckets) {
            map.put(bucket.getKey().toString(), bucket.getDocCount());
        }

        System.out.println(objectMapper.writeValueAsString(map));
    }

    @Test
    public void testMget() {
        MultiGetResponse multiGetItemResponses =
                elasticsearchTemplate.getClient().prepareMultiGet()
                        .add("shop", "product", "1")
                        .add("shop", "product", "2")
                        .get();
        for (MultiGetItemResponse itemResponse : multiGetItemResponses) {
            GetResponse response = itemResponse.getResponse();
            if (response.isExists()) {
                String json = response.getSourceAsString();
                System.out.println(json);
            }
        }
    }
        /**
         *  第四个数据分析需求：计算每个tag下的商品的平均价格，并且按照平均价格降序排序
         */

        /**
         * 第五个数据分析需求：按照指定的价格范围区间进行分组，然后在每组内再按照tag进行分组，最后再计算每组的平均价格
         */

}
