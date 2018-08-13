package com.example.testaop.aops;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * ProceedingJoinPoint is only supported for around advice
 */
@Aspect
@Component
@Order(2)
public class ServiceAspect2 {

    @Pointcut("execution(* com.example.testaop.service.*.*(..))")
    public void print(){

    }

    @Around("print()")
    public void around(ProceedingJoinPoint point){
        Object[] args = point.getArgs();

        for (Object arg : args) {
            System.out.println("arg is : " + arg);
        }

    }

}
