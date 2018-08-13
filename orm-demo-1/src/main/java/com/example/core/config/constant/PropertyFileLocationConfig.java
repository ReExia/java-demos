package com.example.core.config.constant;

/**
 * 读取配置文件位置
 */
public enum PropertyFileLocationConfig {

    APPLICATION_CONFIG("application.properties");

    public String value;

    PropertyFileLocationConfig(String value) {
        this.value = value;
    }

    public String getStringValue() {
        return this.value;
    }
}
