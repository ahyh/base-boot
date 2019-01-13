package com.yh.utils;

import com.google.common.base.Preconditions;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 加密解密的工具类
 *
 * @author yanhuan1
 */
public final class EncrypUtil {

    /**
     * 将原字符串加密并返回加密后的字符串
     *
     * @param sourceStr 原字符串
     * @return 加密后的字符串
     */
    public static String encodeJDKBase64(String sourceStr) {
        Preconditions.checkArgument(StringUtils.isNoneBlank(sourceStr), "需要处理的字符串为空!");
        return new BASE64Encoder().encode(sourceStr.getBytes());
    }

    /**
     * 将加密后的字符串解密并返回解密后的字符串
     *
     * @param targetStr 加密的字符串
     * @return 加密前的字符串
     */
    public static String decodeJDKBase64(String targetStr) {
        try {
            Preconditions.checkArgument(StringUtils.isNoneBlank(targetStr), "需要处理的字符串为空!");
            return new String(new BASE64Decoder().decodeBuffer(targetStr));
        } catch (Exception e) {
            throw new RuntimeException("解密失败" + targetStr);
        }
    }

    /**
     * 加密
     *
     * @param sourceStr 原字符串
     * @return 加密后的字符串
     */
    public static String encodeCommonsCodecBase64(String sourceStr) {
        Preconditions.checkArgument(StringUtils.isNoneBlank(sourceStr), "需要处理的字符串为空!");
        return new String(Base64.encodeBase64(sourceStr.getBytes()));
    }

    /**
     * 解密
     *
     * @param targetStr 目标字符串
     * @return 解密后的字符串
     */
    public static String decodeCommonsCodecBase64(String targetStr) {
        Preconditions.checkArgument(StringUtils.isNoneBlank(targetStr), "需要处理的字符串为空!");
        return new String(Base64.decodeBase64(targetStr));
    }

    /**
     * 加密
     *
     * @param sourceStr 原字符串
     * @return 加密后字符串
     */
    public static String encodeBouncyCaseleBase64(String sourceStr) {
        Preconditions.checkArgument(StringUtils.isNoneBlank(sourceStr), "需要处理的字符串为空!");
        return new String(org.bouncycastle.util.encoders.Base64.encode(sourceStr.getBytes()));
    }

    /**
     * 解密
     *
     * @param targetStr 目标字符串
     * @return 解密后的字符串
     */
    public static String decodeBouncyCaseleBase64(String targetStr) {
        Preconditions.checkArgument(StringUtils.isNoneBlank(targetStr), "需要处理的字符串为空!");
        return new String(org.bouncycastle.util.encoders.Base64.decode(targetStr));
    }

}
