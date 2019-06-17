package com.yh.service;

/**
 * 手机验证码服务，全redis实现
 *
 * @author yanhuan
 */
public interface PhoneConfirmService {

    /**
     * 获取已验证次数
     *
     * @param phone 手机号
     * @return 已验证次数
     */
    Integer confirmCount(String phone);

    /**
     * 生成验证码并返回
     *
     * @param phone 手机号
     * @return 验证码
     */
    String getConfirmCode(String phone);

    /**
     * 验证，成功返回true，否则返回false
     *
     * @param phone 手机号
     * @param code  验证码
     * @return 是否验证成功
     */
    Boolean confirm(String phone, String code);


}
