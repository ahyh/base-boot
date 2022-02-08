package com.yh.java8.al4.unionfind;

/**
 *
 */
public class QuickFind extends AbstractUnionFind {

    public QuickFind(int num) {
        super(num);
    }

    @Override
    public int find(int id) {
        return ids[id];
    }

    @Override
    public void union(int p, int q) {
        // 将p & q归并到相同的分量中
        int pId = find(p);
        int qId = find(q);

        // 如果p & q已经在相同的分量中则不需要采取任何行动
        if (pId == qId) {
            return;
        }

        // 将p的分量重新命名为q的名称
        for (int i = 0; i < ids.length; i++) {
            if (ids[i] == pId) {
                ids[i] = qId;
            }
        }
        count--;
    }

}
