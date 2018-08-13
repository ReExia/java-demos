package com.demo.orm.core.config;

import com.demo.orm.core.constant.ConfigConstant;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * @author setsuna
 *
 * 加载配置文件
 */
public class PropertiesPlaceHolder extends Properties {

    private static final long serialVersionUID = 1L;


    /**
     * 加载配置文件
     */
    public PropertiesPlaceHolder() {
        String path = ConfigConstant.PROPERTIES_CONFIG_PATH.getPath();

        //jdk8 这样写可以自动关闭资源，不需要手动写close了
        try (
                InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(path);
            ) {

            this.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getPropertyByKey(String key) {
        return getProperty(key);
    }

    @Override
    public  String toString() {
        return this.hashCode() + "";
    }
}
