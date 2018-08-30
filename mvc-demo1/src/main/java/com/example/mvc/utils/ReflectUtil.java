package com.example.mvc.utils;

import com.example.mvc.annotation.MyController;
import com.example.mvc.annotation.MyService;

public class ReflectUtil {

    /**
     * 根据类对象获取字母小写别名
     * @param c1
     * @return
     */
    private static String getLowerAlias(Class<?> c1){
        String simpleName = c1.getSimpleName();
        String start = simpleName.substring(0,1).toLowerCase();
        String end = simpleName.substring(1);
        return start + end;
    }

    /**
     * 获取到注解对应的别名
     * @param c1
     * @return
     */
    public static String getAnnotationAlias(Class<?> c1){
        //默认别名
        String aliasName = getLowerAlias(c1);

        if (c1.isAnnotationPresent(MyService.class)){
            MyService myService = c1.getAnnotation(MyService.class);
            if (!"".equals(myService.value())){
                aliasName = myService.value();
            }
        }else if (c1.isAnnotationPresent(MyController.class)){
            MyController myController = c1.getAnnotation(MyController.class);
            if (!"".equals(myController.value())){
                aliasName = myController.value();
            }
        }
        return aliasName;
    }

    /**
     * 定于处理url路径的方法
     * @param url
     * @return
     */
    public static String handleUrl(String url){
        if (url.startsWith("/")){
            return url;
        }else {
            return "/" + url;
        }
    }

    public static void main(String[] args) {
    }
}
