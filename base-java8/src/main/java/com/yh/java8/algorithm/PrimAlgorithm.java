package com.yh.java8.algorithm;

import java.util.Arrays;

/**
 * 普利姆算法求解最小生成树
 *
 * @author yanhuan
 */
public class PrimAlgorithm {

    public static void main(String[] args) {
        char[] data = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int vertex = data.length;
        int[][] weight = new int[][]{
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000}};


        Graph graph = new Graph(vertex);
        MinTree minTree = new MinTree();
        minTree.createGraph(graph, vertex, data, weight);

        minTree.printGraph(graph);

        minTree.prim(graph, 1);
    }
}

class MinTree {

    /**
     * @param graph  图
     * @param vertex 顶点个数
     * @param data   各个顶点的值
     * @param weight 权值，边
     */
    public void createGraph(Graph graph, int vertex, char[] data, int[][] weight) {
        int i, j;
        for (i = 0; i < vertex; i++) {
            graph.data[i] = data[i];
            for (j = 0; j < vertex; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    public void printGraph(Graph graph) {
        for (int[] link : graph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * 普利姆算法生成最小生成树
     */
    public void prim(Graph graph, int v) {
        //标记顶点是否被访问过，为0表示没有访问过，为1表示访问过
        int visited[] = new int[graph.vertex];

        //把当前节点标记为已访问
        visited[v] = 1;
        //h1和h2表示两个顶点的下标
        int h1 = -1, h2 = -1;

        int minWeight = 10000;

        for (int k = 1; k < graph.vertex; k++) {
            //确定每一次生成的子图，和哪个节点的距离最近
            for (int i = 0; i < graph.vertex; i++) {
                //i表示访问过的节点， j表示未访问过的节点
                for (int j = 0; j < graph.vertex; j++) {
                    //遍历比较，找出最小权值
                    if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight) {
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }

            //找出一条边是最小的
            System.out.println("边<" + graph.data[h1] + "," + graph.data[h2] + ">, 权值: " + graph.weight[h1][h2]);
            visited[h2] = 1;
            minWeight = 10000;
        }
    }
}

class Graph {
    int vertex;
    char[] data;
    int[][] weight;

    public Graph(int vertex) {
        this.vertex = vertex;
        data = new char[vertex];
        weight = new int[vertex][vertex];
    }
}
