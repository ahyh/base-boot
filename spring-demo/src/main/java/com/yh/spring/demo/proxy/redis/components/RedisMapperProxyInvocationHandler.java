package com.yh.spring.demo.proxy.redis.components;

import com.yh.spring.demo.common.utils.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ClassUtils;

import java.beans.Introspector;
import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * RedisMapper代理执行逻辑
 *
 * @author yanhuan
 */
public class RedisMapperProxyInvocationHandler implements InvocationHandler, Serializable {

    private static final Logger logger = LoggerFactory.getLogger(RedisMapperProxyInvocationHandler.class);

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RedisMapperMethodInfo info = RedisMapperInfoContainer.get(method.toString());
        //没有info,说明没有加注解，则直接走MybatisMapper逻辑
        if (info == null) {
            //execute proxy of mybaits
            Object mybatisProxy = SpringUtil.getBean(buildMybatisName(method));
            return method.invoke(mybatisProxy, args);
        }
        System.out.println(info.getMethod());
        return info.getReturnType().newInstance();
    }

    private String buildMethodFullName(Method method) {
        String className = method.getDeclaringClass().getName();
        return className.concat(".").concat(method.getName());
    }

    private String buildMybatisName(Method method) {
        String shortClassName = ClassUtils.getShortName(method.getDeclaringClass());
        return Introspector.decapitalize(shortClassName);
    }

}
