package com.yh.boot.redis.cluster.redis;

import com.yh.boot.redis.cluster.RedisClusterApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisTest extends RedisClusterApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testSet() {
        String key = "key1";
        String value = "value";
        redisTemplate.opsForValue().set(key,value);
    }

    @Test
    public void testGet(){
        String key = "key1";
        Object value = redisTemplate.opsForValue().get(key);
        System.out.println(value.getClass().getSimpleName());
        System.out.println(value.toString());
    }

    @Test
    public void testLogin(){
        String id = "666";
        String key = "loginUserId_"+id;
        Object o = redisTemplate.opsForValue().get(key);
        System.out.println(o);
    }
}
