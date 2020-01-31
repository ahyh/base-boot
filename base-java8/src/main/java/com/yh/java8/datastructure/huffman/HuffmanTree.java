package com.yh.java8.datastructure.huffman;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 赫夫曼树
 *
 * @author yanhuan
 */
public class HuffmanTree {

    /**
     * 赫夫曼树的根节点
     */
    private Node root;

    /**
     * 通过一个数组来构建一个赫夫曼树
     *
     * @param elements 构建赫夫曼树的元素数组
     */
    public HuffmanTree(int[] elements) {
        //1-根据elements创建Node数组
        if (elements == null || elements.length == 0) {
            throw new RuntimeException("elements cannot empty");
        }
        List<Node> nodeList = new ArrayList<>();
        for (int data : elements) {
            nodeList.add(new Node(data));
        }
        while (nodeList.size() > 1) {
            //2-对NodeList进行排序
            Collections.sort(nodeList);
            //3-找出前二个
            Node nodeLeft = nodeList.get(0);
            Node nodeRight = nodeList.get(1);
            Node parentNode = new Node(nodeLeft.getData() + nodeRight.getData());
            //4-移除节点
            parentNode.setLeft(nodeLeft);
            parentNode.setRight(nodeRight);
            nodeList.remove(nodeLeft);
            nodeList.remove(nodeRight);
            nodeList.add(parentNode);
        }
        this.root = nodeList.get(0);
    }

    /**
     * 前序遍历赫夫曼树
     */
    public void printByPreOrder() {
        if (this.root != null) {
            preOrder(this.root);
        }
    }

    private void preOrder(Node btNode) {
        if (btNode != null) {
            System.out.println(btNode);
            preOrder(btNode.getLeft());
            preOrder(btNode.getRight());
        }
    }
}
