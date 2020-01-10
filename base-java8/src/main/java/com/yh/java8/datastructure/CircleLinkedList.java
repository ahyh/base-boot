package com.yh.java8.datastructure;

/**
 * 循环链表
 *
 * @author yanhuan
 */
public class CircleLinkedList {

    private Node first = null;

    /**
     * 约瑟夫问题
     *
     * @param startNo
     * @param countNum
     */
    public void josepfu(int startNo, int countNum) {
        int size = size();
        if (size == 0 || startNo < 1 || startNo > size) {
            throw new RuntimeException("invalid params");
        }
        //helper指向最后一个节点
        Node helper = first;
        while (true) {
            if (helper.next == first) {
                break;
            }
            helper = helper.next;
        }

        for (int i = 0; i < startNo - 1; i++) {
            first = first.next;
            helper = helper.next;
        }

        //循环操作，移动startNo-1次，出圈
        while (true) {
            if (helper == first) {
                break;
            }
            for (int j = 0; j < countNum - 1; j++) {
                //first指向的节点就是要出圈的节点
                first = first.next;
                helper = helper.next;
            }
            System.out.println("移除的节点：" + first);
            first = first.next;
            helper.next = first;
        }
        System.out.println("最后的节点：" + first);
    }

    public void deleteNode(int no) {
        if (first == null || no < 1 || no > size()) {
            throw new RuntimeException("invalid params");
        }
        Node curNode = first;
        Node pre = null, next = null;
        while (curNode.next != first) {
            if (curNode.next.no == no) {
                pre = curNode;
                curNode = curNode.next;
                break;
            }
            curNode = curNode.next;
        }
        next = curNode.next;
        if (pre == null) {
            pre = curNode;
            next = next.next;
            pre.next = next;
            this.first = pre.next;
        } else {
            curNode = null;
            pre.next = next;
        }
    }

    public int size() {
        if (first == null) {
            return 0;
        }
        int sum = 0;
        Node curNode = first;
        while (curNode.next != first) {
            sum++;
            curNode = curNode.next;
        }
        return sum;
    }

    /**
     * 遍历
     */
    public void printList() {
        if (first == null) {
            return;
        }
        Node curNode = first;
        do {
            System.out.println(curNode);
            curNode = curNode.next;
        } while (curNode != first);
    }

    /**
     * 按照编号添加Node
     *
     * @param node 待添加的node
     */
    public void addNode(Node node) {
        //如果是添加的第一个节点
        if (first == null) {
            first = node;
            first.next = first;
        } else {
            Node curNode = first;
            Node nextNode = curNode.next;
            //如果只有一个节点
            if (curNode == nextNode) {
                if (nextNode.no > node.no) {
                    first = node;
                    first.next = curNode;
                    curNode.next = first;
                } else {
                    curNode.next = node;
                    node.next = first;
                }
            } else {
                //多个节点
                while (nextNode != first) {
                    if (curNode.no < node.no && nextNode.no > node.no) {
                        break;
                    }
                    curNode = nextNode;
                    nextNode = nextNode.next;
                }
                if (nextNode == first) {
                    curNode.next = node;
                    node.next = first;
                } else {
                    curNode.next = node;
                    node.next = nextNode;
                }
            }
        }
    }

    public static class Node {
        private int no;
        private String name;
        private Node next;

        public Node(int no, String name) {
            this.no = no;
            this.name = name;
        }

        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "no=" + no +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
