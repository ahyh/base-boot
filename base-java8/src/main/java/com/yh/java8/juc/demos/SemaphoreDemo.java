package com.yh.java8.juc.demos;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Semaphore demo
 * 共享资源只能被几个线程访问
 *
 * permits number is 3, 6 thread use
 */
public class SemaphoreDemo {

    private static final int NUMBER = 3;

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    //当前线程尝试获取一个许可证，如果能够获取到就继续往下执行，否则就会阻塞
                    semaphore.acquire();

                    System.out.println(Thread.currentThread().getName() + "获取到许可证");

                    // 模拟执行任务
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));

                    System.out.println(Thread.currentThread().getName() + "任务执行完成，归还许可证");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    // 归还许可证，如果没有归还，其他线程不能获取
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }
    }
}
