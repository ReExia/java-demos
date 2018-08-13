package com.demo.orm.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author setsuna
 * 标记数据库表
 */
@Target(ElementType.TYPE) //标记于类
@Retention(RetentionPolicy.RUNTIME) //运行时生效
public @interface Table {
    String value();
}
