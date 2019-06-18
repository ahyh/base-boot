package com.yh.service.impl;

import com.google.common.base.Preconditions;
import com.yh.cache.RedisService;
import com.yh.service.SecondKillService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 秒杀接口实现类
 *
 * @author yanhuan
 */
@Service
public class SecondKillServiceImpl implements SecondKillService {

    private static final Logger logger = LoggerFactory.getLogger(SecondKillServiceImpl.class);

    @Autowired
    private RedisService redisService;

    /**
     * 秒杀实现
     *
     * @param skuId  商品id
     * @param userId 用户名
     * @return 是否秒杀成功
     */
    @Override
    public boolean secondKill(String skuId, String userId) throws Exception {
        Preconditions.checkArgument(StringUtils.isNotBlank(skuId), "skuId could not blank");
        Preconditions.checkArgument(StringUtils.isNotBlank(userId), "userId could not blank");
        //1-查看userId是否秒杀成功了，如果成功过了提示信息
        String memberKey = "sk:" + skuId + ":userIds";
        boolean sismember = redisService.sismember(memberKey, userId);
        if (sismember) {
            logger.info("SecondKillServiceImpl secondKill already success,skuId:{},userId:{}", skuId, userId);
            throw new RuntimeException("已经秒杀成功了，不能重复秒杀");
        }
        //没有秒杀过，则判断库存
        String skuCountKey = "sk:" + skuId + ":count";
        boolean exists = redisService.exists(skuCountKey);
        if (!exists) {
            throw new RuntimeException("该商品不是秒杀商品");
        }
        return redisService.secondkill(skuId, userId);
    }
}
