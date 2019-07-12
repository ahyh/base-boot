package com.yh.zk.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.data.Stat;

/**
 * Curator客户端获取数据
 *
 * @author yanhuan
 */
public class GetDataSample {

    private static final String PATH = "/zk-book/c1";

    private static final CuratorFramework CLIENT = CuratorFrameworkFactory
            .builder()
            .connectString("mini1:2181,mini2:2181,mini3:2181")
            .sessionTimeoutMs(3000)
            .retryPolicy(new ExponentialBackoffRetry(1000, 3))
            .build();

    public static void main(String[] args) throws Exception {
        CLIENT.start();
        //获取节点下的数据
//        byte[] bytes = CLIENT.getData().forPath(PATH);
//        String s = new String(bytes);
//        System.out.println(s);

        //版本号等信息
        Stat stat = new Stat();
        byte[] bytes1 = CLIENT.getData().storingStatIn(stat).forPath(PATH);
        System.out.println(stat);
        System.out.println(new String(bytes1));
    }
}
