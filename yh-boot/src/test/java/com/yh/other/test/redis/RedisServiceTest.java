package com.yh.other.test.redis;

import com.yh.boot.test.BaseTest;
import com.yh.cache.RedisService;
import com.yh.service.SecondKillService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class RedisServiceTest extends BaseTest {

    @Autowired
    private RedisService redisService;

    @Autowired
    private SecondKillService secondKillService;

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

    @Test
    public void testSadd() {
        String key = "set-test";
        String value1 = "v1";
        String value2 = "v2";
        boolean sadd = redisService.sadd(key, value1, value2);
        System.out.println(sadd);
    }

    @Test
    public void testSetMembers() {
        String key = "set-test";
        Set<Object> objects = redisService.setMembers(key);
        System.out.println(objects);
    }

    @Test
    public void testSismember() {
        String key = "set-test";
        String value = "v1";
        boolean sismember = redisService.sismember(key, value);
        System.out.println(sismember);
    }

    @Test
    public void testskAddSku(){
        String skuId = "sku123456";
        Integer count = 100;
        boolean set = redisService.set("sk:" + skuId + ":count", count);
        Assert.assertTrue(set);
    }

    @Test
    public void testSk() throws Exception {
        String skuId = "sku123456";
        String userId = "chenjian1";
        boolean b = secondKillService.secondKill(skuId, userId);
        System.out.println(b);
    }


}
