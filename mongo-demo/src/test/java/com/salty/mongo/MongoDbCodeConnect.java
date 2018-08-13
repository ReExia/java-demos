package com.salty.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

/**
 * 用代码方式创建mongo链接
 */
public class MongoDbCodeConnect {


    public static void main(String[] args) {
        MongoClientOptions options = MongoClientOptions.builder().connectTimeout(60000).build();
        MongoClient client = new MongoClient(new ServerAddress("localhost", 27017), options);
        MongoTemplate mongoTemplate = new MongoTemplate(new SimpleMongoDbFactory(client, "dumall"));
        mongoTemplate.getCollectionNames().forEach(System.out::println);
    }


}
