package com.example.mvc.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

public class BeanUtils {

    /**
     * 把非自定义类型转换为Object对象
     * @param request
     * @param clazz
     * @return
     * @throws Exception
     */
    public static Object convertHttpServletRequestToSystemClassBean(HttpServletRequest request, Class<?> clazz) throws Exception{

        Enumeration<String> paramterNames = request.getParameterNames();

        Object fieldValue = null;

        while (paramterNames.hasMoreElements()){
            String fieldName = paramterNames.nextElement();
            if ("param".equals(fieldName)){
                continue;
            }

            //定义获取到表单元素名称对应值
            String value = request.getParameter(fieldName);

            if (clazz == Integer.class) {
                fieldValue = Integer.valueOf(value);
            } else if (clazz == int.class) {
                fieldValue = Integer.parseInt(value);
            } else if (clazz == Double.class) {
                fieldValue = Double.valueOf(value);
            } else if (clazz == double.class) {
                fieldValue = Double.parseDouble(value);
            } else if (clazz == BigDecimal.class) {
                fieldValue = new BigDecimal(value);
            } else if (clazz == Date.class) {
                fieldValue = new SimpleDateFormat("yyyy-MM-dd").parse(value);
            } else if (clazz == String.class) {
                fieldValue = value;
            }
            return fieldValue;
        }
            return fieldValue;
    }

    /**
     * 把request对象中提交的表单数据封装为一个对应JavaBean对象
     * @param request
     * @param clazz
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T convertHttpServletRequestToJavaBean(HttpServletRequest request, Class<T> clazz) throws Exception{

        T instance = clazz.newInstance();

        Enumeration<String> parameterNames = request.getParameterNames();

        while (parameterNames.hasMoreElements()){
            String fieldName = parameterNames.nextElement();
            if ("param".equals(fieldName)){
                continue;
            }
            try {

                //根据参数名称获取字段对象
                Field field = clazz.getDeclaredField(fieldName);

                //获取到字段类型
                Class<?> fieldType = field.getType();

                //定义获取到表单元素名称对应的值
                String value = request.getParameter(fieldName);

                Object fieldValue = null;

                if (fieldType == Integer.class) {
                    fieldValue = Integer.valueOf(value);
                } else if (fieldType == int.class){
                    fieldValue = Integer.parseInt(value);
                } else if (fieldType == Double.class){
                    fieldValue = Double.valueOf(value);
                } else if (fieldType == double.class){
                    fieldValue = Double.parseDouble(value);
                } else if (fieldType == BigDecimal.class){
                    fieldValue = new BigDecimal(value);
                } else if (fieldType == Date.class){
                    fieldValue = new SimpleDateFormat("yyyy-MM-dd").parse(value);
                } else if (fieldType == String.class) {
                    fieldValue = value;
                }

                field.setAccessible(true);
                field.set(instance, fieldValue);

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return instance;
    }



}
