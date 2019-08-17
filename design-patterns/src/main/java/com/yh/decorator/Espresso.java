package com.yh.decorator;

/**
 * 一种饮料，浓缩咖啡
 *
 * @author yanhuan
 */
public class Espresso extends Beverage {

    public Espresso() {
        description = "Espresso";
    }

    @Override
    public double cost() {
        return 1.99;
    }
}
