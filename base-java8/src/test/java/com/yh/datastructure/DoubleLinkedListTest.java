package com.yh.datastructure;

import com.yh.java8.datastructure.DoubleLinkedList;
import org.junit.Test;

/**
 * 双向链表测试类
 *
 * @author yanhuan
 */
public class DoubleLinkedListTest {

    private static DoubleLinkedList.Node node1 = new DoubleLinkedList.Node(1, "宋江", "及时雨");
    private static DoubleLinkedList.Node node2 = new DoubleLinkedList.Node(2, "卢俊义", "玉麒麟");
    private static DoubleLinkedList.Node node3 = new DoubleLinkedList.Node(3, "吴用", "智多星");
    private static DoubleLinkedList.Node node4 = new DoubleLinkedList.Node(4, "公孙胜", "入云龙");

    @Test
    public void testAdd() {
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.addNode(node2);
        doubleLinkedList.addNode(node1);
        doubleLinkedList.addNode(node4);
        doubleLinkedList.addNode(node3);

        doubleLinkedList.updateNode(new DoubleLinkedList.Node(3, "3", "3"));
        doubleLinkedList.printList();

        doubleLinkedList.delete(3);
        doubleLinkedList.printList();

        doubleLinkedList.delete(1);
        doubleLinkedList.printList();

        doubleLinkedList.delete(4);
        doubleLinkedList.printList();

        doubleLinkedList.printList();
    }
}
