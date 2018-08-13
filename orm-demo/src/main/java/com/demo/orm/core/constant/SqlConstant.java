package com.demo.orm.core.constant;

/**
 * @author setsuna
 *
 *  Sql常量,提取对象属性时候，用于去除serialVersionUID属性
 */
public enum  SqlConstant {

    //序列化属性
    SERIAL_VERSION_UID("serialVersionUID");

    private String value;

    SqlConstant(String value){
        this.value = value;
    }

    public String getStringValue(){
        return this.value;
    }

}
