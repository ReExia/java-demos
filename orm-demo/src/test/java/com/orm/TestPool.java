package com.orm;

import com.demo.orm.core.JdbcUtils;
import com.demo.orm.core.template.mysql.MysqlTemplate;
import com.demo.orm.example.entity.BookInfo;

public class TestPool {

    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
               for (int i = 0; i < 20; i++){
                   insertBookInfo();
               }
            }
        }).start();


    }

    public static void insertBookInfo(){
        MysqlTemplate mysqlTemplate = new MysqlTemplate();
        BookInfo bookInfo = new BookInfo();
        bookInfo.setBookAuthor("张三");
        bookInfo.setBookPrice(212.1);
        mysqlTemplate.save(bookInfo);
    }

}
