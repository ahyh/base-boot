package com.yh.spring.demo.proxy.redis.components;

import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RedisMapperProxyHolder {

    private static final Map<Class<?>, Object> MAPPER_PROXY_MAP = new ConcurrentHashMap<>();

    public static Object get(Class<?> mapperInterface) {
        if (MAPPER_PROXY_MAP.get(mapperInterface) == null) {
            Object o = Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[]{mapperInterface}, new RedisMapperProxyInvocationHandler());
            MAPPER_PROXY_MAP.put(mapperInterface, o);
        }
        return MAPPER_PROXY_MAP.get(mapperInterface);
    }
}
