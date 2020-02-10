package com.yh.java8.datastructure.bst;

/**
 * 二叉排序树
 *
 * @param <T>
 * @author yanhuan
 */
public class BSortTree<T extends Comparable> {

    /**
     * 二叉排序树的根节点
     */
    private BstNode<T> root;

    public BstNode getRoot(){
        return this.root;
    }

    /**
     * 添加节点
     *
     * @param node 需要添加的节点
     */
    public void add(BstNode node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    /**
     * 查找当前节点
     *
     * @param value
     * @return
     */
    public BstNode search(T value) {
        if (this.root == null) {
            return null;
        }
        return this.root.search(value);
    }

    /**
     * 删除节点
     *
     * @param value 节点的值
     */
    public void delete(T value) {
        if (this.root == null) {
            return;
        }
        BstNode targetNode = search(value);
        //没有找到需要删除的节点
        if (targetNode == null) {
            return;
        }
        //如果当前二叉排序树只有一个root节点
        if (root.getLeft() == null && root.getRight() == null) {
            this.root = null;
            return;
        }
        //获取待删除节点的父节点
        BstNode parentNode = searchParent(value);
        //如果待删除的节点是叶子节点
        if (targetNode.getLeft() == null && targetNode.getRight() == null) {
            if (parentNode.getLeft() != null && parentNode.getLeft().getValue().compareTo(value) == 0) {
                //待删除的节点是左子节点
                parentNode.setLeft(null);
            }
            if (parentNode.getRight() != null && parentNode.getRight().getValue().compareTo(value) == 0) {
                //待删除的节点是右子节点
                parentNode.setRight(null);
            }
        } else if (targetNode.getLeft() != null && targetNode.getRight() != null) {
            //从右子树找最小的值
            Comparable minVal = delRightTreeMin(targetNode.getRight());
            targetNode.setValue(minVal);
        } else {
            //删除只有一颗子树的节点
            if (targetNode.getLeft() != null) {
                if (parentNode != null) {
                    //待删除的节点有左子节点
                    if (parentNode.getLeft().getValue().compareTo(value) == 0) {
                        //待删除节点是parentNode的左子节点
                        parentNode.setLeft(targetNode.getLeft());
                    } else {
                        //待删除的节点是parentNode的右子节点
                        parentNode.setRight(targetNode.getLeft());
                    }
                } else {
                    this.root = targetNode.getLeft();
                }
            } else {
                if (parentNode != null) {
                    //待删除的节点有右子节点
                    if (parentNode.getLeft().getValue().compareTo(value) == 0) {
                        parentNode.setLeft(targetNode.getRight());
                    } else {
                        parentNode.setRight(targetNode.getRight());
                    }
                } else {
                    this.root = targetNode.getRight();
                }
            }
        }
    }

    /**
     * 删除右子树最小节点
     */
    private T delRightTreeMin(BstNode<T> node) {
        BstNode<T> temp = node;
        while (temp.getLeft() != null) {
            temp = temp.getLeft();
        }
        delete(temp.getValue());
        return temp.getValue();
    }

    /**
     * 查找父节点
     *
     * @param value
     * @return
     */
    public BstNode searchParent(T value) {
        if (this.root == null) {
            return null;
        }
        return this.root.searchParent(value);
    }

    /**
     * 前序遍历二叉排序树
     */
    public void preOrder() {
        preOrder(this.root);
    }

    private void preOrder(BstNode bstNode) {
        if (bstNode != null) {
            System.out.println(bstNode);
            preOrder(bstNode.getLeft());
            preOrder(bstNode.getRight());
        }
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

    private void inOrder(BstNode bstNode) {
        if (bstNode != null) {
            inOrder(bstNode.getLeft());
            System.out.println(bstNode);
            inOrder(bstNode.getRight());
        }
    }

    /**
     * 二叉排序树删除节点
     * 1-叶子节点
     * 2-只有一颗子树的节点
     * 3-有两颗子树的节点
     */
    public void deleteNode() {

    }
}
