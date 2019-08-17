package com.yh.observer.selfdef;

/**
 * 观察者模式-观察者
 *
 * @author yanhuan
 */
public interface Observer {

    void update(Integer temp, Integer humidity, Integer pressure);
}
