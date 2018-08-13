package com.orm;

import com.demo.orm.core.JdbcUtils;
import org.junit.Test;

public class TestJdbcUtils {

    @Test
    public void test1(){

        String sql  = "INSERT INTO book_info(book_author,book_price) VALUES (?,?)";

        JdbcUtils.executeUpdate(sql, "语文",22.9);
        JdbcUtils.executeUpdate(sql, "语文",22.9);

    }

    @Test
    public void test2(){
        String sql = "DELETE FROM book_info where book_author=?";
        JdbcUtils.executeUpdate(sql,"语文");
    }

}
