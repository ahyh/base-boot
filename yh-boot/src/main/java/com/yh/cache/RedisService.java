package com.yh.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis服务
 *
 * @author yanhuan
 */
@Component
@ConditionalOnProperty(name = "enable-redis-flag", havingValue = "true")
public class RedisService {

    private static final Logger logger = LoggerFactory.getLogger(RedisService.class);

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 写入缓存，对应set命令
     *
     * @param key   缓存键
     * @param value string类型的值
     * @return boolean是否写入缓存成功
     */
    public boolean set(String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            logger.error("RedisService set key:{},value:{},error:{}", key, value, e);
        }
        return result;
    }

    /**
     * 写入缓存设置时效时间，对应set+expire命令
     *
     * @param key        键
     * @param value      值
     * @param expireTime 过期时间
     * @return 是否写入成功
     */
    public boolean setEx(String key, Object value, Long expireTime, TimeUnit unit) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, unit);
            result = true;
        } catch (Exception e) {
            logger.error("RedisService set key:{},value:{},error:{}", key, value, e);
        }
        return result;
    }

    /**
     * 批量删除对应的value
     */
    public void remove(String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * redis事务实现秒杀功能
     *
     * @param skuId  商品id
     * @param userId 用户id
     * @return 是否秒杀成功
     */
    public boolean secondkill(String skuId, String userId) {
        String skuCountKey = "sk:" + skuId + ":count";
        String memberKey = "sk:" + skuId + ":userIds";
        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.watch(skuCountKey);
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        Integer skuCount = Integer.valueOf(operations.get(skuCountKey).toString());
        if (skuCount <= 0) {
            throw new RuntimeException("商品秒杀完了，没有库存了");
        }
        //如果商品还有库存，则开启redis事务
        redisTemplate.multi();
        set.add(memberKey, userId);
        operations.increment(skuCountKey, -1);
        List list = redisTemplate.exec();
        if (CollectionUtils.isEmpty(list)) {
            return false;
        }
        return true;
    }

    /**
     * watch监控
     *
     * @param key 需要监控的键
     */
    public void watch(String key) {
        redisTemplate.watch(key);
    }

    /**
     * 递增
     *
     * @param key   键
     * @param delta 要增加几(大于0)
     */
    public long incr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }


    /**
     * 递减
     *
     * @param key   键
     * @param delta 要减少几(小于0)
     * @return
     */
    public long decr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, -delta);
    }

    /**
     * 删除对应的value
     */
    public void remove(String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }

    /**
     * 判断缓存中是否有对应的value
     */
    public boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }


    /**
     * 读取缓存
     *
     * @param key 键
     * @return 对应的值
     */
    public Object get(String key) {
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        return operations.get(key);
    }


    /**
     * 哈希 添加
     *
     * @param key   键
     * @param field 域
     * @param value 值
     */
    public void hmSet(String key, Object field, Object value) {
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        hash.put(key, field, value);
    }

    /**
     * 哈希获取数据
     *
     * @param key   键
     * @param field 域
     * @return 值
     */
    public Object hmGet(String key, Object field) {
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        return hash.get(key, field);
    }

    /**
     * 列表添加
     *
     * @param key   列表键
     * @param value 添加到列表的值
     */
    public void lPush(String key, Object value) {
        ListOperations<String, Object> list = redisTemplate.opsForList();
        list.rightPush(key, value);
    }

    /**
     * 列表获取
     *
     * @param key   列表的键
     * @param start 起始下标
     * @param end   终止下标
     * @return 列表
     */
    public List<Object> lRange(String key, long start, long end) {
        ListOperations<String, Object> list = redisTemplate.opsForList();
        return list.range(key, start, end);
    }

    /**
     * 集合添加
     *
     * @param key   键
     * @param value 值
     */
    public boolean sadd(String key, Object value) {
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        Long add = set.add(key, value);
        if (Long.valueOf("1").equals(add)) {
            return true;
        }
        return false;
    }

    public boolean sadd(String key, Object... values) {
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        Long add = set.add(key, values);
        if (Long.valueOf(values.length).equals(add)) {
            return true;
        }
        return false;
    }

    /**
     * 集合获取
     *
     * @param key 键
     * @return set集合
     */
    public Set<Object> setMembers(String key) {
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        return set.members(key);
    }

    public boolean sismember(String key, Object value) {
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        return set.isMember(key, value);
    }

    /**
     * 有序集合添加
     *
     * @param key    zset的键
     * @param value  zset添加的值
     * @param scoure 对应的分数
     */
    public void zAdd(String key, Object value, double scoure) {
        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
        zset.add(key, value, scoure);
    }

    /**
     * 有序集合获取
     *
     * @param key   zset的键
     * @param start score的分数起始值
     * @param end   score的分数终止值
     * @return zset的子集
     */
    public Set<Object> rangeByScore(String key, double start, double end) {
        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
        return zset.rangeByScore(key, start, end);
    }

}
