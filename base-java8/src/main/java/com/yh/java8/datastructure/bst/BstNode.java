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
     * 返回左子树的高度
     *
     * @return 左子树的高度
     */
    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return left.height();
    }

    /**
     * 返回右子树的高度
     *
     * @return 右子树的高度
     */
    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.height();
    }

    /**
     * 返回当前节点的高度
     *
     * @return 当前节点
     */
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    /**
     * AVL树左旋
     * 如果右子树的高度 - 左子树的高度 > 1
     */
    private void leftRotate() {
        //1-创建新的节点，以当前节点的值
        BstNode<T> newNode = new BstNode<>(value);
        //2-把新节点的左子树设置为当前节点的左子树
        newNode.setLeft(left);
        //3-把新节点的右子树设置为当前节点右子树的左子树
        newNode.setRight(right.left);
        //4-把当前节点的值替换为右子节点的值
        this.value = (T) right.value;
        //5-把当前节点的右子树设置为当前节点右子树的右子树
        right = right.right;
        //6-把当前节点的左子树设置为新的节点
        left = newNode;
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
        if (node.value.compareTo(this.value) < 0) {
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
        //添加完节点后如果右子树的高度-左子树的高度 > 1, 则左旋
        if (rightHeight() - leftHeight() > 1) {
            if (right != null && right.rightHeight() < right.leftHeight()) {
                leftRotate();
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
