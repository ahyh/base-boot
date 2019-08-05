package com.yh.singleton;

/**
 * 静态内部类实现方式：
 * 1、外部类没有static属性，不会像饿汉式那样立即加载对象
 * 2、只有真正调用getSingleton()，才会加载静态内部类，加载类时是线程安全的
 * 只能被赋值一次
 * 3、兼备了并发高效调用和延迟加载的优势
 *
 * @author yanhuan
 */
public class Singleton4 {

    //0-私有化构造器
    private Singleton4() {
    }

    //1-定义一个静态内部类，持有一个单例实例对象
    private static final class Singleton4Holder {
        private static final Singleton4 singleton = new Singleton4();
    }

    //2-获取单例实例方法
    public static Singleton4 getSingleton() {
        return Singleton4Holder.singleton;
    }

}
