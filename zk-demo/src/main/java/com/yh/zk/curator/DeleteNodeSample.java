package com.yh.zk.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * 使用Curator删除节点
 *
 * @author yanhuan
 */
public class DeleteNodeSample {

    private static final String PATH = "/zk-book/c1";

    private static final CuratorFramework CLIENT = CuratorFrameworkFactory
            .builder()
            .connectString("mini1:2181,mini2:2181,mini3:2181")
            .sessionTimeoutMs(3000)
            .retryPolicy(new ExponentialBackoffRetry(1000, 3))
            .build();

    public static void main(String[] args) throws Exception {
        CLIENT.start();

        //这个方式只能删除叶子节点
        CLIENT.delete().forPath(PATH);

        //删除一个节点，并递归删除所有子节点
        CLIENT.delete().deletingChildrenIfNeeded().forPath(PATH);

        //删除特定版本号的PATH节点数据
        CLIENT.delete().withVersion(0).forPath(PATH);

        //删除一个节点，强制保证一定删除
        CLIENT.delete().guaranteed().forPath(PATH);
    }
}
