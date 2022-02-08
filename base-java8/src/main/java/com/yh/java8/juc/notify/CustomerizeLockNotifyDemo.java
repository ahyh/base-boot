package com.yh.java8.juc.notify;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程间定制化通信
 * AA打印5次，然后BB打印10次，然后CC打印15次，循环10次
 *
 * @author yanhuan
 */
public class CustomerizeLockNotifyDemo {

    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++)
                    shareData.printA(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "AA").start();

        new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++)
                    shareData.printC(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "CC").start();

        new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++)
                    shareData.printB(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "BB").start();
    }

    public static class ShareData {
        private int flag = 1;

        private Lock lock = new ReentrantLock();
        public Condition conditionA = lock.newCondition();
        public Condition conditionB = lock.newCondition();
        public Condition conditionC = lock.newCondition();

        public void printA(int loop) throws Exception {
            lock.lock();
            try {
                while (flag != 1) {
                    conditionA.await();
                }
                for (int i = 0; i < 5; i++) {
                    System.out.println(Thread.currentThread().getName() + ":" + i + loop);
                }
                flag = 2;
                conditionB.signalAll();
            } finally {
                lock.unlock();
            }
        }

        public void printB(int loop) throws Exception {
            lock.lock();
            try {
                while (flag != 2) {
                    conditionB.await();
                }
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName() + ":" + i + loop);
                }
                flag = 3;
                conditionC.signalAll();
            } finally {
                lock.unlock();
            }
        }

        public void printC(int loop) throws Exception {
            lock.lock();
            try {
                while (flag != 3) {
                    conditionC.await();
                }
                for (int i = 0; i < 15; i++) {
                    System.out.println(Thread.currentThread().getName() + ":" + i + loop);
                }
                flag = 1;
                conditionA.signalAll();
            } finally {
                lock.unlock();
            }
        }

    }
}
