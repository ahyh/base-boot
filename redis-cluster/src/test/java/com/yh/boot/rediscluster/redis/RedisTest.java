package com.yh.boot.rediscluster.redis;

import com.yh.boot.rediscluster.RedisClusterApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisTest extends RedisClusterApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testSet() {
        String key = "key";
        String value = "value";
        redisTemplate.opsForValue().set(key,value);
    }

    @Test
    public void testGet(){
        String key = "key";
        Object value = redisTemplate.opsForValue().get(key);
        System.out.println(value.getClass().getSimpleName());
        System.out.println(value.toString());
    }
}
