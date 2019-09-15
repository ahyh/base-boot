package com.yh.spring.demo.common.utils;

import org.springframework.context.ApplicationContext;

/**
 * Spring工具类
 *
 * @author yanhuan
 */
public class SpringUtil {

    private static volatile ApplicationContext applicationContext;

    public static boolean isApplicationContextLoaded() {
        return applicationContext != null;
    }

    public static void setApplicationContext(ApplicationContext appContext) {
        if (applicationContext == null) {
            applicationContext = appContext;
        }
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    public static Object getBean(Class clazz) {
        return applicationContext.getBean(clazz);
    }

    public static Object getBean(String id, Class clazz) {
        return applicationContext.getBean(id, clazz);
    }

}
