package com.yh.zk.test;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Zk
 */
public class ZkDemoTest {

    private ZooKeeper zkClient;

    @Before
    public void init() throws Exception {
        zkClient = new ZooKeeper("mini1:2181,mini2:2181,mini3:2181", 5000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println("WatchedEvent:" + watchedEvent);
            }
        });
    }

    /**
     * 创建节点
     */
    @Test
    public void testCreate() throws Exception {
        String path = "/huanyan";
        byte[] data = "Hello World".getBytes();
        String p = zkClient.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(p);
    }

    /**
     * 获取数据并监控变化
     */
    @Test
    public void testGetAndWatch() throws Exception {
        List<String> children = zkClient.getChildren("/", new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                try {
                    List<String> children = zkClient.getChildren("/", true);
                    for (String child : children) {
                        System.out.println("child:" + child);
                    }
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        for (String child : children) {
            System.out.println("child:" + child);
        }
        //睡1000秒保持ZK连接
        TimeUnit.SECONDS.sleep(1000);
    }

    /**
     * 判断节点是否存在并增加监听
     */
    @Test
    public void testIsAlive() throws Exception {
        Stat exists = zkClient.exists("/huanyan", new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("WatchedEvent:" + event);
            }
        });
        System.out.println(exists);
        //睡1000秒保持ZK连接
        TimeUnit.SECONDS.sleep(1000);
    }
}
