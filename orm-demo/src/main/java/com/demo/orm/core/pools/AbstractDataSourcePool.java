package com.demo.orm.core.pools;

/**
 * @author setsuna
 * 数据库连接抽象类
 */
public abstract class AbstractDataSourcePool {

    /**
     * 定义获取连接池中连接的方法
     * @return
     */
    public abstract PoolConnection getPoolConnection();

    /**
     * 创建连接池中连接对象的方法（提供）
     * @param count
     */
    protected abstract void createConnections(int count);

}
