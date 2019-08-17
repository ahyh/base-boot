package com.yh.observer.selfdef;

public interface Subject {

    void registerObserver(Observer observer);

    void removeObserver(Observer observer);

    void nofityObservers();
}
