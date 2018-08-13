package com.demo.orm.core.template;

import com.demo.orm.core.exception.MyOrmException;

import java.util.List;

/**
 * @author setsuna
 * 为不同的数据库提供模板
 */
public abstract class HandlerTemplate {

    /**
     * 保存实体类
     * @param entity
     * @param <T>
     */
    public abstract <T> void save(T entity) throws MyOrmException;

    /**
     * 删除实体类
     * @param entity
     * @param <T>
     * @return
     * @throws MyOrmException
     */
    public abstract <T> int delete(T entity) throws MyOrmException;

    /**
     * 更新实体类
     * @param entity
     * @param <T>
     * @return
     * @throws MyOrmException
     */
    public abstract <T> int update(T entity) throws MyOrmException;

    /**
     * 返回查询列表
     * @param entity
     * @param <T>
     * @return
     * @throws MyOrmException
     */
    public abstract <T> List<T> queryForList(T entity) throws MyOrmException;

    /**
     * 查询全部
     * @param <T>
     * @return
     * @throws MyOrmException
     */
    public abstract <T> List<T> queryAll(T entity) throws MyOrmException;

}
