package com.yh.adapter;

/**
 * Turkey接口实现类
 *
 * @author yanhuan
 */
public class WildTurkey implements Turkey {
    @Override
    public void gobble() {
        System.out.println("WildTurkey gobble");
    }

    @Override
    public void fly() {
        System.out.println("WildTurkey fly");
    }
}
