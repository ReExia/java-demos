package com.orm;

import com.demo.orm.core.enums.DriverInfoEnum;
import org.junit.Test;

public class TestGetProperty {


    /**
     * 测试获取配置文件值
     */
    @Test
    public void test1(){

        String value1 = DriverInfoEnum.DRIVER_CLASS.getValue();
        String value2 = DriverInfoEnum.PASSWORD.getValue();

        String value3 = DriverInfoEnum.URL.getValue();
        String value4 = DriverInfoEnum.USER_NAME.getValue();

        System.out.println(value1);
        System.out.println(value2);
        System.out.println(value3);
        System.out.println(value4);
    }

}
