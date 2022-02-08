package com.yh.java8.juc.notify;

public class SyncNotifyDemo {

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

        public synchronized void add() throws InterruptedException {
            while (num != 0) {
                this.wait();
            }
            num++;
            System.out.println(Thread.currentThread().getName() + ":" + num);
            this.notifyAll();
        }

        public synchronized void sub() throws InterruptedException {
            while (num == 0) {
                this.wait();
            }
            num--;
            System.out.println(Thread.currentThread().getName() + ":" + num);
            this.notifyAll();
        }
    }
}
