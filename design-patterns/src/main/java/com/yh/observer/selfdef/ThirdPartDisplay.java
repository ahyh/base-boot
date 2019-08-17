package com.yh.observer.selfdef;

public class ThirdPartDisplay implements Observer, DisplayElement {

    private Integer temp;

    private Integer humidity;

    private Subject watcherData;

    public ThirdPartDisplay(Subject watcherData) {
        this.watcherData = watcherData;
        watcherData.registerObserver(this);
    }

    @Override
    public void display() {
        System.out.println("ThirdPartDisplay Conditions:" + temp + "F degree and humidity:" + humidity);
    }

    @Override
    public void update(Integer temp, Integer humidity, Integer pressure) {
        this.temp = temp;
        this.humidity = humidity;
        display();
    }
}
