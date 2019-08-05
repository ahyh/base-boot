package com.yh.singleton;

/**
 * 单例模式:饿汉式，天然线程安全，在初始化时立即加载这个对象
 *
 * @author yanhuan
 */
public class Singleton1 {

    //0-私有化构造器
    private Singleton1() {
    }

    //1-类初始化时立即加载对象
    private static Singleton1 singleton = new Singleton1();

    //2-静态方法提供单例对象
    public static Singleton1 getSingleton() {
        return singleton;
    }

}
