package com.yh.java8.algorithm;

import java.util.Arrays;

/**
 * Dijkstra算法求解最短路径
 *
 * @author yanhuan
 */
public class Dijkstra {

    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;
        matrix[0] = new int[]{N, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, N, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, N, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, N, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, N, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, N, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, N};

        DijkstraGraph graph = new DijkstraGraph(vertex, matrix);

        graph.printGraph();

        graph.dijkstra(6);


    }


}

class DijkstraGraph {
    private char[] vertex;
    private int[][] matrix;
    private VisitedVertex visitedVertex;

    public DijkstraGraph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }

    public void printGraph() {
        for (int[] link : matrix) {
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * @param index 出发顶点对应的下标
     */
    public void dijkstra(int index) {
        visitedVertex = new VisitedVertex(vertex.length, index);
        //更新下标index顶点到周围顶点的距离和前驱顶点
        update(index);
        for (int j = 1; j < vertex.length; j++) {
            //选择并返回新的访问顶点
            index = visitedVertex.updateArr();
            //更新index顶点到周围顶点的距离和前驱节点
            update(index);
        }
        visitedVertex.show();
    }

    /**
     * 更新index下标顶点到周围顶点的距离和周围顶点的前驱顶点
     *
     * @param index
     */
    public void update(int index) {
        int len = 0;
        for (int j = 0; j < matrix[index].length; j++) {
            //len: 出发顶点到index顶点的距离 + 从index到j顶点的距离的和
            len = visitedVertex.getDis(index) + matrix[index][j];
            //如果j顶点没有被访问过，且len小于出发顶点到j顶点的距离，就需要更新
            if (!visitedVertex.indexVisited(j) && len < visitedVertex.getDis(j)) {
                //更新j顶点的前驱为index
                visitedVertex.updatePre(j, index);
                //更新出发顶点到j的距离
                visitedVertex.updateDis(j, len);
            }
        }
    }


}

/**
 * 已访问顶点的集合
 */
class VisitedVertex {

    public int[] visitedArr;

    public int[] preVisited;

    public int[] dis;

    public VisitedVertex(int length, int index) {
        this.visitedArr = new int[length];
        this.preVisited = new int[length];
        this.dis = new int[length];
        Arrays.fill(dis, 65535);
        //设置出发顶点被访问过
        this.visitedArr[index] = 1;
        //设置出发顶点的访问距离为0
        this.dis[index] = 0;
    }

    /**
     * 判断下标为index的顶点是否访问过，访问过返回true，否则返回false
     *
     * @param index
     * @return
     */
    public boolean indexVisited(int index) {
        return visitedArr[index] == 1;
    }

    /**
     * 更新出发顶点到下标为index顶点的距离
     *
     * @param index
     * @param len
     */
    public void updateDis(int index, int len) {
        dis[index] = len;
    }

    /**
     * 更新pre这个顶点的前驱顶点为index顶点
     *
     * @param pre
     * @param index
     */
    public void updatePre(int pre, int index) {
        preVisited[pre] = index;
    }

    /**
     * 返回出发顶点到index顶点的距离
     *
     * @param index
     */
    public int getDis(int index) {
        return dis[index];
    }

    /**
     * 继续选择并返回新的访问顶点
     *
     * @return
     */
    public int updateArr() {
        int min = 65535, index = 0;
        for (int i = 0; i < visitedArr.length; i++) {
            if (visitedArr[i] == 0 && dis[i] < min) {
                min = dis[i];
                index = i;
            }
        }
        //更新index顶点被访问过
        visitedArr[index] = 1;
        return index;
    }

    /**
     * 将3个定义的数组显示出来
     */
    public void show() {
        System.out.println("===========================================================");
        for (int i = 0; i < visitedArr.length; i++) {
            if (i != visitedArr.length - 1) {
                System.out.print(visitedArr[i] + " => ");
            } else {
                System.out.print(visitedArr[i]);
            }
        }

        System.out.println();
        System.out.println("===========================================================");
        for (int i = 0; i < preVisited.length; i++) {
            if (i != preVisited.length - 1) {
                System.out.print(preVisited[i] + " => ");
            } else {
                System.out.print(preVisited[i]);
            }
        }

        System.out.println();
        System.out.println("===========================================================");
        for (int i = 0; i < dis.length; i++) {
            if (i != dis.length - 1) {
                System.out.print(dis[i] + " => ");
            } else {
                System.out.print(dis[i]);
            }
        }
    }
}
