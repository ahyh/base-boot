package com.yh.zk.recipes.distribute.lock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * 使用Curator实现分布式锁功能
 *
 * @author yanhuan
 */
public class RecipesLockSample {

    private static final String LOCK_PATH = "/distribute-lock";

    private static final CuratorFramework CLIENT = CuratorFrameworkFactory
            .builder()
            .connectString("mini1:2181,mini2:2181,mini3:2181")
            .sessionTimeoutMs(3000)
            .retryPolicy(new ExponentialBackoffRetry(1000, 3))
            .build();

    public static void main(String[] args) {
        CLIENT.start();
        //创建一个锁
        final InterProcessMutex lock = new InterProcessMutex(CLIENT, LOCK_PATH);

        final CountDownLatch latch = new CountDownLatch(1);

        //每次只有一个线程获取锁，一个线程获得锁执行完逻辑释放后，其他线程才能获取锁
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                try {
                    latch.await();
                    lock.acquire();
                    System.out.println(Thread.currentThread().getName() + "获取锁");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss|SSS");
                String orderNo = format.format(new Date());
                System.out.println("生成的订单号是：" + orderNo);
                try {
                    lock.release();
                    System.out.println(Thread.currentThread().getName() + "释放锁锁");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
        latch.countDown();
    }
}
