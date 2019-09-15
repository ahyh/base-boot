package com.yh.spring.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.Annotation;

/**
 * RedisMapperProxy配置类
 *
 * @author yanhuan
 */
@Configuration
@ConfigurationProperties(prefix = "redis.mapper.scanner")
public class RedisMapperProxyConfig {

    private Class<? extends Annotation> annotationClass;

    private String basePackage;

    public Class<? extends Annotation> getAnnotationClass() {
        return annotationClass;
    }

    public void setAnnotationClass(Class<? extends Annotation> annotationClass) {
        this.annotationClass = annotationClass;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }
}
