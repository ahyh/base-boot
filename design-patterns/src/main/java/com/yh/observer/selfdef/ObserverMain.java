package com.yh.observer.selfdef;

public class ObserverMain {

    public static void main(String[] args) {
        WatcherData wacherData = new WatcherData();
        CurrentConditionDisplay currentConditionDisplay = new CurrentConditionDisplay(wacherData);
        StatisticDisplay statisticDisplay = new StatisticDisplay(wacherData);
        ThirdPartDisplay thirdPartDisplay = new ThirdPartDisplay(wacherData);

        wacherData.setMeasurements(1,2,3);
        wacherData.setMeasurements(4,5,6);
        wacherData.setMeasurements(7,8,9);
    }
}
