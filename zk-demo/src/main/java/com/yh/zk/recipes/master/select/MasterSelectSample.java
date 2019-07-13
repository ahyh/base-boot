package com.yh.zk.recipes.master.select;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.TimeUnit;

/**
 * 使用Curator实现Master选举功能
 *
 * @author yanhuan
 * <p>
 * master选举思路：
 * 选择一个根节点，例如/master-select，多台机器同时向该节点创建一个子节点
 * /master-select/lock，利用ZK的特性，最终只会有一个节点创建成功，成功的
 * 那一台就是master
 */
public class MasterSelectSample {

    private static final String PATH = "/master-select";

    private static final CuratorFramework CLIENT = CuratorFrameworkFactory
            .builder()
            .connectString("mini1:2181,mini2:2181,mini3:2181")
            .sessionTimeoutMs(3000)
            .retryPolicy(new ExponentialBackoffRetry(1000, 3))
            .build();

    public static void main(String[] args) throws Exception {
        //启动客户端
        CLIENT.start();
        //创建LeaderSelector，Master的选举器，入参包括客户端，Master路径，选中后执行的逻辑，LeaderSelectorListenerAdapter
        //执行完takeLeadership方法后，就释放Master权利了
        LeaderSelector leaderSelector = new LeaderSelector(CLIENT, PATH, new LeaderSelectorListenerAdapter() {
            @Override
            public void takeLeadership(CuratorFramework client) throws Exception {
                System.out.println("成为Master角色");
                TimeUnit.SECONDS.sleep(3);
                System.out.println("完成Master操作，释放Master权利");
            }
        });
        leaderSelector.autoRequeue();
        //选举器开始运行
        leaderSelector.start();

        TimeUnit.SECONDS.sleep(100);
    }
}
