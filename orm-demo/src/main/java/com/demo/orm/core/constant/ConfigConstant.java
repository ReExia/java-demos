package com.demo.orm.core.constant;

/**
 * @author setsuna
 *
 * 用于读取配置文件位置
 */
public enum ConfigConstant {

    //配置文件路径
    PROPERTIES_CONFIG_PATH("application.properties");

    private String path;

    ConfigConstant(String path){
        this.path = path;
    }

    public String getPath(){
        return  this.path;
    }

}
