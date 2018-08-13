package com.demo.orm.core.pools;

import com.demo.orm.core.enums.DriverInfoEnum;
import com.demo.orm.core.enums.PoolEnum;
import com.demo.orm.core.exception.MyOrmException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

/**
 * @author setsuna
 * 数据源连池
 */
public class DataSourcePool extends AbstractDataSourcePool{

    private final static Logger LOGGER = LoggerFactory.getLogger(DataSourcePool.class);

    /**
     * 连接池默认属性
     */
    private int initialSize = 3;
    private int increaseSize = 5;
    private int maxSize = 30;
    private int timeOut = 1000;

    /**
     * 锁
     */
    private Object lock = new Object();

    /**
     * 存储数据库连接
     */
    public static Vector<PoolConnection> poolConnections = new Vector<>();

    /**
     * 构造函数,初始化连接池
     */
    public DataSourcePool(){
        init();
    }

    /**
     * 初始化连接信息
     * @throws MyOrmException
     */
    public void init() throws MyOrmException{

        if (StringUtils.isNotBlank(PoolEnum.INITIAL_SIZE.getStringValue())){
            initialSize = Integer.parseInt(PoolEnum.INITIAL_SIZE.getStringValue());
        }

        if (StringUtils.isNotBlank(PoolEnum.INCREASE_SIZE.getStringValue())){
            increaseSize = Integer.parseInt(PoolEnum.INCREASE_SIZE.getStringValue());
        }

        if (StringUtils.isNotBlank(PoolEnum.MAX_SIZE.getStringValue())){
            maxSize = Integer.parseInt(PoolEnum.MAX_SIZE.getStringValue());
        }

        if (StringUtils.isNotBlank(PoolEnum.TIME_OUT.getStringValue())){
            timeOut = Integer.parseInt(PoolEnum.TIME_OUT.getStringValue());
        }

        if (initialSize < 0) {
            throw new MyOrmException("初始化连接数必须大于0");
        }

        if (increaseSize < 0) {
            throw new MyOrmException("连接数增长数必须大于0");
        }

        if (maxSize < 0) {
            throw new MyOrmException("最大连接数必须大于0");
        }

        if (timeOut < 0) {
            throw new MyOrmException("连接超时时间必须大于0");
        }

        try {
            Driver driver = (Driver) Class.forName(DriverInfoEnum.DRIVER_CLASS.getValue()).newInstance();
            DriverManager.registerDriver(driver);
        } catch (Exception e) {
            //e.printStackTrace();
            throw new MyOrmException("加载数据库的驱动失败，信息为:" + e.getMessage());
        }
    }

    /**
     * 获取连接池中的连接
     * @return
     */
    @Override
    public PoolConnection getPoolConnection(){

        PoolConnection poolConnection = null;

        synchronized (lock){

            /**
             * 连接池为空
             */
            if (CollectionUtils.isEmpty(poolConnections)){
                LOGGER.info("获取连接中连接失败,连接池为空!");
                //创建连接
                createConnections(initialSize);
            }

            poolConnection = getRealConnection();

            while (poolConnection == null){

                createConnections(increaseSize);
                poolConnection = getRealConnection();
                try {
                    if (poolConnection == null){
                        Thread.sleep(timeOut);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                LOGGER.info("尝试获取连接.....");
            }

        }
        return poolConnection;
    }

    /**
     * 获取数据库连接
     * @return
     */
    private synchronized PoolConnection getRealConnection() {

        for (PoolConnection poolConnection : poolConnections) {

            //查看连接池中是否有闲置连接
            if (!poolConnection.isBusy()){
                Connection connection = poolConnection.getConnection();

                //发送指令给数据库,看是否有回应
                try {

                    //如果连接死掉了,就新建一个连接补上
                    if (!connection.isValid(timeOut)){
                        String url = DriverInfoEnum.URL.getValue();
                        String username = DriverInfoEnum.USER_NAME.getValue();
                        String password = DriverInfoEnum.PASSWORD.getValue();
                        connection = DriverManager.getConnection(url, username, password);
                        poolConnection.setConnection(connection);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                poolConnection.setBusy(true);
                return poolConnection;
            }
        }
        return null;
    }

    @Override
    public void createConnections(int count) {

        if (poolConnections.size() + count <= maxSize){
            for (int i = 0; i < count; i++){
                try {
                    String url = DriverInfoEnum.URL.getValue();
                    String username = DriverInfoEnum.USER_NAME.getValue();
                    String password = DriverInfoEnum.PASSWORD.getValue();
                    Connection connection = DriverManager.getConnection(url, username, password);

                    PoolConnection poolConnection = new PoolConnection(connection, false);
                    poolConnections.add(poolConnection);
                    LOGGER.info("初始化 {} 个连接", i + 1);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }else {
            LOGGER.info("数据库连接已经达到上限，无法创建更多连接了");
        }
    }

    @Override
    protected void finalize() throws Throwable {
        poolConnections.clear();
        poolConnections = null;
        super.finalize();
    }
}
