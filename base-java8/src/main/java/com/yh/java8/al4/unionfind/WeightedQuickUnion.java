package com.yh.java8.al4.unionfind;

public class WeightedQuickUnion extends AbstractUnionFind {

    private int[] sz; //各个根节点所对应的分量的大小

    public WeightedQuickUnion(int num) {
        super(num);
        sz = new int[num];
        for (int i = 0; i < num; i++) {
            sz[i] = 1;
        }
    }

    @Override
    public int find(int id) {
        while (id != ids[id]) {
            id = ids[id];
        }
        return id;
    }

    @Override
    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);
        if (i == j) {
            return;
        }
        // 将小树的根节点连接到大树的根节点
        if (sz[i] < sz[j]) {
            ids[i] = j;
            sz[j] += sz[i];
        } else {
            ids[j] = i;
            sz[i] += sz[j];
        }
        count--;
    }
}
