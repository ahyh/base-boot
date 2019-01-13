package com.yh.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
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
    @Before(value = "@annotation(com.yh.annotations.MethodAnnotation)")
    public void before(JoinPoint point) {

    }

    /**
     * 后置通知
     */
    @After(value = "@annotation(com.yh.annotations.MethodAnnotation)")
    public void after(JoinPoint point) {

    }

    /**
     * 返回通知
     *
     * @param point  切点
     * @param result 返回结果
     */
    @AfterReturning(value = "@annotation(com.yh.annotations.MethodAnnotation)", returning = "result")
    public void afterReturning(JoinPoint point, Object result) {

    }

    /**
     * 异常通知
     *
     * @param point 切点
     * @param e     异常信息
     */
    @AfterThrowing(value = "@annotation(com.yh.annotations.MethodAnnotation)", throwing = "e")
    public void afterThrowing(JoinPoint point, Throwable e) {

    }

    /**
     * 环绕通知
     *
     * @param joinPoint 切点
     * @return 方法执行结果
     * @throws Throwable 异常信息
     */
    @Around(value = "@annotation(com.yh.annotations.MethodAnnotation)")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        return joinPoint.proceed();
    }
}
