package com.demo.orm.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author setsuna
 * 用于标注数据库字段
 */

@Target(ElementType.FIELD) //标注属性
@Retention(RetentionPolicy.RUNTIME) //运行时生效
public @interface Column {
    String value();
}
