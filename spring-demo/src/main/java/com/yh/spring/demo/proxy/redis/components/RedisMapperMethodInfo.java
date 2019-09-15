package com.yh.spring.demo.proxy.redis.components;

import lombok.Data;
import org.apache.ibatis.mapping.MappedStatement;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 方法收集的信息
 *
 * @author yanhuan
 */
public class RedisMapperMethodInfo {

    /**
     * Mapper接口
     */
    private Class mapperInterface;

    /**
     * 方法
     */
    private Method method;

    /**
     * 返回值类型
     */
    private Class returnType;

    /**
     * 返回值类型中的泛型类型，如果没有泛型类型则为null
     */
    private List<Class<?>> genericType;

    /**
     * Mapper接口中方法对应的MappedStatement
     */
    private MappedStatement mappedStatement;

    /**
     * 方法参数类型
     */
    private List<Class<?>> paramTypes;

    public Class getMapperInterface() {
        return mapperInterface;
    }

    public void setMapperInterface(Class mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Class getReturnType() {
        return returnType;
    }

    public void setReturnType(Class returnType) {
        this.returnType = returnType;
    }

    public List<Class<?>> getGenericType() {
        return genericType;
    }

    public void setGenericType(List<Class<?>> genericType) {
        this.genericType = genericType;
    }

    public MappedStatement getMappedStatement() {
        return mappedStatement;
    }

    public void setMappedStatement(MappedStatement mappedStatement) {
        this.mappedStatement = mappedStatement;
    }

    public List<Class<?>> getParamTypes() {
        return paramTypes;
    }

    public void setParamTypes(List<Class<?>> paramTypes) {
        this.paramTypes = paramTypes;
    }
}
