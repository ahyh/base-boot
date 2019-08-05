package com.yh.singleton;

/**
 * 双重检验锁方式：在静态获取单例实例时做了两次判断，且采用synchronized关键字确保单例
 *
 * @author yanhuan
 */
public class Singleton3 {

    //0-私有化构造器
    private Singleton3() {
    }

    //1-设置私有静态属性,volatile关键字保证数据在多个线程间修改是可见的
    private volatile static Singleton3 singleton;

    //2-提供获取实例方法，双重检验，加synchronized关键字保证线程安全
    public static Singleton3 getSingleton() {
        if (null == singleton) {
            synchronized (Singleton3.class) {
                if (null == singleton) {
                    singleton = new Singleton3();
                }
            }
        }
        return singleton;
    }
}
