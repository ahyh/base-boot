package com.yh.datastructure;

import com.yh.java8.datastructure.CircleLinkedList;
import org.junit.Test;

public class CircleLinkedListTest {

    @Test
    public void testAddNode(){
        CircleLinkedList list = new CircleLinkedList<Integer>();
        System.out.println(list.size());

        list.addNodeR(3);
        list.addNodeR(4);
        list.addNodeR(5);

        list.printList();

        list.addNodeF(2);
        list.addNodeF(1);
        list.printList();

        System.out.println(list.size());

        list.deleteNode(3);
        list.printList();

        list.deleteNode(0);
        list.printList();
    }


}
