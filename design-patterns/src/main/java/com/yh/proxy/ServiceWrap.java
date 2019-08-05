package com.yh.proxy;

import java.lang.reflect.Method;

/**
 * Service方法包装
 *
 * @author yanhuan
 */
public class ServiceWrap {

    private Class interfaceClazz;

    private Method method;

    public ServiceWrap(Class interfaceClazz, Method method) {
        this.interfaceClazz = interfaceClazz;
        this.method = method;
    }

    public String execute(Object[] args) {
        String clazzName = interfaceClazz.getName();
        String methodName = method.getName();
        Class<?>[] parameterTypes = method.getParameterTypes();
        StringBuilder argsSb = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            argsSb.append(parameterTypes[i].getName()).append(":").append(args[i]).append(",");
        }
        String argsStr = argsSb.toString();
        if (argsStr.endsWith(",")) {
            argsStr = argsStr.substring(0, argsStr.length() - 1);
        }
        return clazzName + " " + methodName + "(" + argsStr + ")";
    }
}
