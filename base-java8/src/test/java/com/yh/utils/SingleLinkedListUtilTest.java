package com.yh.utils;

import com.yh.java8.datastructure.SingleLinkedList;
import com.yh.java8.utils.SingleLinkedListUtil;
import org.junit.Test;

public class SingleLinkedListUtilTest {

    private static final SingleLinkedList list1 = new SingleLinkedList();
    private static final SingleLinkedList list2 = new SingleLinkedList();

    static {
        list1.addHero(new SingleLinkedList.HeroNode(1, "宋江", "及时雨"));
        list1.addHero(new SingleLinkedList.HeroNode(3, "无用", "智多星"));
        list1.addHero(new SingleLinkedList.HeroNode(5, "关胜", "大刀"));

        list2.addHero(new SingleLinkedList.HeroNode(2, "卢俊义", "玉麒麟"));
        list2.addHero(new SingleLinkedList.HeroNode(4, "公孙胜", "入云龙"));
        list2.addHero(new SingleLinkedList.HeroNode(6, "林冲", "豹子头"));
    }

    @Test
    public void testSingleLinkedListMerge() {
        SingleLinkedList merge = SingleLinkedListUtil.merge(list1, list2);
        merge.printList();
    }
}
