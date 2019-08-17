package com.yh.observer.jdk;

/**
 * 测试观察者模式
 *
 * @author yanhuan
 */
public class ObserverMain {

    public static void main(String[] args) {
        WatcherData watcherData = new WatcherData();
        new CurrentConditionDisplay(watcherData);
        new StatisticDisplay(watcherData);
        new ThirdpartDisplay(watcherData);

        watcherData.setMeasurements(1, 2, 3);
        watcherData.setMeasurements(4, 5, 6);
    }
}
