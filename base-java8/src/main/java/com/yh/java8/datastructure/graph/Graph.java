package com.yh.java8.datastructure.graph;

import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 图
 *
 * @author yanhuan
 */
public class Graph<T> {

    /**
     * 边的数目
     */
    private int edgeSize;

    /**
     * 顶点集合
     */
    private List<T> vertexList;

    /**
     * 图对应的邻接矩阵
     */
    private int[][] edges;

    /**
     * 记录顶点是否被访问过
     */
    private boolean[] visited;

    public Graph(int n) {
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        edgeSize = 0;
        visited = new boolean[n];
    }

    public void insertVertex(T value) {
        Preconditions.checkNotNull(value);
        vertexList.add(value);
    }

    /**
     * @param v1     边的第一个顶点
     * @param v2     边的第二个顶点
     * @param weight 边的权值
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        edgeSize++;
    }

    /**
     * 返回顶点的数量
     */
    public int getNumOfVertex() {
        return vertexList.size();
    }

    /**
     * 返回边的数量
     */
    public int getEdgeSize() {
        return edgeSize;
    }

    /**
     * 返回下标对应的节点值
     */
    public T getValueByIndex(int index) {
        return vertexList.get(index);
    }

    /**
     * 获取边的权值
     */
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    public void printGraph() {
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * 深度优先遍历：Depth First Search
     * 从初始访问节点出发，初始访问节点可能有多个邻接节点，深度优先遍历的策略就是首先
     * 访问第一个邻接节点，然后再以这个被访问的邻接节点作为初始节点，访问它的第一个邻
     * 接节点，可以这样理解：每次都在访问完当前节点后首先访问当前节点的第一个邻接节点
     * 深度优先遍历是优先往纵向挖掘深入，而不是对一个节点的所有邻接节点进行横向访问
     * <p>
     * 算法步骤：
     * 1-访问初始节点v，并标记节点v为已访问
     * 2-查找节点v的第一个邻接节点w
     * 3-若w存在，则继续执行4，如果w不存在，则回到第1步，在从v的下一个节点继续
     * 4-若w未被访问过，对w进行深度优先遍历递归
     * 5-查找接地那v的w邻接节点的下一个邻接节点，转到步骤3
     */
    public void dfs(boolean[] visited, int i) {
        System.out.print(getValueByIndex(i) + "->");
        visited[i] = true;
        //查询节点i的第一个邻接节点
        int w = getFirstNeighbor(i);
        while (w != -1) {
            if (!visited[w]) {
                dfs(visited, w);
            }
            //如果w已经被访问过，查找邻接节点的下一个节点
            w = getNextNeighbor(i, w);
        }
    }

    /**
     * 遍历所有的节点，进行dfs
     */
    public void dfs() {
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!visited[i]) {
                dfs(visited, i);
            }
        }
    }

    /**
     * 返回index节点的第一个邻接节点的下标
     */
    private int getFirstNeighbor(int index) {
        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[index][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    /**
     * 根据前一个邻接节点的下标来获取下一个邻接节点
     */
    private int getNextNeighbor(int v1, int v2) {
        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if (edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    /**
     * 类似于一个分层搜索的过程，广度优先遍历需要使用一个队列以保存访问过的节点的顺序
     * 以便按照这个顺序来访问这些节点的邻接节点
     * 算法步骤：
     * 1-访问初始节点v并标记节点v为已访问
     * 2-节点v入队列
     * 3-当队列非空时，继续执行，否则算法结束
     * 4-出队列，取得队头节点u
     * 5-查找节点u的第一个邻接节点w
     * 6-若节点u的邻接节点w不存在，则转到步骤3，否则循环执行以下3个步骤
     * 6.1-若节点w尚未被访问，则访问节点w并标记为已访问
     * 6.2-节点w入队列
     * 6.3-查找节点u的继w邻接节点后的下一个邻接节点w，转到步骤6
     */
    public void bfs(boolean[] visited, int i) {
        int u; //表示队列的头节点对应的下标
        int w; //表示邻接节点的下标
        LinkedList queue = new LinkedList<>();
        //访问节点
        System.out.print(getValueByIndex(i) + "=>");
        visited[i] = true;
        queue.addLast(i);
        while (!queue.isEmpty()) {
            //取出队列的头节点的下标
            u = (int) queue.removeFirst();
            //获取第一个邻接节点的下标w
            w = getFirstNeighbor(u);
            while (w != -1) {
                if (!visited[w]) {
                    System.out.print(getValueByIndex(w) + "=>");
                    visited[w] = true;
                    queue.addLast(w);
                }
                //如果w被访问过
                w = getNextNeighbor(u, w);
            }
        }
    }

    public void bfs() {
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!visited[i]) {
                bfs(visited, i);
            }
        }
    }

}
