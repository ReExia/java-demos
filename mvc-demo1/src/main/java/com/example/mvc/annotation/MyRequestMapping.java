package com.example.mvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.invoke.MethodType;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface MyRequestMapping {
    String value() default "";
    MethodType method() default MethodType.NONE;
    public enum MethodType{
        PUT,
        DELETE,
        GET,
        POST,
        UPDATE,
        NONE
    }
}

