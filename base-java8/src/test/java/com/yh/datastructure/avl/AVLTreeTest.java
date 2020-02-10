package com.yh.datastructure.avl;

import com.yh.java8.datastructure.bst.BstNode;
import com.yh.java8.datastructure.bst.avl.AVLTree;
import org.junit.Test;

public class AVLTreeTest {

    @Test
    public void testAVL() {
        int[] arr = {4, 3, 6, 5, 7, 8};
        AVLTree avlTree = new AVLTree();
        for (int value : arr) {
            avlTree.add(new BstNode(value));
        }
        avlTree.inOrder();
    }
}
