package com.example.core.config.constant;

import com.example.core.config.manager.ProPertyHolderManager;

/**
 * sql jdbc配置
 */
public enum SqlPropertyEnum {


    DRIVER_CLASS{
        @Override
        public String getStringValue() {
            return ProPertyHolderManager.getInstance().getPropertyByKey("jdbc.driver_class");
        }
    },

    URL{
        @Override
        public String getStringValue() {
            return ProPertyHolderManager.getInstance().getPropertyByKey("jdbc.url");
        }
    },

    USER_NAME{
        @Override
        public String getStringValue() {
            return ProPertyHolderManager.getInstance().getPropertyByKey("jdbc.username");
        }
    },

    PASSWORD{
        @Override
        public String getStringValue() {
            return ProPertyHolderManager.getInstance().getPropertyByKey("jdbc.password");
        }
    };

    public abstract String getStringValue();

}
