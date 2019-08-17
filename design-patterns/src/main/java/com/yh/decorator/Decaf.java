package com.yh.decorator;

/**
 * 一种饮料，低咖啡因
 *
 * @author yanhuan
 */
public class Decaf extends Beverage {

    public Decaf() {
        description = "Decaf";
    }

    @Override
    public double cost() {
        return 2.21;
    }
}
