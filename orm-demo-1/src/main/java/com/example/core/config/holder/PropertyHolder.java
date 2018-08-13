package com.example.core.config.holder;

import com.example.core.config.constant.PropertyFileLocationConfig;

import java.io.InputStream;
import java.util.Properties;

/**
 * 读取配置文件
 */
public class PropertyHolder extends Properties {

    public PropertyHolder(){
        try (
                InputStream in = this.getClass().getClassLoader().getResourceAsStream(PropertyFileLocationConfig.APPLICATION_CONFIG.getStringValue());

        ){
            this.load(in);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }

    public String getPropertyByKey(String key){
        return getProperty(key);
    }

    @Override
    public  String toString() {
        return this.hashCode() + "";
    }
}
