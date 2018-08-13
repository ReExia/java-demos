package com.example.core.config.dataSource.pool;

import com.example.core.config.dataSource.pool.ConnectionPool;

/**
 * 数据库连接池单例
 */
public class SqlPoolManager {

    private static class Pool {
         private static final ConnectionPool INSTANCE = new ConnectionPool();
    }

    public static ConnectionPool getInstance(){
        return Pool.INSTANCE;
    }

}
