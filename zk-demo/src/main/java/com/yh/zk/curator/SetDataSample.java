package com.yh.zk.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * 使用Curator客户端更新数据
 *
 * @author yanhuan
 */
public class SetDataSample {

    private static final String PATH = "/zk-book/c1";

    private static final CuratorFramework CLIENT = CuratorFrameworkFactory
            .builder()
            .connectString("mini1:2181,mini2:2181,mini3:2181")
            .sessionTimeoutMs(3000)
            .retryPolicy(new ExponentialBackoffRetry(1000, 3))
            .build();

    public static void main(String[] args) throws Exception {
        CLIENT.start();
        CLIENT.setData()
                .withVersion(1).forPath(PATH, "HelloWorld".getBytes());
    }
}
