package com.yh.spring.demo.proxy.redis.components;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * RedisMapper相关信息保存容器
 *
 * @author yanhuan
 */
public class RedisMapperInfoContainer {

    private static Map<String, RedisMapperMethodInfo> map = new ConcurrentHashMap<>();

    public static RedisMapperMethodInfo get(String methodFullName) {
        return map.get(methodFullName);
    }

    public static void put(String fullMethodName, RedisMapperMethodInfo redisMapperMethodInfo) {
        map.put(fullMethodName, redisMapperMethodInfo);
    }
}
