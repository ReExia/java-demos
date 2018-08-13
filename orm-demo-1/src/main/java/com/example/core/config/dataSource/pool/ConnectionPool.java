package com.example.core.config.dataSource.pool;

import com.example.core.config.constant.SqlPoolPropertyEnum;
import com.example.core.config.constant.SqlPropertyEnum;
import com.example.core.exception.MyOrmException;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {


    /*private int initialSize = 3;
    private int increaseSize = 5;
    private int maxSize = 30;
    private int timeOut = 1000;*/

    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionPool.class);

    //锁
    private Lock lock = new ReentrantLock();

    public static Vector<WrapSqlConnection> wrapSqlConnectionVector = new Vector<>();

    public ConnectionPool() {
        init();
        setWrapConnection();
    }

    /**
     * 初始化DriverManager
     */
    private void init() {

        try {
            Driver driver = (Driver) Class.forName(SqlPropertyEnum.DRIVER_CLASS.getStringValue()).newInstance();
            DriverManager.registerDriver(driver);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("加载数据库驱动失败 => {}", e.getMessage());
            //todo 抛出自定义异常
            throw new MyOrmException(String.format("加载数据库驱动失败 => {%s}", e.getMessage()));
        }


    }

    /**
     * 向wrapSqlConnectionVector中加入WrapSqlConnection
     */
    public void setWrapConnection() {

        WrapSqlConnection wrapSqlConnection = null;
        //加锁
        lock.lock();

        int poolSize = Integer.valueOf(SqlPoolPropertyEnum.POOL_SIZE.getStringValue());
        int timeOut = Integer.valueOf(SqlPoolPropertyEnum.TIME_OUT.getStringValue());

        //移除挂掉的连接
        for (WrapSqlConnection wrapConnection : wrapSqlConnectionVector) {
            try {
                if (!wrapConnection.getConnection().isValid(timeOut)) {
                    wrapSqlConnectionVector.remove(wrapConnection);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (CollectionUtils.isEmpty(wrapSqlConnectionVector)) {
            for (int i = wrapSqlConnectionVector.size(); i < poolSize; i++) {
                WrapSqlConnection wrapConnection = createWrapConnection();
                wrapSqlConnectionVector.add(wrapConnection);
            }
        }
        //解锁
        lock.unlock();
    }

    public WrapSqlConnection getWrapConnection(){
        //清理无用连接
        setWrapConnection();

        for (WrapSqlConnection wrapSqlConnection : wrapSqlConnectionVector) {
            if (!wrapSqlConnection.isBusy()){
                LOGGER.info("获取 connection 连接成功 => {}", wrapSqlConnection.getConnection());
                return wrapSqlConnection;
            }
        }
        return null;
    }

    /**
     * 新建连接
     *
     * @return
     */
    public WrapSqlConnection createWrapConnection() {

        WrapSqlConnection wrapSqlConnection = null;

        try {
            Connection connection = getRealSqlConnection();
            wrapSqlConnection = new WrapSqlConnection(connection, false);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return wrapSqlConnection;
        }
    }

    private Connection getRealSqlConnection() throws SQLException {
        String url = SqlPropertyEnum.URL.getStringValue();
        String username = SqlPropertyEnum.USER_NAME.getStringValue();
        String password = SqlPropertyEnum.PASSWORD.getStringValue();
        return DriverManager.getConnection(url, username, password);
    }

}
