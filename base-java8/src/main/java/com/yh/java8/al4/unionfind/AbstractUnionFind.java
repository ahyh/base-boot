package com.yh.java8.al4.unionfind;

/**
 * 连通性算法抽象类
 *
 * @author yanhuan
 */
public abstract class AbstractUnionFind {

    // 节点的索引
    protected int[] ids;

    // 分量
    protected int count;

    public AbstractUnionFind(int num) {
        this.count = num;
        ids = new int[num];
        // 初始化
        for (int i = 0; i < num; i++) {
            ids[i] = i;
        }
    }

    public int count() {
        return count;
    }

    // 确定索引为p & q的两个节点是否可以连通
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }


    public abstract int find(int id);

    // 在索引为p & q的两个节点上建立连接
    public abstract void union(int p, int q);
}
