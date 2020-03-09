package com.yh.java8.datastructure.tree;

/**
 * 二叉树类
 *
 * @param <T>
 * @author yanhuan
 */
public class BinaryTree<T> {

    /**
     * 根节点
     */
    private BTNode<T> root;

    private int sum;

    public BinaryTree() {
        this.root = new BTNode<>();
    }

    public BinaryTree(T data) {
        this.root = new BTNode<>(data);
    }

    public BinaryTree(BTNode btNode){
        this.root = btNode;
    }

    /**
     * 前序遍历
     * 先访问根节点
     * 在先序遍历左子树
     * 在先序遍历右子树
     */
    public void preOrder() {
        preOrder(this.root);
    }

    /**
     * 中序遍历
     * 先中序遍历左子树
     * 在访问根节点
     * 在中序遍历右子树
     */
    public void inOrder() {
        inOrder(this.root);
    }

    /**
     * 后序遍历
     * 先后序遍历左子树
     * 在后序遍历右子树
     * 在访问根节点
     */
    public void postOrder() {
        postOrder(this.root);
    }

    /**
     * 前序遍历查找如果找到返回BTNode
     *
     * @param no 根据编号查找
     * @return
     */
    public T preOrderSearch(int no) {
        return preOrderSearch(this.root, no);
    }

    private T preOrderSearch(BTNode<T> btNode, int no) {
        if (btNode.getNo() == no) {
            return btNode.getData();
        }
        if (btNode.getLeft() != null) {
            preOrderSearch(btNode.getLeft(), no);
        }
        if (btNode.getRight() != null) {
            preOrderSearch(btNode.getRight(), no);
        }
        return null;
    }

    /**
     * 中序遍历查找如果找到返回BTNode
     *
     * @param no 根据编号查找
     * @return
     */
    public T inOrderSearch(int no) {
        return inOrderSearch(this.root, no);
    }

    private T inOrderSearch(BTNode<T> btNode, int no) {
        if (btNode.getLeft() != null) {
            inOrderSearch(btNode.getLeft(), no);
        }
        if (btNode.getNo() == no) {
            return btNode.getData();
        }
        if (btNode.getRight() != null) {
            inOrderSearch(btNode.getRight(), no);
        }
        return null;
    }

    /**
     * 后序遍历查找如果找到返回BTNode
     *
     * @param no 根据编号查找
     * @return
     */
    public T postOrderSearch(int no) {
        return postOrderSearch(this.root, no);
    }

    private T postOrderSearch(BTNode<T> btNode, int no) {
        if (btNode.getLeft() != null) {
            postOrderSearch(btNode.getLeft(), no);
        }
        if (btNode.getRight() != null) {
            postOrderSearch(btNode.getRight(), no);
        }
        if (btNode.getNo() == no) {
            return btNode.getData();
        }
        return null;
    }

    private void preOrder(BTNode btNode) {
        if (btNode != null) {
            System.out.println(btNode);
            preOrder(btNode.getLeft());
            preOrder(btNode.getRight());
        }
    }

    private void inOrder(BTNode btNode) {
        if (btNode != null) {
            inOrder(btNode.getLeft());
            System.out.println(btNode);
            inOrder(btNode.getRight());
        }
    }

    private void postOrder(BTNode btNode) {
        if (btNode != null) {
            postOrder(btNode.getLeft());
            postOrder(btNode.getRight());
            System.out.println(btNode);
        }
    }

    /**
     * 叶子节点加和
     * @param btNode
     */
    public void preOrderAdd(BTNode btNode){
        if (btNode != null) {
            if(btNode.getLeft() != null){
                btNode.getLeft().setData(btNode.getData().toString() + btNode.getLeft().getData().toString());
            }
            if(btNode.getRight() != null){
                btNode.getRight().setData(btNode.getData().toString() + btNode.getRight().getData().toString());
            }
            if(btNode.getLeft() == null && btNode.getRight() == null){
                this.sum += Integer.valueOf(btNode.getData().toString());
            }
            preOrderAdd(btNode.getLeft());
            preOrderAdd(btNode.getRight());
        }
    }

    public static void main(String[] args) {
        BTNode root = new BTNode<>(5);
        BTNode<Integer> leftN1 = new BTNode<>(3);
        BTNode<Integer> rightN1 = new BTNode<>(4);
        BTNode<Integer> leftN2 = new BTNode<>(2);
        root.setLeft(leftN1);
        root.setRight(rightN1);
        leftN1.setLeft(leftN2);

        BinaryTree<Object> tree = new BinaryTree<>(root);

        tree.preOrderAdd(root);

        System.out.println();
    }

}
