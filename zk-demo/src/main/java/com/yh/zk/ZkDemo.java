package com.yh.zk;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.List;

/**
 * 测试ZK
 *
 * @author yanhuan
 */
public class ZkDemo {

    public static void main(String[] args) throws Exception {
        ZooKeeper zooKeeper = new ZooKeeper("mini1:2181,mini2:2181,mini3:2181", 3000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("WatchedEvent:" + event);
            }
        });
        List<String> children = zooKeeper.getChildren("/zk-book", true);
        children.forEach(System.out::println);

        Stat stat = new Stat();
        byte[] data = zooKeeper.getData("/zk-book", new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("WatchedEvent:" + event);
            }
        }, stat);
        System.out.println(new String(data));
        System.out.println("Stat:" + stat);
    }
}
