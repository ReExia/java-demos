package com.example.mvc.utils;

public class ClassUtils {

    public static boolean isSystemClass(Class<?> cl){
        System.out.println(cl.getClassLoader());
        return cl != null && cl.getClassLoader() == null;
    }

}
