package com.example.core.config.dataSource.pool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;

/**
 * 封装数据库连接
 */
public class WrapSqlConnection {

    private final static Logger LOGGER = LoggerFactory.getLogger(WrapSqlConnection.class);

    private Connection connection;

    private boolean isBusy;

    public WrapSqlConnection(Connection connection, Boolean busyFlag){
        this.connection = connection;
        this.isBusy = busyFlag;
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
