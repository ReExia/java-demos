package com.example.core.jdbc.template.mysql;

import com.example.core.convertor.EntityToParamtersConvertor;
import com.example.core.jdbc.JdbcUtil;
import com.example.core.jdbc.template.BaseTemplate;

import java.util.List;
import java.util.Map;

/**
 * sql查询template
 */
public class MysqlTemplate extends BaseTemplate {

    @Override
    public <T> List<T> queryAll() {
        return null;
    }

    @Override
    public <T> List<T> queryByColumns(T entity) {
        return null;
    }

    @Override
    public <T> void insert(T entity) {



    }

    @Override
    public <T> void updateById(T entity) {

    }

    @Override
    public <T> void updateByIdSelective(T entity) {

    }

    @Override
    public <T> void deleteById(T entity) {

    }

    @Override
    public <T> void deleteByColumns(T entity) {

    }
}
