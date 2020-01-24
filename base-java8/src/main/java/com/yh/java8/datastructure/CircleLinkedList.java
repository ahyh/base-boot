package com.yh.java8.datastructure;

/**
 * 循环链表
 *
 * @author yanhuan
 */
public class CircleLinkedList<T> {

    private Node<T> first = null;

    /**
     * 初始化空栈
     */
    public CircleLinkedList() {
    }

    public CircleLinkedList(Node<T> node) {
        if (node == null) {
            throw new RuntimeException("param invalid");
        }
        this.first = node;
        node.setNext(first);
    }

    /**
     * 初始化循环链表
     *
     * @param arr    需要插入循环链表的节点数组
     * @param revert 是否需要反转，true需要反转，false不反转从尾部插入
     */
    public CircleLinkedList(T[] arr, boolean revert) {
        if (arr == null) {
            throw new RuntimeException("param invalid");
        }
        Node<T>[] nodes = new Node[arr.length];
        for (int i = 0; i < arr.length; i++) {
            nodes[i] = new Node<>(arr[i]);
        }
        //如果需要从头部插入
        if (revert == true) {
            Node temp;
            for (int i = 0; i <= nodes.length / 2; i++) {
                temp = nodes[i];
                nodes[i] = nodes[nodes.length - 1 - i];
                nodes[nodes.length - 1 - i] = temp;
            }
        }
        Node<T> curNode = null;
        for (Node<T> node : nodes) {
            if (node == null) {
                throw new RuntimeException("Invalid param");
            }
            if (this.first == null || curNode == null) {
                this.first = node;
                curNode = this.first;
            } else {
                curNode.setNext(node);
                curNode = node;
            }
        }
        curNode.setNext(this.first);
    }


    /**
     * 求循环链表元素个数
     *
     * @return 循环列表中元素个数
     */
    public int size() {
        if (first == null) {
            return 0;
        }
        int sum = 1;
        Node curNode = first;
        while (curNode.getNext() != first) {
            sum++;
            curNode = curNode.getNext();
        }
        return sum;
    }

    /**
     * 遍历打印循环链表
     */
    public void printList() {
        if (first == null) {
            return;
        }
        Node curNode = first;
        do {
            System.out.println(curNode);
            curNode = curNode.getNext();
        } while (curNode != first);
    }

    /**
     * 在循环链表的尾部添加节点
     *
     * @param ele 待添加的元素值
     */
    public void addNodeR(T ele) {
        if (ele == null) {
            throw new RuntimeException("ele cannot null");
        }
        Node<T> node = new Node<>(ele);
        //如果是添加的第一个节点
        if (first == null) {
            first = node;
            first.setNext(first);
        } else {
            Node curNode = first;
            Node nextNode = curNode.getNext();
            //如果只有一个节点
            if (curNode == nextNode) {
                first.setNext(node);
                node.setNext(nextNode);
            } else {
                //多个节点, 找到最后一个节点
                while (nextNode != first) {
                    curNode = nextNode;
                    nextNode = nextNode.getNext();
                }
                curNode.setNext(node);
                node.setNext(first);
            }
        }
    }

    /**
     * 头部插入节点
     *
     * @param ele 节点中元素之
     */
    public void addNodeF(T ele) {
        if (ele == null) {
            throw new RuntimeException("ele cannot null");
        }
        Node<T> node = new Node<>(ele);
        //如果是添加的第一个节点
        if (first == null) {
            first = node;
            first.setNext(first);
        } else {
            Node curNode = first;
            Node nextNode = curNode.getNext();
            //如果只有一个节点
            if (curNode == nextNode) {
                this.first = node;
                node.setNext(nextNode);
                nextNode.setNext(this.first);
            } else {
                //多个节点, 找到最后一个节点
                while (nextNode != first) {
                    curNode = nextNode;
                    nextNode = curNode.getNext();
                }
                node.setNext(first);
                this.first = node;
                curNode.setNext(first);
            }
        }
    }

    public boolean isEmpty() {
        return this.first == null;
    }

    /**
     * 待删除节点编号,从0开始
     *
     * @param no 待删除节点编号
     */
    public void deleteNode(int no) {
        if (first == null || no < 0 || no > size() || isEmpty()) {
            throw new RuntimeException("invalid params");
        }
        Node curNode = first;
        Node pre = null, next = null;
        int i = 0;
        while (curNode.getNext() != first) {
            pre = curNode;
            curNode = curNode.getNext();
            next = curNode.getNext();
            i++;
            if (no != 0 && i == no) {
                break;
            }
        }
        if (no == 0) {
            //需要删除的是first节点
            pre = curNode;
            curNode = curNode.getNext();
            next = curNode.getNext();
            this.first = next;
            pre.setNext(this.first);
        } else {
            curNode.setNext(null);
            curNode = null;
            pre.setNext(next);
        }
    }

    public Node getFirst() {
        return first;
    }

    public void setFirst(Node first){
        this.first = first;
    }

}
