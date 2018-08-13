package com.demo.orm.core;

import com.demo.orm.core.enums.DriverInfoEnum;
import com.demo.orm.core.exception.MyOrmException;
import com.demo.orm.core.pools.AbstractDataSourcePool;
import com.demo.orm.core.pools.DataSourcePool;
import com.demo.orm.core.pools.DatasourcePoolManager;
import com.demo.orm.core.pools.PoolConnection;
import com.demo.orm.core.transaction.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author setsuna
 */
public class JdbcUtils implements Transaction {


    private static final ThreadLocal<PoolConnection> threadLocal = new ThreadLocal<PoolConnection>();

    private final static Logger LOGGER = LoggerFactory.getLogger(JdbcUtils.class);

    /**
     * 获取数据库连接
     *
     * @return
     */
    public final static PoolConnection getPoolConnection() {
        PoolConnection poolConnection = threadLocal.get();

        if (null == poolConnection){
            poolConnection = DatasourcePoolManager.getInstance().getPoolConnection();
            threadLocal.set(poolConnection);
            LOGGER.info("threadLocal set connection => {}", poolConnection.getConnection().toString());

        }

        return poolConnection;
    }


    /**
     * 设置PreparedStatement 的参数
     *
     * @param pstm
     * @param paramters
     */
    public final static void setParameters(PreparedStatement pstm, Object... paramters) {
        try {
            if (paramters.length > 0) {
                for (int i = 0; i < paramters.length; i++) {
                    pstm.setObject(i + 1, paramters[i]);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 执行简单SQL的DML(INSERT,UPDATE,DELETE)操作
     *
     * @param sql
     * @param paramters
     * @return
     */
    public final static int executeUpdate(String sql, Object... paramters) {
        //受影响行数，返回0表示执行失败
        int row = 0;
        PoolConnection poolConnection = null;
        Connection connection = null;
        PreparedStatement pstm = null;
        try {
            poolConnection = getPoolConnection();
            connection = poolConnection.getConnection();
            pstm = connection.prepareStatement(sql);
            //设置参数
            setParameters(pstm, paramters);
            //执行sql
            row = pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (connection != null) {
                    //connection.close();
                    connection = null;
                }
                poolConnection.setBusy(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return row;
    }

    /**
     * 查询操作
     * @param sql
     * @param paramters
     * @return
     */
    public final static List<Map<String, Object>> executeQuery(String sql, Object... paramters) {

        List<Map<String, Object>> table = new ArrayList<Map<String, Object>>();

        ResultSet resultSet = null;
        PoolConnection poolConnection = null;
        Connection connection = null;
        PreparedStatement pstm = null;
        try {

            poolConnection = getPoolConnection();

            connection = poolConnection.getConnection();

            pstm = connection.prepareStatement(sql);
            //设置参数
            setParameters(pstm, paramters);
            //执行sql
            resultSet = pstm.executeQuery();

            ResultSetMetaData metaData = resultSet.getMetaData();
            int colCount = metaData.getColumnCount();

            while (resultSet.next()){
                Map<String, Object> row = new HashMap<>();

                for (int i = 0; i < colCount; i++){
                    //获取列名
                    String colName = metaData.getColumnName(i + 1);
                    // 获取当前列的值
                    Object colValue = resultSet.getObject(colName);

                    row.put(colName, colValue);
                }
                table.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (connection != null) {
                    //connection.close();
                    connection = null;
                }

                poolConnection.setBusy(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return table;
        }
    }

    /**
     * 开启事务
     */
    @Override
    public void begin() {
        PoolConnection poolConnection = threadLocal.get();
        if (null != poolConnection){
            try {
                poolConnection.getConnection().setAutoCommit(false);
            } catch (SQLException e) {
                //e.printStackTrace();
                throw new MyOrmException("事务开启失败,原因为:"+e.getMessage());
            }
        }
    }

    /**
     * 关闭事务
     */
    @Override
    public void commit() {
        PoolConnection poolConnection = threadLocal.get();
        if (null != poolConnection){
            try {
                poolConnection.getConnection().commit();
            } catch (SQLException e) {
                //e.printStackTrace();
                throw new MyOrmException("事务提交失败,原因为:"+e.getMessage());
            }finally {
                // 解除当前线程与连接对象的绑定
                threadLocal.remove();
            }
        }
    }

    /**
     * 回滚
     */
    @Override
    public void rollback() {
        PoolConnection poolConnection = threadLocal.get();
        if (null != poolConnection){
            try {
                poolConnection.getConnection().rollback();
            } catch (SQLException e) {
                //e.printStackTrace();
                throw new MyOrmException("事务回滚失败,原因为:"+e.getMessage());
            }
            finally {
                // 解除当前线程与连接对象的绑定
                threadLocal.remove();
            }
        }
    }
}
