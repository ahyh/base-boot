package com.yh.decorator;

/**
 * 抽象类，表示一种饮料
 *
 * @author yanhuan
 */
public abstract class Beverage {

    String description = "Unknown Beverage";

    public String getDescription() {
        return description;
    }

    public abstract double cost();
}
