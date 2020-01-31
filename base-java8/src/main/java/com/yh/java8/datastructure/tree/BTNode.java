package com.yh.java8.datastructure.tree;

/**
 * 二叉树的节点
 *
 * @param <T>
 * @author yanhuan
 */
public class BTNode<T> {

    /**
     * 节点编号
     */
    private int no;

    private T data;

    private BTNode<T> left;

    private BTNode<T> right;

    public BTNode() {

    }

    public BTNode(T data) {
        this.data = data;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public BTNode<T> getLeft() {
        return left;
    }

    public void setLeft(BTNode<T> left) {
        this.left = left;
    }

    public BTNode<T> getRight() {
        return right;
    }

    public void setRight(BTNode<T> right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "no: " + this.no + "data: " + this.data.toString();
    }
}
