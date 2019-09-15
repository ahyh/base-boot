package com.yh.spring.demo.proxy.redis.components;

import org.springframework.beans.factory.FactoryBean;

/**
 * RedisMapper的代理
 *
 * @author yanhuan
 */
public class RedisMapperProxyFactoryBean implements FactoryBean {

    private Class<?> mapperInterface;

    @Override
    public Object getObject() throws Exception {
        return RedisMapperProxyHolder.get(mapperInterface);
    }

    @Override
    public Class<?> getObjectType() {
        return mapperInterface;
    }

    public Class<?> getMapperInterface() {
        return mapperInterface;
    }

    public void setMapperInterface(Class<?> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }
}
