package com.example.core.config.manager;

import com.example.core.config.holder.PropertyHolder;

/**
 * 将读取配置文件的类写成单例
 */
public class ProPertyHolderManager {

    private static class Configuration{
        private static final PropertyHolder CONFIG = new PropertyHolder();
    }

    public static PropertyHolder  getInstance(){
        return Configuration.CONFIG;
    }

}
