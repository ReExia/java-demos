package com.example.core.jdbc.template;

import java.util.List;

public abstract class BaseTemplate{

    /**
     * 查询所有记录
     * @param <T>
     * @return
     */
    public abstract <T> List<T> queryAll();

    /**
     * 按字段查询记录
     * @param entity
     * @param <T>
     * @return
     */
    public abstract <T> List<T> queryByColumns(T entity);

    /**
     * 插入记录
     * @param entity
     * @param <T>
     */
    public abstract <T> void insert(T entity);

    /**
     * 按id更新记录,空值也覆盖
     * @param entity
     * @param <T>
     */
    public abstract <T> void updateById(T entity);

    /**
     * 按id更新记录,空值不覆盖
     * @param entity
     * @param <T>
     */
    public abstract <T> void updateByIdSelective(T entity);

    /**
     * 按id删除
     * @param entity
     * @param <T>
     */
    public abstract <T> void deleteById(T entity);

    /**
     * 按字段删除
     * @param entity
     * @param <T>
     */
    public abstract <T> void deleteByColumns(T entity);
}
