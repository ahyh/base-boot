package com.yh.java8.datastructure;


/**
 * Stack中存放的节点
 *
 * @author yanhuan
 */
public class Node<E> {

    private E data;

    private Node next;

    public Node() {
    }

    public Node(E data) {
        this.data = data;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
