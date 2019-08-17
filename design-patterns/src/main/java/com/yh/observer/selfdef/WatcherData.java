package com.yh.observer.selfdef;

import java.util.ArrayList;
import java.util.List;

/**
 * 实际的主题
 *
 * @author yanhuan
 */
public class WatcherData implements Subject {

    private static final List<Observer> OBSERVERS = new ArrayList<>();

    private Integer temp;

    private Integer humidity;

    private Integer pressure;

    @Override
    public void registerObserver(Observer observer) {
        OBSERVERS.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        int index = OBSERVERS.indexOf(observer);
        if (index >= 0) {
            OBSERVERS.remove(index);
        }
    }

    @Override
    public void nofityObservers() {
        for (Observer observer : OBSERVERS) {
            observer.update(temp, humidity, pressure);
        }
    }

    public void measurementsChanged() {
        nofityObservers();
    }

    public void setMeasurements(Integer temp, Integer humidity, Integer pressure) {
        this.temp = temp;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }
}
