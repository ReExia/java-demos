package com.example.core.convertor;

import com.example.core.annotations.Column;
import com.example.core.annotations.ID;
import com.example.core.annotations.Table;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 提取实体类字段
 */
public class EntityToParamtersConvertor {

    /**
     * @param entity
     * @param <T>
     * @return map
     *   key : table            表名
     *   key : fieldList        数据库字段合集
     *   key : valueFieldList   数据库字段对应值合集
     *   key : idField          主键字段名
     *   key : idValue          主键值
     */
    public final static <T> Map convert(T entity) {

        //存储返回参数
        Map parameterMap = new HashMap();
        //字段链
        List fieldList = new ArrayList();
        //值链
        List valueList = new ArrayList();

        //获取类对象
        Class<?> clazz = entity.getClass();

        //获取表名
        Table table = clazz.getAnnotation(Table.class);

        //获取域
        Field[] fields = clazz.getDeclaredFields();

        //遍历域
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);

            Annotation[] annotations = field.getDeclaredAnnotations();

            if (annotations.length > 1) {
                ID idAnnotation = field.getDeclaredAnnotation(ID.class);
                String id = idAnnotation.value();

                try {
                    parameterMap.put("idField", id);
                    parameterMap.put("idValue", field.get(entity));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            Column columnAnnotation = field.getDeclaredAnnotation(Column.class);
            String column = columnAnnotation.value();

            try {
                fieldList.add(column);
                valueList.add(field.get(entity));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }

        //存储数据
        parameterMap.put("table", table.value());
        parameterMap.put("fieldList",fieldList);
        parameterMap.put("valueFieldList",valueList);

        return parameterMap;
    }

}
