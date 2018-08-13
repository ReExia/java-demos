package com.demo.orm.core.pools;

/**
 * @author setsuna
 * 获取连接池的单例类
 */
public class DatasourcePoolManager {

    private static class Pool{
        private static final AbstractDataSourcePool INSTANCE = new DataSourcePool();
    }

    public static AbstractDataSourcePool getInstance(){
        return Pool.INSTANCE;
    }

}
