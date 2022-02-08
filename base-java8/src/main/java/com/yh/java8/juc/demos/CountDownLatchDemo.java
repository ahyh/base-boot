package com.yh.java8.juc.demos;

import java.util.concurrent.CountDownLatch;

/**
 * 模拟6个同学都离开教室以后在关灯
 */
public class CountDownLatchDemo {

    private static final int NUMBER = 6;

    public static void main(String[] args) {
        // 初始化值以后不能修改
        CountDownLatch countDownLatch = new CountDownLatch(6);

        new Thread(() -> {
            try {
                countDownLatch.await();
                System.out.println(Thread.currentThread().getName() + "start work");
            } catch (Exception e){
                e.printStackTrace();
            }
        }, "关灯线程").start();

        for (int i = 0; i < NUMBER; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "离开了教室");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }

    }
}
