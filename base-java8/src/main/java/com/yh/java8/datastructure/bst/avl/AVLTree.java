package com.yh.java8.datastructure.bst.avl;

import com.yh.java8.datastructure.bst.BSortTree;

/**
 * 平衡二叉树
 *
 * @author yanhuan
 */
public class AVLTree extends BSortTree {

    /**
     * 获取AVL树的高度
     *
     * @return AVL树的高度
     */
    public int height() {
        return getRoot().height();
    }


}
