package com.yh.java8.juc.demos;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class CycleBarrierDemo {

    public static void main(String[] args) {
        // cycle barrier await 数量达到设置的值时，开始执行构造器里面的Runnable任务
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("循环栅栏达到打开标准开始执行栅栏任务");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("栅栏任务执行结束");
        });

        for (int i = 0; i < 7; i++) {
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " before cycle barrier await");
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread().getName() + " after cycle barrier await");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }

        System.out.println("over");
    }
}
