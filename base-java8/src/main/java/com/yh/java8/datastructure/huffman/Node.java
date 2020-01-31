package com.yh.java8.datastructure.huffman;

/**
 * huffman树节点
 *
 * @param <T>
 * @author yanhuan
 */
public class Node implements Comparable<Node> {

    /**
     * 使用int值作为赫夫曼树节点的权值类型
     */
    private int data;

    /**
     * 左子节点
     */
    private Node left;

    /**
     * 右子节点
     */
    private Node right;

    public Node(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return String.valueOf(this.data);
    }

    @Override
    public int compareTo(Node o) {
        return this.data - o.data;
    }
}
