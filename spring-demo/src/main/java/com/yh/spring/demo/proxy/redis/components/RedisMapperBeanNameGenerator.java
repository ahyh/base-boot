package com.yh.spring.demo.proxy.redis.components;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;

/**
 * 生成Bean的名称生成器
 *
 * @author yanhuan
 */
public class RedisMapperBeanNameGenerator extends AnnotationBeanNameGenerator {

    @Override
    public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
        //定义为高优先级，优先装配
        definition.setPrimary(true);
        String beanName = super.generateBeanName(definition, registry);
        return beanName.concat("Redis");
    }
}
