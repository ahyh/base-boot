package com.yh.other.test.redis;

import com.yh.boot.test.BaseTest;
import com.yh.cache.RedisService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class RedisServiceTest extends BaseTest {

    @Autowired
    private RedisService redisService;

    @Test
    public void testSet() {
        String key = "key1";
        String value = "value1";
        boolean set = redisService.set(key, value);
        Assert.assertTrue(set);
    }

    @Test
    public void testGet() {
        String key = "key1";
        Object str = redisService.get(key);
        System.out.println(str);
    }

    @Test
    public void testSetAndGet() throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        redisService.set("map1", map);
        TimeUnit.SECONDS.sleep(2);
        System.out.println(redisService.get("map1"));
    }

    @Test
    public void testExists() {
        System.out.println(redisService.exists("key1"));
    }
}
