package com.yh.zk.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.TimeUnit;

/**
 * 使用Curator创建ZK客户端
 *
 * @author yanhuan
 */
public class CreateSessionSample {

    public static void main(String[] args) throws Exception {
        //重试机制，每两次重试间隔1000ms，最多重试3次
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        //通过CuratorFrameworkFactory创建客户端
        CuratorFramework client = CuratorFrameworkFactory.newClient("mini1:2181,mini2:2181,mini3:2181", 5000, 3000, retryPolicy);
        //启动客户端
        client.start();
        //保持客户端连接
        TimeUnit.SECONDS.sleep(1000);
    }
}
