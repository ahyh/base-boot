package com.yh.java8.datastructure.bst;

/**
 * 二叉排序树的节点
 *
 * @param <T>
 * @author yanhuan
 */
public class BstNode<T extends Comparable> {

    private T value;

    private BstNode left;

    private BstNode right;

    public BstNode(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public BstNode getLeft() {
        return left;
    }

    public void setLeft(BstNode left) {
        this.left = left;
    }

    public BstNode getRight() {
        return right;
    }

    public void setRight(BstNode right) {
        this.right = right;
    }

    /**
     * 添加节点
     *
     * @param node
     */
    public void add(BstNode node) {
        if (node == null) {
            return;
        }
        if (node.value.compareTo(this) < 0) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
    }

    /**
     * 查找节点
     *
     * @param t
     * @return
     */
    public BstNode search(T t) {
        if (t == null) {
            return null;
        }
        if (this.value.compareTo(t) == 0) {
            return this;
        } else if (this.value.compareTo(t) > 0) {
            if (this.left != null) {
                this.left.search(t);
            } else {
                return null;
            }
        } else {
            if (this.right != null) {
                this.right.search(t);
            } else {
                return null;
            }
        }
        return null;
    }


    /**
     * 查找父节点
     *
     * @param t
     * @return
     */
    public BstNode searchParent(T t) {
        if ((this.left != null && this.left.value.compareTo(t) == 0)
                || (this.right != null && this.right.value.compareTo(t) == 0)) {
            return this;
        } else {
            if (this.value.compareTo(t) > 0 && this.left != null) {
                return this.left.searchParent(t);
            } else if (this.value.compareTo(t) < 0 && this.right != null) {
                return this.right.searchParent(t);
            } else {
                //没有找到父节点
                return null;
            }
        }
    }

    @Override
    public String toString() {
        return "BstNode{" +
                "value=" + value +
                '}';
    }
}
