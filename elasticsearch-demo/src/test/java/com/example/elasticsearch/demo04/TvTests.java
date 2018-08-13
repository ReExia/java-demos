package com.example.elasticsearch.demo04;

import com.example.elasticsearch.entity.Article;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AbstractAggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.histogram.*;
import org.elasticsearch.search.aggregations.bucket.range.Range;
import org.elasticsearch.search.aggregations.bucket.range.RangeAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.avg.InternalAvg;
import org.elasticsearch.search.aggregations.metrics.max.InternalMax;
import org.elasticsearch.search.aggregations.metrics.min.InternalMin;
import org.elasticsearch.search.aggregations.metrics.min.Min;
import org.elasticsearch.search.aggregations.metrics.sum.InternalSum;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.ResultsExtractor;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.ServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TvTests {

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void testCreateIndex() {

    }

    /**
     * 统计哪种颜色的电视销量最高
     * size 0 是为了去除参与聚合的原始数据，只要结果
     * GET /tvs/sales/_search
     * {
     * "size" : 0,
     * "aggs" : {
     * "popular_colors" : {
     * "terms" : {
     * "field" : "color"
     * }
     * }
     * }
     * }
     */
    @Test
    public void testCountSales() {
        TermsAggregationBuilder termsAggregationBuilder = AggregationBuilders
                //返回的集合名称
                .terms("popular_colors")
                //聚合的字段
                .field("color");
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withIndices("tvs")
                .withTypes("sales")
                .addAggregation(termsAggregationBuilder)
                .build();
        SearchResponse searchResponse = elasticsearchTemplate.query(searchQuery, new ResultsExtractor<SearchResponse>() {
            @Override
            public SearchResponse extract(SearchResponse searchResponse) {
                return searchResponse;
            }
        });

        StringTerms stringTerms = (StringTerms) searchResponse.getAggregations().asMap().get("popular_colors");

        Iterator<StringTerms.Bucket> iterator = stringTerms.getBuckets().iterator();

        while (iterator.hasNext()) {
            StringTerms.Bucket bucket = iterator.next();
            System.out.println(bucket.getKey() + ":" + bucket.getDocCount());
        }
    }

    /**
     * bucket+metric：统计每种颜色电视平均价格
     * "aggs": {
     *    "avg_price": {
     *       "avg": {
     *          "field": "price"
     *       }
     *    }
     * }
     */
    @Test
    public void testAvg() {

        TermsAggregationBuilder termsAggregationBuilder = AggregationBuilders.terms("popular_colors").field("color");

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withIndices("tvs")
                .withTypes("sales")
                .addAggregation(termsAggregationBuilder.subAggregation(
                        AggregationBuilders.avg("avg_price").field("price")
                ))
                .build();

        SearchResponse searchResponse = elasticsearchTemplate.query(searchQuery, new ResultsExtractor<SearchResponse>() {
            @Override
            public SearchResponse extract(SearchResponse searchResponse) {
                return searchResponse;
            }
        });

        StringTerms stringTerms = (StringTerms) searchResponse.getAggregations().asMap().get("popular_colors");

        Iterator<StringTerms.Bucket> iterator = stringTerms.getBuckets().iterator();

        while (iterator.hasNext()) {
            StringTerms.Bucket bucket = iterator.next();
            System.out.println("----------------a group start-------------------");

            System.out.println(bucket.getKey() + ":" + bucket.getDocCount());

            InternalAvg internalAvg = (InternalAvg) bucket.getAggregations().asMap().get("avg_price");
            System.out.println(internalAvg.getName() + ":" + internalAvg.getValue());

            System.out.println("----------------a group end-------------------\n");
        }
    }

    /**
     * 红色电视中的3台长虹的平均价格是多少？
     * 红色电视中的1台小米的平均价格是多少？
     * GET /tvs/sales/_search
     * {
     *   "size": 0,
     *   "aggs": {
     *     "group_by_color": {
     *       "terms": {
     *         "field": "color"
     *       },
     *       "aggs": {
     *         "color_avg_price": {
     *           "avg": {
     *             "field": "price"
     *           }
     *         },
     *         "group_by_brand": {
     *           "terms": {
     *             "field": "brand"
     *           },
     *           "aggs": {
     *             "brand_avg_price": {
     *               "avg": {
     *                 "field": "price"
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
    public void testBrandAvg() {

        TermsAggregationBuilder termsAggregationBuilder = AggregationBuilders.terms("popular_colors").field("color");

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withIndices("tvs")
                .withTypes("sales")
                .addAggregation(termsAggregationBuilder
                        .subAggregation(
                                AggregationBuilders.avg("avg_price").field("price")
                        )
                        .subAggregation(
                                AggregationBuilders.terms("group_by_brand").field("brand")
                                        .subAggregation(
                                                AggregationBuilders.avg("brand_avg_price").field("price")
                                        )
                        )
                )
                .build();

        SearchResponse searchResponse = elasticsearchTemplate.query(searchQuery, new ResultsExtractor<SearchResponse>() {
            @Override
            public SearchResponse extract(SearchResponse searchResponse) {
                return searchResponse;
            }
        });

        StringTerms popularColorsTerms = (StringTerms) searchResponse.getAggregations().asMap().get("popular_colors");

        Iterator<StringTerms.Bucket> popularColorsTermsIterator = popularColorsTerms.getBuckets().iterator();
        while (popularColorsTermsIterator.hasNext()){

            System.out.println("-------------a group start-------------------");

            StringTerms.Bucket popularColorsBucket = popularColorsTermsIterator.next();

            StringTerms groupByBrandTerms = (StringTerms) popularColorsBucket.getAggregations().asMap().get("group_by_brand");

            Iterator<StringTerms.Bucket> groupByBrandIterator = groupByBrandTerms.getBuckets().iterator();
            while (groupByBrandIterator.hasNext()){
                StringTerms.Bucket bucket = groupByBrandIterator.next();
                System.out.println(bucket.getKey() + ":" + bucket.getDocCount());
                InternalAvg avg = (InternalAvg) bucket.getAggregations().asMap().get("brand_avg_price");
                System.out.println(avg.getName() + ":" + avg.getValue());
            }
            System.out.println("-------------a group end-------------------");
        }
    }

    /**
     * 90%的常见的数据分析的操作，metric，无非就是count，avg，max，min，sum
     * GET /tvs/sales/_search
     * {
     *    "size" : 0,
     *    "aggs": {
     *       "group_color": {
     *          "terms": {
     *             "field": "color"
     *          },
     *          "aggs": {
     *             "avg_price": { "avg": { "field": "price" } },
     *             "min_price" : { "min": { "field": "price"} },
     *             "max_price" : { "max": { "field": "price"} },
     *             "sum_price" : { "sum": { "field": "price" } }
     *          }
     *       }
     *    }
     * }
     */
    @Test
    public void testColorSaleSum(){
        TermsAggregationBuilder termsAggregationBuilder = AggregationBuilders.terms("group_color").field("color");

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withIndices("tvs")
                .withTypes("sales")
                .addAggregation(termsAggregationBuilder
                        .subAggregation(AggregationBuilders.avg("avg_price").field("price"))
                        .subAggregation(AggregationBuilders.min("min_price").field("price"))
                        .subAggregation(AggregationBuilders.max("max_price").field("price"))
                        .subAggregation(AggregationBuilders.sum("sum_price").field("price"))
                )
                .build();

        SearchResponse searchResponse = elasticsearchTemplate.query(searchQuery, new ResultsExtractor<SearchResponse>() {
            @Override
            public SearchResponse extract(SearchResponse searchResponse) {
                return searchResponse;
            }
        });

        StringTerms stringTerms = (StringTerms) searchResponse.getAggregations().asMap().get("group_color");
        Iterator<StringTerms.Bucket> iterator = stringTerms.getBuckets().iterator();

        while (iterator.hasNext()){
            System.out.println("------------------a group start--------------------");

            StringTerms.Bucket bucket = iterator.next();

            System.out.println(bucket.getKey() + ":" + bucket.getDocCount());
            InternalAvg avg = (InternalAvg) bucket.getAggregations().asMap().get("avg_price");
            InternalMin min = (InternalMin) bucket.getAggregations().asMap().get("min_price");
            InternalMax max = (InternalMax) bucket.getAggregations().asMap().get("max_price");
            InternalSum sum = (InternalSum) bucket.getAggregations().asMap().get("sum_price");
            System.out.println(avg.getName() + ":" + avg.getValue());
            System.out.println(min.getName() + ":" + min.getValue());
            System.out.println(max.getName() + ":" + max.getValue());
            System.out.println(sum.getName() + ":" + sum.getValue());

            System.out.println("------------------a group end--------------------");
        }
    }

    /**
     * 范围划分,直方图
     * GET /tvs/sales/_search
     * {
     *    "size" : 0,
     *    "aggs":{
     *       "price":{
     *          "histogram":{
     *             "field": "price",
     *             "interval": 2000
     *          },
     *          "aggs":{
     *             "revenue": {
     *                "sum": {
     *                  "field" : "price"
     *                }
     *              }
     *          }
     *       }
     *    }
     * }
     */
    @Test
    public void testHistogram(){

        HistogramAggregationBuilder histogramAggregationBuilder = AggregationBuilders.histogram("range_by_price").field("price").interval(2000);

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withIndices("tvs")
                .withTypes("sales")
                .addAggregation(histogramAggregationBuilder.subAggregation(
                        AggregationBuilders.sum("group_sum").field("price")
                ))
                .build();

        SearchResponse searchResponse = elasticsearchTemplate.query(searchQuery, new ResultsExtractor<SearchResponse>() {
            @Override
            public SearchResponse extract(SearchResponse searchResponse) {
                return searchResponse;
            }
        });

        InternalHistogram histogram = (InternalHistogram) searchResponse.getAggregations().asMap().get("range_by_price");
        Iterator<InternalHistogram.Bucket> iterator = histogram.getBuckets().iterator();
        while (iterator.hasNext()){
            System.out.println("--------------a group start---------------------");

            InternalHistogram.Bucket bucket = iterator.next();
            System.out.println(bucket.getKey() + ":" + bucket.getDocCount());
            InternalSum sum = (InternalSum) bucket.getAggregations().asMap().get("group_sum");
            System.out.println(sum.getName() + ":" + sum.getValue());

            System.out.println("--------------a group end---------------------");
        }
    }

    /**
     *
     * date Histogram 统计每个月电视销量
     * GET /tvs/sales/_search
     * {
     *    "size" : 0,
     *    "aggs": {
     *       "sales": {
     *          "date_histogram": {
     *             "field": "sold_date",
     *             "interval": "month",
     *             "format": "yyyy-MM-dd",
     *             "min_doc_count" : 0,
     *             "extended_bounds" : {
     *                 "min" : "2016-01-01",
     *                 "max" : "2017-12-31"
     *             }
     *          }
     *       }
     *    }
     * }
     */
    @Test
    public void testSalesCount(){
        ExtendedBounds extendedBounds = new ExtendedBounds("2016-01-01","2017-12-31");
        DateHistogramAggregationBuilder dateHistogramAggregationBuilder = AggregationBuilders.dateHistogram("sales_by_month").field("sold_date")
                .dateHistogramInterval(DateHistogramInterval.MONTH)
                //设置统计为0的不忽略,设置为1,那么小于1条的全部忽略
                .minDocCount(0)
                .extendedBounds(extendedBounds);
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withIndices("tvs")
                .withTypes("sales")
                .addAggregation(dateHistogramAggregationBuilder.subAggregation(
                        AggregationBuilders.sum("sales_count").field("price")
                ))
                .build();
        SearchResponse searchResponse = elasticsearchTemplate.query(searchQuery, new ResultsExtractor<SearchResponse>() {
            @Override
            public SearchResponse extract(SearchResponse searchResponse) {
                return searchResponse;
            }
        });

       InternalDateHistogram internalDateHistogram = (InternalDateHistogram) searchResponse.getAggregations().asMap().get("sales_by_month");
        Iterator<InternalDateHistogram.Bucket> iterator = internalDateHistogram.getBuckets().iterator();
        while (iterator.hasNext()){
            System.out.println("------------------a group start------------------------");
            InternalDateHistogram.Bucket bucket = iterator.next();

            System.out.println(bucket.getKeyAsString()+ ":" + bucket.getDocCount());
            System.out.println(bucket.getKey());

            InternalSum sum = (InternalSum) bucket.getAggregations().asMap().get("sales_count");
            System.out.println(sum.getName() + ":" + sum.getValue());

            System.out.println("------------------a group end------------------------");
        }
    }

    /**
     * 统计每季度每个品牌的销售额
     * GET /tvs/sales/_search
     * {
     *   "size": 0,
     *   "aggs": {
     *     "group_by_sold_date": {
     *       "date_histogram": {
     *         "field": "sold_date",
     *         "interval": "quarter",
     *         "format": "yyyy-MM-dd",
     *         "min_doc_count": 0,
     *         "extended_bounds": {
     *           "min": "2016-01-01",
     *           "max": "2017-12-31"
     *         }
     *       },
     *       "aggs": {
     *         "group_by_brand": {
     *           "terms": {
     *             "field": "brand"
     *           },
     *           "aggs": {
     *             "sum_price": {
     *               "sum": {
     *                 "field": "price"
     *               }
     *             }
     *           }
     *         },
     *         "total_sum_price": {
     *           "sum": {
     *             "field": "price"
     *           }
     *         }
     *       }
     *     }
     *   }
     * }
     */
    @Test
    public void testQuarter(){
        ExtendedBounds extendedBounds = new ExtendedBounds("2016-01-01","2017-12-31");
        DateHistogramAggregationBuilder dateHistogramAggregationBuilder = AggregationBuilders.dateHistogram("group_by_quarter")
                .field("sold_date")
                //按季度分组
                .dateHistogramInterval(DateHistogramInterval.QUARTER)
                //设置统计为0的不忽略,设置为1,那么小于1条的全部忽略
                .minDocCount(0)
                .extendedBounds(extendedBounds);
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withIndices("tvs")
                .withTypes("sales")
                .addAggregation(dateHistogramAggregationBuilder.subAggregation(
                        AggregationBuilders.terms("group_by_brand").field("brand").subAggregation(
                                AggregationBuilders.sum("sum_price").field("price")
                        )
                ))
                .build();
        SearchResponse searchResponse = elasticsearchTemplate.query(searchQuery, new ResultsExtractor<SearchResponse>() {
            @Override
            public SearchResponse extract(SearchResponse searchResponse) {
                return searchResponse;
            }
        });

        InternalDateHistogram internalDateHistogram = (InternalDateHistogram) searchResponse.getAggregations().asMap().get("group_by_quarter");

        Iterator<InternalDateHistogram.Bucket> quarterIterator = internalDateHistogram.getBuckets().iterator();

        while (quarterIterator.hasNext()){
            System.out.println("------------------a quarter group start------------------------");

            InternalDateHistogram.Bucket quarterBucket = quarterIterator.next();

            System.out.println(quarterBucket.getKey() + ":" + quarterBucket.getDocCount());

            StringTerms stringTerms = (StringTerms) quarterBucket.getAggregations().asMap().get("group_by_brand");
            Iterator<StringTerms.Bucket> brandIterator = stringTerms.getBuckets().iterator();

            while (brandIterator.hasNext()){
                StringTerms.Bucket bucket = brandIterator.next();
                System.out.println(bucket.getKey() + ":" + bucket.getDocCount());

                InternalSum sum = (InternalSum) bucket.getAggregations().asMap().get("sum_price");
                System.out.println(sum.getName() + ":" + sum.getValue());

            }
            System.out.println("------------------a quarter group end------------------------");
        }
    }

    /**
     * 统计价格超过1200的
     * GET /tvs/sales/_search
     * {
     *   "size": 0,
     *   "query": {
     *     "constant_score": {
     *       "filter": {
     *         "range": {
     *           "price": {
     *             "gte": 1200
     *           }
     *         }
     *       }
     *     }
     *   },
     *   "aggs": {
     *     "avg_price": {
     *       "avg": {
     *         "field": "price"
     *       }
     *     }
     *   }
     * }
     */
    @Test
    public void testRange(){

        QueryBuilder queryBuilder = QueryBuilders.rangeQuery("price").gte(7000).includeLower(true);

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withIndices("tvs")
                .withTypes("sales")
                //.withQuery(queryBuilder)
                .withFilter(queryBuilder)
                .addAggregation(
                        AggregationBuilders.avg("avg_price").field("price")
                )
                .build();
        SearchResponse searchResponse = elasticsearchTemplate.query(searchQuery, new ResultsExtractor<SearchResponse>() {
            @Override
            public SearchResponse extract(SearchResponse searchResponse) {
                return searchResponse;
            }
        });

        InternalAvg avg = (InternalAvg) searchResponse.getAggregations().asMap().get("avg_price");
        System.out.println(avg.getName() + ":" + avg.getValue());
    }



}
