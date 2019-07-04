package com.yh.java8.completable.future;

import java.util.Random;

public class ExchangeService {

    public enum Money {
        /**
         * 欧元
         */
        EUR,

        /**
         * 美元
         */
        USD,

        /**
         * 人民币
         */
        RMB
    }

    public static double getRate(Money source, Money target) {
        return new Random().nextDouble();
    }
}
