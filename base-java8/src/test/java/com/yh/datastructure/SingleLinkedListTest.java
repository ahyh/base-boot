package com.yh.datastructure;

import com.yh.java8.datastructure.SingleLinkedList;
import org.junit.Test;

public class SingleLinkedListTest {

    private static SingleLinkedList.HeroNode node1 = new SingleLinkedList.HeroNode(1,"宋江","及时雨");
    private static SingleLinkedList.HeroNode node2 = new SingleLinkedList.HeroNode(2,"卢俊义","玉麒麟");
    private static SingleLinkedList.HeroNode node3 = new SingleLinkedList.HeroNode(3,"吴用","智多星");
    private static SingleLinkedList.HeroNode node4 = new SingleLinkedList.HeroNode(4,"公孙胜","入云龙");

    @Test
    public void testAdd(){
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.addHero(node4);
        singleLinkedList.addHero(node2);
        singleLinkedList.addHero(node1);
        singleLinkedList.addHero(node3);

        singleLinkedList.updateHero(new SingleLinkedList.HeroNode(3, "林冲","豹子头"));

        singleLinkedList.deleteHero(1);



        singleLinkedList.printList();

        System.out.println(singleLinkedList.size());

        singleLinkedList.reverse();
    }
}
