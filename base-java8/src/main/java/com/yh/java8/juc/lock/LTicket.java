package com.yh.java8.juc.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LTicket {

    private int NUM = 30;

    private final Lock lock = new ReentrantLock();

    /**
     * 通过lock的方式来同步
     */
    public void sellTicketLock() {
        lock.lock();
        try {
            if (NUM > 0) {
                System.out.println(Thread.currentThread().getName() + "sold:" + (30 - NUM) + "retained:" + NUM--);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
