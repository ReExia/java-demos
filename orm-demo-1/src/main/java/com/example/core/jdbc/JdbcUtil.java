package com.example.core.jdbc;

import com.example.core.config.dataSource.pool.ConnectionPool;
import com.example.core.config.dataSource.pool.SqlPoolManager;
import com.example.core.config.dataSource.pool.WrapSqlConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 执行sql语句
 */
public class JdbcUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcUtil.class);

    private static ThreadLocal<WrapSqlConnection> threadLocal = new ThreadLocal<WrapSqlConnection>();

    /**
     * 获取数据库连接
     *
     * @return
     */
    public final static WrapSqlConnection getPoolConnection() {
        WrapSqlConnection wrapSqlConnection = threadLocal.get();
        //直到能获取到数据库连接再出循环
        while (wrapSqlConnection == null) {

            wrapSqlConnection = SqlPoolManager.getInstance().getWrapConnection();

            //如果获取不到连接,等待3秒
            if (wrapSqlConnection == null){
                try {
                    LOGGER.info("繁忙等待连接...");
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (wrapSqlConnection != null) {
                threadLocal.set(wrapSqlConnection);
                LOGGER.info("threadLocal set connection => {}", wrapSqlConnection.getConnection().toString());
            }
        }
        return wrapSqlConnection;
    }

    /**
     * 执行简单SQL的DML(INSERT,UPDATE,DELETE)操作
     *
     * @return
     */
    public final static int executeUpdate(String sql, Object... paramters) {

        int row = 0;

        WrapSqlConnection wrapSqlConnection = null;
        Connection connection = null;
        PreparedStatement pstm = null;
        try {
            //初始化连接资源等
            wrapSqlConnection = getPoolConnection();
            wrapSqlConnection.setBusy(true);

            connection = wrapSqlConnection.getConnection();
            pstm = connection.prepareStatement(sql);
            setParameters(pstm, paramters);
            //row = pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pstm != null){
                try {
                    pstm.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            wrapSqlConnection.setBusy(false);
        }
        return row;
    }

    /**
     * 设置参数
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


}
