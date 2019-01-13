package com.yh.annotations;

import java.lang.annotation.*;

/**
 * 注解,给MyBatis拦截器使用，将需要加密的属性上加此注解可以实现加密解密
 *
 * @author yanhuan
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface EncrypAnnotation {
}
