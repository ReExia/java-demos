package com.demo.orm.core.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author setsuna
 * 用于标注主键
 */
@Target(ElementType.FIELD) //可标记属性
@Retention(RetentionPolicy.RUNTIME) //运行时生效
public @interface PK {
    String value();
}
