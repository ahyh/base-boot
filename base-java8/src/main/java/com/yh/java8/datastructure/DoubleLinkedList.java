package com.yh.java8.datastructure;

/**
 * 双向链表
 *
 * @author yanhuan
 */
public class DoubleLinkedList {

    private Node header = new Node(0, "", "");

    private Node tail = header;

    public boolean isEmpty() {
        return header.next == null;
    }

    /**
     * delete node
     *
     * @param no 节点编号
     */
    public void delete(int no) {
        if (no <= 0) {
            throw new RuntimeException("invalid no");
        }
        //if tail.no == no
        if (tail.no == no) {
            Node delNode = tail;
            Node temp = delNode.pre;
            temp.next = null;
            delNode = null;
            this.tail = temp;
        }
        Node curNode = header.next;
        while (curNode != null) {
            if (curNode.no == no) {
                Node preNode = curNode.pre;
                Node nextNode = curNode.next;
                preNode.next = nextNode;
                nextNode.pre = preNode;
                curNode = null;
                break;
            }
            curNode = curNode.next;
        }
    }

    /**
     * 根据node.no更新node
     *
     * @param node 待更新的node
     */
    public void updateNode(Node node) {
        if (node == null) {
            return;
        }
        Node curNode = header.next;
        while (curNode != null) {
            if (curNode.no == node.no) {
                curNode.setName(node.getName());
                curNode.setNickName(node.getNickName());
                break;
            }
            curNode = curNode.next;
        }
    }

    /**
     * 按照no插入Node
     *
     * @param node 待插入的Node
     */
    public void addNode(Node node) {
        if (node == null) {
            return;
        }
        Node curNode = header;
        Node nextNode = header.next;
        while (nextNode != null) {
            if (curNode.no < node.no && nextNode.no > node.no) {
                break;
            }
            curNode = nextNode;
            nextNode = curNode.next;
        }
        //构建curNode、nextNode、node的pre&next关系
        if (nextNode != null) {
            curNode.next = node;
            node.next = nextNode;
            nextNode.pre = node;
            node.pre = curNode;
        } else {
            curNode.next = node;
            node.next = nextNode;
            node.pre = curNode;
            tail = node;
        }
    }

    public void printList() {
        if (header.next == null) {
            System.out.println(header);
            return;
        }
        Node curNode = header.next;
        while (curNode != null) {
            System.out.println(curNode);
            curNode = curNode.next;
        }
    }

    public void printList(boolean revert) {
        if (revert) {
            printList();
        }
        if (header.next == null) {
            System.out.println(header);
            return;
        }
        Node curNode = tail;
        while (curNode != null && curNode != header) {
            System.out.println(curNode);
            curNode = curNode.pre;
        }
    }

    public static class Node {
        private int no;
        private String name;
        private String nickName;
        private Node pre;
        private Node next;

        public Node() {

        }

        public Node(int no, String name, String nickName) {
            this.no = no;
            this.name = name;
            this.nickName = nickName;
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

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public Node getPre() {
            return pre;
        }

        public void setPre(Node pre) {
            this.pre = pre;
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
                    ", nickName='" + nickName + '\'' +
                    '}';
        }
    }
}
