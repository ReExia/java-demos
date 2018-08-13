package com.example.core.config.constant;

import com.example.core.config.manager.ProPertyHolderManager;

public enum SqlPoolPropertyEnum {

    POOL_SIZE{
        @Override
        public String getStringValue() {
            return ProPertyHolderManager.getInstance().getPropertyByKey("poolSize");
        }
    },

    TIME_OUT{
        @Override
        public String getStringValue() {
            return ProPertyHolderManager.getInstance().getPropertyByKey("timeout");
        }
    };

    public abstract String getStringValue();

}
