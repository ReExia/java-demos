package com.orm;

import com.demo.orm.core.template.HandlerTemplate;
import com.demo.orm.core.template.mysql.MysqlTemplate;
import com.demo.orm.example.entity.Account;
import com.demo.orm.example.entity.BookInfo;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class TestMysqlTemplate {

    @Test
    public void test1(){

        MysqlTemplate mysqlTemplate = new MysqlTemplate();

        BookInfo bookInfo = new BookInfo();
        //bookInfo.setBookId(223);
        bookInfo.setBookAuthor("");
        bookInfo.setBookPrice(22.3);

        mysqlTemplate.save(bookInfo);
    }

    @Test
    public void test2(){
        MysqlTemplate mysqlTemplate = new MysqlTemplate();

        BookInfo bookInfo = new BookInfo();
        bookInfo.setBookId(2);
        //bookInfo.setBookAuthor(null);
        //bookInfo.setBookPrice(22.3);

        int delete = mysqlTemplate.delete(bookInfo);
        System.out.println(delete);

    }

    @Test
    public void test3(){
        HandlerTemplate mysqlTemplate = new MysqlTemplate();

        BookInfo bookInfo = new BookInfo();
        bookInfo.setBookId(20);
        bookInfo.setBookAuthor("jojx");
        bookInfo.setBookPrice(222121.3);

        int update = mysqlTemplate.update(bookInfo);
        int update2 = mysqlTemplate.update(bookInfo);


        System.out.println(update);
    }

    @Test
    public void test4(){
        HandlerTemplate mysqlTemplate = new MysqlTemplate();

        /*BookInfo bookInfo = new BookInfo();
        bookInfo.setBookId(20);
        bookInfo.setBookAuthor("zjamsah");
        bookInfo.setBookPrice(222121.3);
        mysqlTemplate.queryForList(bookInfo);*/

        List<BookInfo> bookInfos = mysqlTemplate.queryForList(new BookInfo());

        bookInfos.forEach(bookInfo -> System.out.println(bookInfo.toString()));
    }

    @Test
    public void test5(){
        HandlerTemplate mysqlTemplate = new MysqlTemplate();

        Account account = new Account();
        account.setMoney(1000);
        account.setUsername("李四");
        account.setTranTime(new Date());

        mysqlTemplate.save(account);
    }

    @Test
    public void test6(){
        HandlerTemplate mysqlTemplate = new MysqlTemplate();
        List<Account> accounts = mysqlTemplate.queryAll(new Account());
        List<Account> accounts2 = mysqlTemplate.queryAll(new Account());
        accounts.forEach(account -> System.out.println(account.toString()));
    }


}
