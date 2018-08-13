package com.demo.orm.core.utils;

import com.demo.orm.core.annotation.Column;

import java.lang.reflect.Field;

public class ReflectUtil {

    /**
     * 获取属性映射字段名称
     */
    public static String getFieldMappingName(Field field) {
        // 获取所有的字段名称默认就是属性名称
        String fieldName = field.getName();

        // 判断是否存在Column注解配置
        Column column = field.getAnnotation(Column.class);

        if (column != null) {
            fieldName = column.value();
        }

        return fieldName;
    }

}
