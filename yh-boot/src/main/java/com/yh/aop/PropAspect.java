package com.yh.aop;

import com.yh.annotations.PropAnnotation;
import com.yh.utils.ReflectUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

/**
 * PropAnnoation注解对应的切面
 *
 * @author yanhuan
 */
@Aspect
@Component
public class PropAspect {

    private static final Logger logger = LoggerFactory.getLogger(PropAspect.class);

    /**
     * 切在方法入参中使用PropAnnoation注解修饰的入参
     */
    @Before(value = "execution(* *.* (.., @com.yh.annotations.PropAnnotation (*), ..))")
    public void before(JoinPoint joinPoint) throws Throwable {
        logger.info("PropAspect before");
        Object[] args = joinPoint.getArgs();
        //获取方法
        MethodSignature ms = (MethodSignature) joinPoint.getSignature();
        //获取方法名
        String methodName = ms.getMethod().getName();
        //获取参数类型
        Class<?>[] parameterTypes = ms.getMethod().getParameterTypes();
        //获取方法参数上的注解
        Annotation[][] parameterAnnotations = joinPoint.getTarget().getClass().getMethod(methodName, parameterTypes).getParameterAnnotations();
        if (parameterAnnotations != null) {
            for (int i = 0; i < parameterAnnotations.length; i++) {
                Annotation[] annotations = parameterAnnotations[i];
                for (int j = 0; j < annotations.length; j++) {
                    Annotation annotation = annotations[j];
                    if (annotation.annotationType().equals(PropAnnotation.class)) {
                        PropAnnotation pa = (PropAnnotation) annotation;
                        String createUser = pa.createUser();
                        Object param = args[i];
                        Class<?> parameterType = parameterTypes[i];
                        ReflectUtil.assignField(parameterType, true, param, "createUser", createUser);
                    }
                }
            }
        }
        for (Object a : args) {
            logger.info("arg:{}", a);
        }
    }

}
