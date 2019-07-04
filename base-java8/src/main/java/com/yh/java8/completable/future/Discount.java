package com.yh.java8.completable.future;

/**
 * 折扣代码
 *
 * @author yanhuan
 */
public class Discount {

    public enum Code {
        /**
         * 无折扣
         */
        NONE(0),
        /**
         * silver折扣
         */
        SILVER(5),
        /**
         * gold折扣
         */
        GOLD(10),
        /**
         * platinum折扣
         */
        PLATINUM(15),
        /**
         * diamond折扣
         */
        DIAMOND(20);

        private final int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }
    }

    public static String applyDiscount(Quote quote) {
        return quote.getShopName() + " price is " +
                Discount.apply(quote.getPrice(),
                        quote.getDiscountCode());
    }

    private static double apply(double price, Code code) {
        Shop.delay();
        return price * (100 - code.percentage) / 100;
    }
}
