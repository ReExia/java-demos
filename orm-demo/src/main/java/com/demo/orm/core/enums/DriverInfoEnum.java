package com.demo.orm.core.enums;

import com.demo.orm.core.config.PropertiesPlaceHolder;

/**
 * @author setsuna
 *
 * 用于读取数据库属性配置
 */
public enum DriverInfoEnum {

    /**
     * 数据库驱动
     */
    DRIVER_CLASS{
        @Override
        public String getValue() {
            return new PropertiesPlaceHolder().getPropertyByKey("jdbc.driver_class");
        }
    },

    /**
     * 数据库连接
     */
    URL{
        @Override
        public String getValue() {
            return new PropertiesPlaceHolder().getPropertyByKey("jdbc.url");
        }
    },

    /**
     * 数据库连接用户名
     */
    USER_NAME{
        @Override
        public String getValue() {
            return new PropertiesPlaceHolder().getPropertyByKey("jdbc.username");
        }
    },

    /**
     * 数据库连接密码
     */
    PASSWORD{
        @Override
        public String getValue() {
            return new PropertiesPlaceHolder().getPropertyByKey("jdbc.password");
        }
    };

    public abstract String getValue();



}
