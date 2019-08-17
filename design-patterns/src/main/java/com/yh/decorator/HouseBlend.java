package com.yh.decorator;

/**
 * 一种饮料，综合
 *
 * @author yanhuan
 */
public class HouseBlend extends Beverage {

    public HouseBlend(){
        description = "HouseBlend";
    }

    @Override
    public double cost() {
        return 0.89;
    }
}
