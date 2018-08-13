package com.demo.orm.core.pools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;

/**
 * @author setsuna
 * 封装连接池中连接对象
 */
public class PoolConnection {

    private Logger LOGGER = LoggerFactory.getLogger(PoolConnection.class);

    /**
     * 连接对象
     */
    private Connection connection;

    /**
     * 连接的状态 true繁忙 false 空闲
     */
    private boolean isBusy;

    /**
     * 构造函数
     * @param connection
     * @param isBusy
     */
    public PoolConnection(Connection connection, boolean isBusy){
        this.connection = connection;
        this.isBusy = isBusy;
    }

    /**
     * 归还数据库连接
     */
    public synchronized void close(){
        LOGGER.info("释放连接....");
        isBusy = false;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }
}
