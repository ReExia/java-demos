package com.example.testaop.aops;

import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class ServiceAspect1 {

    /**
     * 在被代理的对象之前调用
     */
    @Before("execution(* com.example.testaop.service.*.*(..))")
    public void before(){
        System.out.println("before com.example.testaop.service ...");
    }

    /**
     * 在被代理的对象之后调用
     */
    @After("execution(* com.example.testaop.service.*.*(..))")
    public void after(){
        System.out.println("after com.example.testaop.service ...");
    }

    /**
     * 在被代理的对象返回之后调用
     */
    @AfterReturning("execution(* com.example.testaop.service.*.*(..))")
    public void AfterReturning(){
        System.out.println("afterReturning com.example.testaop.service ...");
    }

    /**
     * 在被代理的对象抛出异常之后调用
     */
    @AfterThrowing("execution(* com.example.testaop.service.*.*(..))")
    public void AfterThrowing(){
        System.out.println("AfterThrowing com.example.testaop.service ...");
    }

    /**
     * 环绕通知
     * 如果开启会覆盖before
     */
    /*@Around("execution(* com.example.testaop.service.*.*(..))")
    public void Around(){
        System.out.println("around com.example.testaop.service ...");
    }*/

}
