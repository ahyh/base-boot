package com.yh.java8.algorithm;

/**
 * 克鲁斯卡尔算法
 *
 * @author yanhuan
 */
public class KurskalCase {

    private int edgeNum;
    private char[] vertex;
    private int[][] matrix;

    private static final int INF = Integer.MAX_VALUE;

    public KurskalCase(char[] vertex, int[][] matrix) {
        int vlen = vertex.length;
        //初始化顶点
        this.vertex = new char[vlen];
        for (int i = 0; i < vlen; i++) {
            this.vertex[i] = vertex[i];
        }
        this.matrix = new int[vlen][vlen];
        for (int i = 0; i < vlen; i++) {
            for (int j = 0; j < vlen; j++) {
                this.matrix[i][j] = matrix[i][j];
                if (matrix[i][j] != INF) {
                    edgeNum++;
                }
            }
        }
    }

    public void kruskal() {
        int index = 0;
        //用于保存已有最小生成树中每个顶点在最小生成树中的终点
        int[] ends = new int[edgeNum];
        Edge[] rets = new Edge[edgeNum];

        //获取图中所有边的集合
        Edge[] edges = getEdges();

        //按照边的权值大小进行排序
        sortEdges(edges);

        //遍历edges，将边添加到最小生成树中，判断是否构成回路
        for (int i = 0; i < edgeNum; i++) {
            //获取到第i条边的第一个顶点
            int p1 = getPosition(edges[i].start);
            int p2 = getPosition(edges[i].end);

            //获取p1这个顶点在已有最小生成树中的顶点
            int m = getEnd(ends, p1);
            int n = getEnd(ends, p2);
            if (m != n) {
                //没有构成回路
                ends[m] = n;
                rets[index++] = edges[i];
            }
        }

        //rets就是最小生成树

    }

    public void print() {
        System.out.println("邻接矩阵: ");
        for (int i = 0; i < vertex.length; i++) {
            for (int j = 0; j < vertex.length; j++) {
                System.out.printf("%15d\t", matrix[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * 对边进行排序
     *
     * @param edges
     */
    public void sortEdges(Edge[] edges) {
        for (int i = 0; i < edges.length; i++) {
            for (int j = 0; j < edges.length - 1 - i; j++) {
                if (edges[j].weight > edges[j + 1].weight) {
                    Edge tempEdge = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = tempEdge;
                }
            }
        }
    }

    /**
     * 返回顶点对应的下标
     *
     * @param c
     * @return
     */
    public int getPosition(char c) {
        for (int i = 0; i < vertex.length; i++) {
            if (vertex[i] == c) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 获取图中的边
     * 通过matrix来获取边的集合
     *
     * @return
     */
    public Edge[] getEdges() {
        int index = 0;
        Edge[] edges = new Edge[edgeNum];
        for (int i = 0; i < vertex.length; i++) {
            for (int j = i + 1; j < vertex.length; j++) {
                if (matrix[i][j] != INF) {
                    edges[index++] = new Edge(vertex[i], vertex[j], matrix[i][j]);
                }
            }
        }
        return edges;
    }

    /**
     * 获取下标为i的顶点的终点，用于判断两个顶点的终点是否相同
     *
     * @param ends 记录了各个顶点对应的终点有哪些，ends数组是在遍历的过程中逐步形成的
     * @param i    表示传入的顶点对应的下标
     * @return 返回的是下标为i的这个顶点对应的终点的下标
     */
    private int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }

}

class Edge {

    char start;
    char end;
    int weight;

    public Edge(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

}
