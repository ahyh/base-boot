package com.yh.observer.jdk;

import java.util.Observable;

public class WatcherData extends Observable {

    private Integer temp;

    private Integer humidity;

    private Integer pressure;

    public WatcherData() {

    }

    public void measurementChanged() {
        setChanged();
        notifyObservers();
    }

    public void setMeasurements(Integer temp, Integer humidity, Integer pressure) {
        this.temp = temp;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementChanged();
    }

    public Integer getTemp() {
        return temp;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public Integer getPressure() {
        return pressure;
    }
}
