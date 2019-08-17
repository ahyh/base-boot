package com.yh.decorator;

/**
 * 一种饮料，深培
 *
 * @author yanhuan
 */
public class DarkRoast extends Beverage {

    public DarkRoast() {
        description = "DarkRoast";
    }

    @Override
    public double cost() {
        return 1.21;
    }
}
