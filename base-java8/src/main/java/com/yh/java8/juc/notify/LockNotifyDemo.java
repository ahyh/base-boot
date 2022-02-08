package com.yh.java8.juc.notify;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockNotifyDemo {

    public static void main(String[] args) {
        ResData resData = new ResData();
        new Thread(() -> {
            try {
                for (int j = 0; j < 10; j++) {
                    resData.sub();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Sub-0").start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    resData.add();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Add-0").start();

        new Thread(() -> {
            try {
                for (int j = 0; j < 10; j++) {
                    resData.sub();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Sub-1").start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    resData.add();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Add-1").start();
    }

    static class ResData {

        private int num = 0;

        private Lock lock = new ReentrantLock();

        private Condition condition = lock.newCondition();

        public void add() {
            lock.lock();
            try {
                while (num != 0) {
                    condition.await();
                }
                num++;
                System.out.println(Thread.currentThread().getName() + ":" + num);
                condition.signalAll();
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void sub() {
            lock.lock();
            try {
                while (num == 0) {
                    condition.await();
                }
                num--;
                System.out.println(Thread.currentThread().getName() + ":" + num);
                condition.signalAll();
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
