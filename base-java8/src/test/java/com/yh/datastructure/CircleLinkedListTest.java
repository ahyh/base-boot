package com.yh.datastructure;

import com.yh.java8.datastructure.CircleLinkedList;
import org.junit.Test;

public class CircleLinkedListTest {

    private static final CircleLinkedList.Node node1 = new CircleLinkedList.Node(1,"aa");
    private static final CircleLinkedList.Node node2 = new CircleLinkedList.Node(2,"bb");
    private static final CircleLinkedList.Node node3 = new CircleLinkedList.Node(3,"cc");
    private static final CircleLinkedList.Node node4 = new CircleLinkedList.Node(4,"dd");
    private static final CircleLinkedList.Node node5 = new CircleLinkedList.Node(5,"ee");
    private static final CircleLinkedList.Node node6 = new CircleLinkedList.Node(6,"ff");
    private static final CircleLinkedList.Node node7 = new CircleLinkedList.Node(7,"gg");
    private static final CircleLinkedList.Node node8 = new CircleLinkedList.Node(8,"hh");

    @Test
    public void testAddNode(){
        CircleLinkedList circleLinkedList = new CircleLinkedList();

        circleLinkedList.addNode(node2);
        circleLinkedList.addNode(node1);
        circleLinkedList.addNode(node4);
        circleLinkedList.addNode(node3);
        circleLinkedList.addNode(node5);

//        circleLinkedList.deleteNode(2);

        circleLinkedList.josepfu(1,2);

        circleLinkedList.printList();
    }


}
