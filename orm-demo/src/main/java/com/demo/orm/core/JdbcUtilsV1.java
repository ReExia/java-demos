package com.demo.orm.core;

import com.demo.orm.core.enums.DriverInfoEnum;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author setsuna
 */
public class JdbcUtilsV1 {

    /**
     * 加载sql驱动
     */
    static {
        try {
            Class.forName(DriverInfoEnum.DRIVER_CLASS.getValue());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接
     *
     * @return
     */
    public final static Connection getConnection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DriverInfoEnum.URL.getValue(), DriverInfoEnum.USER_NAME.getValue(), DriverInfoEnum.PASSWORD.getValue());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
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
     * @param sql
     * @param paramters
     * @return
     */
    public final static int executeUpdate(String sql, Object... paramters) {
        //受影响行数，返回0表示执行失败
        int row = 0;
        try (
                Connection connection = getConnection();
                PreparedStatement pstm = connection.prepareStatement(sql);
        ) {
            //设置参数
            setParameters(pstm, paramters);
            //执行sql
            row = pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }

}
