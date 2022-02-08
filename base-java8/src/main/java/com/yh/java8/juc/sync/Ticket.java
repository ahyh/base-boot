package com.yh.java8.juc.sync;

/**
 * 模拟售票资源
 */
public class Ticket {

    private int NUM = 30;

    public synchronized void sellTicketSync() {
        if (NUM > 0) {
            System.out.println(Thread.currentThread().getName() + "sold:" + (30 - NUM) + "retained:" + NUM--);
        }
    }

}
