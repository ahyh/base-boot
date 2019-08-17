package com.yh.observer.selfdef;

/**
 * 当前信息的布告板
 *
 * @author yanhuan
 */
public class CurrentConditionDisplay implements Observer, DisplayElement {

    private Integer temp;

    private Integer humidity;

    private Subject watcherData;

    public CurrentConditionDisplay(Subject watcherData) {
        this.watcherData = watcherData;
        watcherData.registerObserver(this);
    }

    @Override
    public void display() {
        System.out.println("Current Conditions:" + temp + "F degree and humidity:" + humidity);
    }

    @Override
    public void update(Integer temp, Integer humidity, Integer pressure) {
        this.temp = temp;
        this.humidity = humidity;
        display();
    }
}
