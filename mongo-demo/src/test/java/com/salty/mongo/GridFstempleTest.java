package com.salty.mongo;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.GridFSFindIterable;
import com.mongodb.client.gridfs.model.GridFSDownloadOptions;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.gridfs.GridFS;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import com.mongodb.BasicDBObject;
import com.mongodb.gridfs.GridFSDBFile;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class GridFstempleTest {

    @Autowired
    GridFsTemplate gridFsTemplate;

    @Autowired
    GridFsOperations operations;
    
    @Autowired
    MongoDbFactory mongoDbFactory;

    @Test
    public void testSave(){
        File file = new File("/home/setsuna/IdeaProjects/mongo/src/main/resources/static/img/psb.jpeg");
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            gridFsTemplate.store(fileInputStream, file.getName(), new BasicDBObject("user_id", 101));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFind(){
        //GridFSFile id = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is("5abc8af0007ae22f9e6ed1f0")));
        DB legacyDb =mongoDbFactory.getLegacyDb();
        DBObject object = new BasicDBObject().append("_id",new ObjectId("5abc7769007ae216489078c7"));
        GridFS gridFS = new GridFS(legacyDb);
        GridFSDBFile fsdbFile = gridFS.findOne(object);
        try {
            fsdbFile.writeTo("/home/setsuna/test.jpeg");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
