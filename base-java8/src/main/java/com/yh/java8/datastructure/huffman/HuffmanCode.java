package com.yh.java8.datastructure.huffman;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 赫夫曼编码
 *
 * @author yanhuan
 */
public class HuffmanCode {

    private static Map<Byte, String> HUFFMAN_CODE_MAP = new HashMap<>();

    private static StringBuilder sb = new StringBuilder();

    /**
     * 赫夫曼解码
     *
     * @param bytes          待解码的字节数组
     * @param huffmanCodeMap 赫夫曼编码树
     * @return 解码后的字符串
     */
    public static String unzip(byte[] bytes, Map<Byte, String> huffmanCodeMap) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            boolean flag = (i == bytes.length - 1);
            sb.append(byte2String(bytes[i], !flag));
        }
        System.out.println(sb.toString());
        //反转huffmanCodeMap
        Map<String, Byte> inverseMap = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodeMap.entrySet()) {
            inverseMap.put(entry.getValue(), entry.getKey());
        }
        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < sb.length(); ) {
            int count = 1;
            boolean flag = true;
            Byte b = null;
            while (flag) {
                String substr = sb.substring(i, i + count);
                b = inverseMap.get(substr);
                if (b == null) {
                    count++;
                } else {
                    flag = false;
                }
            }
            list.add(b);
            i += count;
        }
        //当for结束后，list中就存放了所有的字符
        byte[] resultBytes = new byte[list.size()];
        int j = 0;
        for (Byte b : list) {
            resultBytes[j] = b;
            j++;
        }
        return new String(resultBytes);
    }

    /**
     * 获取字节的二进制串
     *
     * @param b    元字节数据
     * @param flag 是否需要补高位
     * @return 一个字节对应的二进制串
     */
    private static String byte2String(byte b, boolean flag) {
        int temp = b;
        if (flag) {
            temp |= 256;
        }
        String s = Integer.toBinaryString(temp);
        if (flag) {
            return s.substring(s.length() - 8);
        } else {
            return s;
        }
    }

    /**
     * 对bytes进行huffman编码
     *
     * @param bytes          源字符串的字节数组
     * @param huffmanCodeMap 赫夫曼编码树
     * @return 编码后的字节数组，8位对应一个byte，放入字节数组中
     */
    public static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodeMap) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(huffmanCodeMap.get(b));
        }
        System.out.println(sb.toString());
        //将字符串转换为字节数组
        int len = 0;
        if (sb.length() % 8 == 0) {
            len = sb.length() / 8;
        } else {
            len = sb.length() / 8 + 1;
        }
        byte[] result = new byte[len];
        int index = 0;
        //步长为8，每8位生成一个byte
        for (int i = 0; i < sb.length(); i += 8) {
            String subStr;
            if (i + 8 > sb.length()) {
                //最后不足8位时，取所有剩下的子串
                subStr = sb.substring(i);
            } else {
                subStr = sb.substring(i, i + 8);
            }
            result[index] = (byte) Integer.parseInt(subStr, 2);
            index++;
        }
        return result;
    }

    /**
     * 获取赫夫曼树
     *
     * @param rootNode 根节点
     * @return 赫夫曼编码
     */
    public static Map<Byte, String> getHuffmanCodeMap(Node rootNode) {
        if (rootNode == null) {
            return null;
        }
        getHuffmanCode(rootNode, "", sb);
        return HUFFMAN_CODE_MAP;
    }

    /**
     * 获取赫夫曼编码
     *
     * @param node 节点
     * @param code 编码，向左为0，向右为1
     * @param sb   编码串
     */
    public static void getHuffmanCode(Node node, String code, StringBuilder sb) {
        StringBuilder newSb = new StringBuilder(sb);
        newSb.append(code);
        if (node != null) {
            //非叶子节点
            if (node.getData() == null) {
                //递归左子节点
                getHuffmanCode(node.getLeft(), "0", newSb);
                //递归右子节点
                getHuffmanCode(node.getRight(), "1", newSb);
            } else {
                //叶子节点
                HUFFMAN_CODE_MAP.put(node.getData(), newSb.toString());
            }
        }
    }

    /**
     * 根据Node集合创建赫夫曼树
     *
     * @param nodeList Node集合
     * @return 赫夫曼树
     */
    public static Node createHuffmanTree(List<Node> nodeList) {
        if (nodeList == null || nodeList.size() == 0) {
            return null;
        }
        while (nodeList.size() > 1) {
            Collections.sort(nodeList);
            Node leftNode = nodeList.get(0);
            Node rightNode = nodeList.get(1);
            Node parentNode = new Node(null, leftNode.getWeight() + rightNode.getWeight());
            parentNode.setLeft(leftNode);
            parentNode.setRight(rightNode);
            nodeList.remove(leftNode);
            nodeList.remove(rightNode);
            nodeList.add(parentNode);
        }
        return nodeList.get(0);
    }

    /**
     * 根据字符串返回Node集合，返回每个byte出现的次数，用于构建Huffman树
     *
     * @param str 需要编码的字符串
     * @return Node集合
     */
    public static List<Node> getNodes(String str) {
        if (StringUtils.isBlank(str)) {
            throw new RuntimeException("input should not empty");
        }
        byte[] bytes = str.getBytes();
        Multiset<Byte> multiset = HashMultiset.create();
        for (byte b : bytes) {
            multiset.add(b);
        }
        Set<Multiset.Entry<Byte>> entries = multiset.entrySet();
        List<Node> nodeList = new ArrayList<>();
        for (Multiset.Entry<Byte> entry : entries) {
            nodeList.add(new Node(entry.getElement(), entry.getCount()));
        }
        return nodeList;
    }

    /**
     * 前序遍历赫夫曼树
     *
     * @param btNode 当前节点
     */
    private void preOrder(Node btNode) {
        if (btNode != null) {
            System.out.println(btNode);
            preOrder(btNode.getLeft());
            preOrder(btNode.getRight());
        }
    }

    public static class Node implements Comparable<Node> {
        /**
         * 以ASCII码的形式存放的数据本身
         */
        private Byte data;

        /**
         * 出现的次数
         */
        private int weight;

        private Node left;

        private Node right;

        public Node(Byte data, int weight) {
            this.data = data;
            this.weight = weight;
        }

        public Byte getData() {
            return data;
        }

        public void setData(Byte data) {
            this.data = data;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }

        @Override
        public String toString() {
            return "Node: [data=" + this.data + ", weight=" + this.weight + "]";
        }
    }
}
