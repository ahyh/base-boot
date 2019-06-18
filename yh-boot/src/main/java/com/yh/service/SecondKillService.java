package com.yh.service;

/**
 * 秒杀接口定义
 *
 * @author yanhuan
 */
public interface SecondKillService {

    /**
     * 秒杀接口
     *
     * @param skuId  商品id
     * @param userId 用户名
     * @return 是否秒杀成功
     */
    boolean secondKill(String skuId, String userId) throws Exception;
}
