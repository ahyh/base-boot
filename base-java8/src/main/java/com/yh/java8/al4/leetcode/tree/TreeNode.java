package com.yh.java8.al4.leetcode.tree;

public class TreeNode<T> {

    protected T value;

    protected TreeNode left;

    protected TreeNode right;

    public TreeNode(T value){
        this.value = value;
    }

    public static TreeNode<Integer> initIntTree(){
        TreeNode node1 = new TreeNode<Integer>(1);
        TreeNode node2A = new TreeNode<Integer>(2);
        TreeNode node2B = new TreeNode<Integer>(2);
        TreeNode node3A = new TreeNode<Integer>(3);
        TreeNode node3B = new TreeNode<Integer>(3);
        TreeNode node4A = new TreeNode<Integer>(4);
        TreeNode node4B = new TreeNode<Integer>(4);
        node2A.left = node3A;
        node2A.right = node4A;
        node2B.left = node4B;
        node2B.right = node3B;
        node1.left = node2A;
        node1.right = node2B;
        return node1;
    }

    /**
     *       A
     *      / \
     *     B   C
     *    /   / \
     *   D   E   F
     *  / \   \
     * G   H   I
     */
    public static TreeNode<String> initStrTree(){
        TreeNode nodeA = new TreeNode<String>("A");
        TreeNode nodeB = new TreeNode<String>("B");
        TreeNode nodeC = new TreeNode<String>("C");
        TreeNode nodeD = new TreeNode<String>("D");
        TreeNode nodeE = new TreeNode<String>("E");
        TreeNode nodeF = new TreeNode<String>("F");
        TreeNode nodeG = new TreeNode<String>("G");
        TreeNode nodeH = new TreeNode<String>("H");
        TreeNode nodeI = new TreeNode<String>("I");
        nodeE.right = nodeI;
        nodeC.left = nodeE;
        nodeC.right = nodeF;
        nodeD.left = nodeG;
        nodeD.right = nodeH;
        nodeB.left = nodeD;
        nodeA.left = nodeB;
        nodeA.right = nodeC;
        return nodeA;
    }
}
