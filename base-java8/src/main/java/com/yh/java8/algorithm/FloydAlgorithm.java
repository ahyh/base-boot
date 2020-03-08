package com.yh.java8.algorithm;

import java.util.Arrays;

/**
 * Floyd算法求解各个顶点到其他顶点的最短距离
 *
 * @author yanhuan
 */
public class FloydAlgorithm {

    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;
        matrix[0] = new int[]{0, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, 0, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, 0, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, 0, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, 0, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, 0, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, 0};

        FGraph graph = new FGraph(vertex.length, matrix, vertex);
        graph.show();

        graph.floyd();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        graph.show();

    }

}

/**
 * Floyd算法图
 *
 * @author yanhuan
 */
class FGraph {

    private char[] vertex;
    private int[][] dis;
    private int[][] pre;

    /**
     * @param length 顶点个数
     * @param matrix 邻接矩阵
     * @param vertex 顶点数组
     */
    public FGraph(int length, int[][] matrix, char[] vertex) {
        this.vertex = vertex;
        this.dis = matrix;
        this.pre = new int[length][length];
        //对pre数组进行初始化，pre存放的是前驱顶点的下标
        for (int i = 0; i < length; i++) {
            Arrays.fill(pre[i], i);
        }
    }

    public void show() {
        for (int k = 0; k < dis.length; k++) {
            //输出pre数组
            for (int i = 0; i < dis.length; i++) {
                System.out.print(vertex[pre[k][i]] + "\t");
            }
            //输出dis数组的一行数据
            for (int i = 0; i < dis.length; i++) {
                System.out.print(vertex[k] + "=>" + vertex[i] + dis[k][i] + "\t");
            }
            System.out.println();
            System.out.println();
        }
    }

    public void floyd() {
        //定义len保存距离
        int len = 0;
        for (int k = 0; k < dis.length; k++) {
            //从i顶点开始出发
            for (int i = 0; i < dis.length; i++) {
                for (int j = 0; j < dis.length; j++) {
                    //从i开始经过k，到j的距离
                    len = dis[i][k] + dis[k][j];
                    if (len < dis[i][j]) {
                        //如果经过k中间节点的，从i到j的距离小于直连的距离，则替换
                        dis[i][j] = len;
                        //更新前驱节点
                        pre[i][j] = pre[k][j];
                    }
                }
            }
        }
    }

}