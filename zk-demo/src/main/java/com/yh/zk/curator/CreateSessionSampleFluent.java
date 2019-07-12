package com.yh.zk.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.TimeUnit;

/**
 * 通过流式的编程方式来创建ZK客户端
 *
 * @author yanhuan
 */
public class CreateSessionSampleFluent {

    public static void main(String[] args) throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);

        //通过流式的方式来创建客户端，namespace表示一个命名空间，所有的操作在/yanhuan目录下进行
        CuratorFramework client = CuratorFrameworkFactory.builder().connectString("mini1:2181,mini2:2181,mini3:2181")
                .sessionTimeoutMs(3000)
                .retryPolicy(retryPolicy)
                .namespace("yanhuan")
                .build();

        client.start();

        TimeUnit.SECONDS.sleep(1000);
    }
}
