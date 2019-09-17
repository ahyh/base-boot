package com.yh.spring.demo.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * MethodAnnotation注解对应的切面
 *
 * @author yanhuan
 */
@Aspect
@Component
public class MethodAspect {

    private static final Logger logger = LoggerFactory.getLogger(MethodAspect.class);

    /**
     * 切使用MethodAnnotation注解修饰的方法
     * 前置通知
     */
    @Before(value = "@annotation(com.yh.spring.demo.annotations.MethodAnnotation)")
    public void before(JoinPoint point) {
        System.out.println("Transactional before");
    }

    /**
     * 后置通知
     */
    @After(value = "@annotation(com.yh.spring.demo.annotations.MethodAnnotation)")
    public void after(JoinPoint point) {
        System.out.println("Transactional after");
    }

    /**
     * 返回通知
     *
     * @param point  切点
     * @param result 返回结果
     */
    @AfterReturning(value = "@annotation(com.yh.spring.demo.annotations.MethodAnnotation)", returning = "result")
    public void afterReturning(JoinPoint point, Object result) {
        System.out.println("Transactional afterReturning");
    }

    /**
     * 异常通知
     *
     * @param point 切点
     * @param e     异常信息
     */
    @AfterThrowing(value = "@annotation(com.yh.spring.demo.annotations.MethodAnnotation)", throwing = "e")
    public void afterThrowing(JoinPoint point, Throwable e) {
        System.out.println("Transactional after throwing");
    }

    /**
     * 环绕通知
     *
     * @param joinPoint 切点
     * @return 方法执行结果
     * @throws Throwable 异常信息
     */
    @Around(value = "@annotation(com.yh.spring.demo.annotations.MethodAnnotation)")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Transactional around before");
        Object proceed = joinPoint.proceed();
        System.out.println("Transactional around after");
        return proceed;
    }
}
