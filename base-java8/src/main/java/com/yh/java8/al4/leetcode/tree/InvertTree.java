package com.yh.java8.al4.leetcode.tree;

/**
 * 翻转二叉树
 */
public class InvertTree {

    public static void main(String[] args) {
        TreeNode<Integer> node = TreeNode.initIntTree();
        TreeNode invert = invert(node);
        System.out.println(invert);
    }

    /**
     * 翻转一颗二叉树
     */
    private static TreeNode invert(TreeNode node){
        if (node == null) {
            return null;
        }
        invert(node.left);
        invert(node.right);
        TreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;
        return node;
    }
}
