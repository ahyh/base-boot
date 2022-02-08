package com.yh.java8.al4.unionfind;

/**
 *
 */
public class QuickUnion extends AbstractUnionFind {

    public QuickUnion(int num) {
        super(num);
    }

    @Override
    public int find(int id) {
        // 找出分量的名称
        while (id != ids[id]) {
            id = ids[id];
        }
        return id;
    }

    @Override
    public void union(int p, int q) {
        // 将p & q的根节点统一
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }
        ids[pRoot] = qRoot;
        count--;
    }
}
