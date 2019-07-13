package com.yh.zk.recipes;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.util.concurrent.TimeUnit;

/**
 * Cutator-recipes中监控数据节点子节点数据变化情况
 *
 * @author yanhuan
 */
public class PathchildCacheSample {

    private static final String PATH = "/curator";

    private static final CuratorFramework CLIENT = CuratorFrameworkFactory
            .builder()
            .connectString("mini1:2181,mini2:2181,mini3:2181")
            .sessionTimeoutMs(3000)
            .retryPolicy(new ExponentialBackoffRetry(1000, 3))
            .build();

    public static void main(String[] args) throws Exception {
        CLIENT.start();

        PathChildrenCache cache = new PathChildrenCache(CLIENT, PATH, true);

        cache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);

        //PathChildrenCacheListener只能监控子节点的变更（添加，删除，更新），节点本身的变更不会回调此方法
        cache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                switch (event.getType()) {
                    case CHILD_ADDED:
                        System.out.println("CHILD_ADD:" + event.getData().getPath());
                        break;
                    case CHILD_UPDATED:
                        System.out.println("CHILD_UPDATE:" + event.getData().getPath());
                        break;
                    case CHILD_REMOVED:
                        System.out.println("CHILD_REMOVED:" + event.getData().getPath());
                        break;
                    default:
                        break;
                }
            }
        });

        CLIENT.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(PATH);

        TimeUnit.SECONDS.sleep(2);

        CLIENT.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(PATH + "/c1");

        TimeUnit.SECONDS.sleep(2);

        CLIENT.delete().forPath(PATH + "/c1");

        TimeUnit.SECONDS.sleep(2);

        CLIENT.delete().forPath(PATH);

        TimeUnit.SECONDS.sleep(100);
    }
}
