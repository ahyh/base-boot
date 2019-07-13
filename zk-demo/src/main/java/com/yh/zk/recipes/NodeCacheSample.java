package com.yh.zk.recipes;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.util.concurrent.TimeUnit;

/**
 * Curator-recipes中NodeCache使用
 *
 * @author yanhuan
 */
public class NodeCacheSample {

    private static final String PATH = "/zk-curator/node-cache";

    private static final CuratorFramework CLIENT = CuratorFrameworkFactory
            .builder()
            .connectString("mini1:2181,mini2:2181,mini3:2181")
            .sessionTimeoutMs(3000)
            .retryPolicy(new ExponentialBackoffRetry(1000, 3))
            .build();

    public static void main(String[] args) throws Exception {
        CLIENT.start();

        CLIENT.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .forPath(PATH, "init".getBytes());
        NodeCache cache = new NodeCache(CLIENT, PATH, false);
        //设置为true,NodeCache第一次启动就立刻从ZK上读取对应节点的内容，并保存在Cache中
        cache.start(true);

        cache.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                System.out.println("Node data changed:" + new String(cache.getCurrentData().getData()));
            }
        });

        CLIENT.setData().forPath(PATH, "update".getBytes());
        TimeUnit.SECONDS.sleep(2);

        //删除无法触发NodeCacheListener回调
        CLIENT.delete().deletingChildrenIfNeeded().forPath(PATH);
        TimeUnit.SECONDS.sleep(100);
    }
}
