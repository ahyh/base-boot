package com.yh.singleton;

/**
 * 懒汉式，在需要使用对象时在new出来，有线程安全问题
 * 资源利用率提高了，但是每次调用的时候都要同步，并发效率低了
 *
 * @author yanhuan
 */
public class Singleton2 {

    //0-私有化构造器
    private Singleton2() {
        //多次调用直接抛出异常，确保单例
        if (null != singleton) {
            throw new RuntimeException("单例不允许创建多个对象");
        }
    }

    //1-设置私有静态属性
    private static Singleton2 singleton;

    //2-提供获取实例方法，加synchronized关键字则可以保证线程安全
    public static synchronized Singleton2 getSingleton() {
        if (singleton == null) {
            singleton = new Singleton2();
        }
        return singleton;
    }

}
