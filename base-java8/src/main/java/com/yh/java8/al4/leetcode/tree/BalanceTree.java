package com.yh.java8.al4.leetcode.tree;

/**
 * 判断一颗二叉树是否是平衡二叉树，即任意一个节点的左右子树的高度差不大于1
 *
 * @author yanhuan
 */
public class BalanceTree {

    public static void main(String[] args) {
        TreeNode<Integer> node = TreeNode.initIntTree();
        boolean balanced = isBalanced(node);
        System.out.println(balanced);
    }

    public static boolean isBalanced(TreeNode node) {
        int check = check(node);
        return check != -1;
    }

    public static int check(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftDeepth = check(node.left);
        int rightDeepth = check(node.right);
        // 如果左右子树的高度大于1，则返回-1
        if (leftDeepth == -1 || rightDeepth == -1 || Math.abs(leftDeepth - rightDeepth) > 1) {
            return -1;
        }
        return Math.max(leftDeepth, rightDeepth) + 1;
    }
}
