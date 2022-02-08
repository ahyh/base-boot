package com.yh.java8.al4.leetcode.tree;

/**
 * 求树的最大深度
 */
public class MaxDeepthTree {

    public static void main(String[] args) {
        TreeNode<Integer> node = TreeNode.initIntTree();
        int deepth = deepth(node);
        System.out.println(deepth);

        TreeNode<String> node2 = TreeNode.initStrTree();
        int deepth1 = deepth(node2);
        System.out.println(deepth1);
    }

    public static int deepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return Math.max(deepth(node.left), deepth(node.right)) + 1;
    }
}
