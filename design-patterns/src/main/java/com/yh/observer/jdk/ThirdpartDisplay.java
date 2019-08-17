package com.yh.observer.jdk;

import java.util.Observable;
import java.util.Observer;

public class ThirdpartDisplay implements Observer, DisplayElement {

    private Observable observable;

    private Integer temp;

    private Integer humidity;

    public ThirdpartDisplay(Observable observable) {
        this.observable = observable;
        this.observable.addObserver(this);
    }

    @Override
    public void display() {
        System.out.println("ThirdpartDisplay Conditions:" + temp + "F degree and humidity:" + humidity);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof WatcherData) {
            WatcherData watcherData = (WatcherData) o;
            this.temp = watcherData.getTemp();
            this.humidity = watcherData.getHumidity();
            display();
        }
    }
}
