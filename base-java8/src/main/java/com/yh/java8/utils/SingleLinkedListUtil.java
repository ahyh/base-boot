package com.yh.java8.utils;

import com.yh.java8.datastructure.SingleLinkedList;

public class SingleLinkedListUtil {

    public static SingleLinkedList merge(SingleLinkedList list1, SingleLinkedList list2) {
        if (list1 == null || list2 == null) {
            throw new RuntimeException("Invalid params");
        }
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        SingleLinkedList.HeroNode header = list1.getHeader();
        SingleLinkedList.HeroNode curNode = header.next;
        SingleLinkedList.HeroNode nextNode;
        while (curNode != null) {
            nextNode = curNode.next;
            curNode.next = null;
            singleLinkedList.addHero(curNode);
            curNode = nextNode;
        }
        header = list2.getHeader();
        curNode = header.next;
        while (curNode != null) {
            nextNode = curNode.next;
            curNode.next = null;
            singleLinkedList.addHero(curNode);
            curNode = nextNode;
        }
        return singleLinkedList;
    }
}
