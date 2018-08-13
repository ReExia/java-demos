package com.demo.orm.core.enums;

import com.demo.orm.core.config.PropertiesPlaceHolder;

/**
 * @author setsuna
 * 连接池枚举
 */
public enum  PoolEnum {

    /**
     * 连接池初始化大小
     */
    INITIAL_SIZE{
        @Override
        public String getStringValue() {
            return new PropertiesPlaceHolder().getPropertyByKey("initialSize");
        }
    },

    /**
     * 新增连接数
     */
    INCREASE_SIZE{
        @Override
        public String getStringValue() {
            return new PropertiesPlaceHolder().getPropertyByKey("increaseSize");
        }
    },

    /**
     * 最大连接数
     */
    MAX_SIZE{
        @Override
        public String getStringValue() {
            return new PropertiesPlaceHolder().getPropertyByKey("maxSize");
        }
    },

    /**
     * 超时时间
     */
    TIME_OUT{
        @Override
        public String getStringValue() {
            return new PropertiesPlaceHolder().getPropertyByKey("timeout");
        }
    };

    public abstract String getStringValue();
}
