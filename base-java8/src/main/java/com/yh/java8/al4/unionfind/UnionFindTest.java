package com.yh.java8.al4.unionfind;

import com.google.common.collect.Lists;

import java.util.List;

public class UnionFindTest {

    private static final List<Union> UNION_LIST = Lists.newArrayList(
            new Union(4, 3),
            new Union(3, 8),
            new Union(6, 5),
            new Union(9, 4),
            new Union(2, 1),
            new Union(8, 9),
            new Union(5, 0),
            new Union(7, 2),
            new Union(6, 1),
            new Union(1, 0),
            new Union(6, 7)
    );

    public static void main(String[] args) {
//        AbstractUnionFind quickFind = new QuickFind(10);
        AbstractUnionFind quickFind = new QuickUnion(10);
        for (Union union : UNION_LIST) {
            quickFind.union(union.getP(), union.getQ());
        }
        System.out.println(quickFind.connected(1,2));
    }

    public static class Union {
        private int p;
        private int q;

        public Union(int p, int q) {
            this.p = p;
            this.q = q;
        }

        public int getP() {
            return p;
        }

        public void setP(int p) {
            this.p = p;
        }

        public int getQ() {
            return q;
        }

        public void setQ(int q) {
            this.q = q;
        }
    }
}
