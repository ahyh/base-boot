package com.yh.zk.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Curator异步接口使用
 *
 * @author yanhuan
 */
public class BackgroundCallbackSmaple {

    private static final String PATH = "/zk-book/background";

    private static final CuratorFramework CLIENT = CuratorFrameworkFactory
            .builder()
            .connectString("mini1:2181,mini2:2181,mini3:2181")
            .sessionTimeoutMs(3000)
            .retryPolicy(new ExponentialBackoffRetry(1000, 3))
            .build();

    private static final CountDownLatch LATCH = new CountDownLatch(2);

    private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(2);

    public static void main(String[] args) throws Exception {
        CLIENT.start();

        //创建节点，路径相同，不能重复创建
        //创建节点时传入了EXECUTOR，则使用传入的线程池来进行节点的创建
        CLIENT.create().
                creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .inBackground(new BackgroundCallback() {
                    @Override
                    public void processResult(CuratorFramework client, CuratorEvent event) throws Exception {
                        System.out.println("Event:type:" + event.getType() + ",code:" + event.getResultCode());
                        System.out.println("Thread:" + Thread.currentThread().getName());
                        LATCH.countDown();
                    }
                }, EXECUTOR).forPath(PATH, "INIT".getBytes());

        //创建节点时没有传入EXECUTOR。则使用main-EventThread线程来进行节点创建
        CLIENT.create().
                creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .inBackground(new BackgroundCallback() {
                    @Override
                    public void processResult(CuratorFramework client, CuratorEvent event) throws Exception {
                        System.out.println("Event:type:" + event.getType() + ",code:" + event.getResultCode());
                        System.out.println("Thread:" + Thread.currentThread().getName());
                        LATCH.countDown();
                    }
                }).forPath(PATH, "INIT".getBytes());

        LATCH.await();

        EXECUTOR.shutdown();
    }
}
