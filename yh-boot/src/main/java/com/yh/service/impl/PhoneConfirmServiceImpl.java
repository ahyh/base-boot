package com.yh.service.impl;

import com.google.common.base.Preconditions;
import com.yh.cache.RedisService;
import com.yh.service.PhoneConfirmService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 手机验证码功能实现
 *
 * @author yanhuan
 */
@Component
@ConditionalOnProperty(name = "enable-redis-flag", havingValue = "true")
public class PhoneConfirmServiceImpl implements PhoneConfirmService {

    private static final Logger logger = LoggerFactory.getLogger(PhoneConfirmServiceImpl.class);

    private static final Long EXPIRE_TIME_SECONDS = 120L;

    private static final Integer CONFIRM_CODE_LENGTH = 6;

    @Autowired
    private RedisService redisService;


    /**
     * 获取手机号验证次数
     *
     * @param phone 手机号
     * @return 手机号验证次数
     */
    @Override
    public Integer confirmCount(String phone) {
        Preconditions.checkArgument(StringUtils.isNotBlank(phone), "phone could not blank");
        String key = phone + ":count";
        Object countObj = redisService.get(key);
        if (null == countObj) {
            return 0;
        }
        return Integer.valueOf(countObj.toString());
    }

    /**
     * 获取验证码，然后将验证码放在redis中并设置过期时间为2min
     *
     * @param phone 手机号
     * @return 返回验证码
     */
    @Override
    public String getConfirmCode(String phone) {
        Preconditions.checkArgument(StringUtils.isNotBlank(phone), "phone could not blank");
        String key = phone + ":code";
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < CONFIRM_CODE_LENGTH; i++) {
            sb.append(random.nextInt(10));
        }
        redisService.setEx(key, sb.toString(), EXPIRE_TIME_SECONDS, TimeUnit.SECONDS);
        return sb.toString();
    }

    /**
     * 验证，成功返回true，否则返回false
     *
     * @param phone 手机号
     * @param code  验证码
     * @return 是否验证成功
     */
    @Override
    public Boolean confirm(String phone, String code) {
        Preconditions.checkArgument(StringUtils.isNotBlank(phone), "phone could not blank");
        Preconditions.checkArgument(StringUtils.isNotBlank(code), "code could not blank");
        Object countObj = redisService.get(phone + ":count");
        if (null == countObj) {
            redisService.set(phone + ":count", 1);
        } else {
            redisService.incr(phone + ":count", 1);
        }
        Object codeStr = redisService.get(phone + ":code");
        if (null == codeStr) {
            logger.error("验证失败，已过期");
            return false;
        }
        if (code.equalsIgnoreCase(codeStr.toString())) {
            return true;
        } else {
            logger.error("验证失败，验证码不正确");
            return false;
        }
    }
}
