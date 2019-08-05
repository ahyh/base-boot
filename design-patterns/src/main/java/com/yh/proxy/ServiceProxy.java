package com.yh.proxy;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;

/**
 * 根据接口类型生成动态代理
 *
 * @author yanhuan
 */
public class ServiceProxy<T> implements InvocationHandler {

    private Class<T> interfaceClazz;

    public ServiceProxy(Class<T> interfaceClazz) {
        this.interfaceClazz = interfaceClazz;
    }

    public T getProxy() {
        return (T) Proxy.newProxyInstance(interfaceClazz.getClassLoader(), new Class[]{interfaceClazz}, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            if (Object.class.equals(method.getDeclaringClass())) {
                return method.invoke(this, args);
            } else if (isDefaultMethod(method)) {
                return invokeDefaultMethod(proxy, method, args);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ServiceWrap serviceWrap = new ServiceWrap(interfaceClazz, method);
        return serviceWrap.execute(args);
    }

    private boolean isDefaultMethod(Method method) {
        return (method.getModifiers()
                & (Modifier.ABSTRACT | Modifier.PUBLIC | Modifier.STATIC)) == Modifier.PUBLIC
                && method.getDeclaringClass().isInterface();
    }

    private Object invokeDefaultMethod(Object proxy, Method method, Object[] args) throws Throwable {
        final Constructor<MethodHandles.Lookup> constructor = MethodHandles.Lookup.class
                .getDeclaredConstructor(Class.class, int.class);
        if (!constructor.isAccessible()) {
            constructor.setAccessible(true);
        }
        final Class<?> declaringClass = method.getDeclaringClass();
        return constructor.newInstance(declaringClass, MethodHandles.Lookup.PRIVATE | MethodHandles.Lookup.PROTECTED
                | MethodHandles.Lookup.PACKAGE | MethodHandles.Lookup.PUBLIC)
                .unreflectSpecial(method, declaringClass).bindTo(proxy).invokeWithArguments(args);
    }
}
