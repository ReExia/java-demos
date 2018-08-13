package com.demo.orm.core.config;


/**
 * @author setsuna
 * 单例读取配置文件
 */
public class PropertiesConfiguration {

    private static class Configuration{
        private static final PropertiesPlaceHolder CONFIG = new PropertiesPlaceHolder();
    }

    public static PropertiesPlaceHolder getInstance(){
        return Configuration.CONFIG;
    }

}
