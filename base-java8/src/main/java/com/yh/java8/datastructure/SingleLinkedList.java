package com.yh.java8.datastructure;

/**
 * 单链表
 *
 * @author yanhuan
 */
public class SingleLinkedList {

    private HeroNode header = new HeroNode(0, "", "");

    public void addHero(int heroNo, String name, String nickName) {

    }

    /**
     * 链表反转
     */
    public void reverse() {
        HeroNode reverseHeader = new HeroNode(0, "", "");
        if (header.next == null) {
            System.out.println("empty list");
            return;
        }
        //获取当前节点
        HeroNode curNode = header.next;
        HeroNode nextNode;
        while (curNode != null) {
            //保存当前节点的下一个节点
            nextNode = curNode.next;
            //当前节点的下一个节点放在reverseHeader.next
            curNode.next = reverseHeader.next;
            //将cur连接到新的链表上
            reverseHeader.next = curNode;
            //遍历
            curNode = nextNode;
        }
        //将header.next指向reverHeader.next
        this.header = reverseHeader;
    }

    /**
     * 获取单链表有效元素个数
     *
     * @return 链表长度
     */
    public int size() {
        HeroNode temp = header;
        if (header.next == null) {
            return 0;
        }
        int len = 0;
        while (true) {
            if (temp.next != null) {
                len++;
            } else {
                break;
            }
            temp = temp.next;
        }
        return len;
    }

    /**
     * 按照heroNo添加
     *
     * @param heroNode 输入heroNode
     */
    public void addHero(HeroNode heroNode) {
        HeroNode temp = header;
        HeroNode preNode;
        while (true) {
            if (temp.getNext() == null) {
                temp.next = heroNode;
                break;
            }
            preNode = temp;
            temp = temp.next;
            if (preNode.getHeroNo() == heroNode.getHeroNo() || temp.getHeroNo() == heroNode.getHeroNo()) {
                throw new RuntimeException("HeroNo: " + heroNode.getHeroNo() + "already exists!");
            }
            if (preNode.getHeroNo() < heroNode.getHeroNo() && temp.getHeroNo() > heroNode.getHeroNo()) {
                //重建链表的关系
                preNode.next = heroNode;
                heroNode.next = temp;
                break;
            }
        }
    }

    /**
     * 根据heroNo修改
     */
    public void updateHero(HeroNode heroNode) {
        if (header.next == null) {
            throw new RuntimeException("empty list");
        }
        HeroNode temp = header;
        while (true) {
            if (temp.next == null) {
                System.out.println("HeroNo: " + heroNode.getHeroNo() + " not exists!");
                break;
            }
            temp = temp.next;
            if (temp.getHeroNo() == heroNode.getHeroNo()) {
                temp.setName(heroNode.getName());
                temp.setNickName(heroNode.getNickName());
                break;
            }
        }
    }

    /**
     * 根据节点编号删除节点
     *
     * @param heroNo 节点编号
     */
    public void deleteHero(int heroNo) {
        if (header.next == null) {
            throw new RuntimeException("empty list");
        }
        HeroNode preNode;
        HeroNode temp = header;
        while (true) {
            if (temp.next == null) {
                System.out.println("HeroNo: " + heroNo + " not exists!");
                break;
            }
            preNode = temp;
            temp = temp.next;
            if (temp.getHeroNo() == heroNo) {
                //如果找到heroNo对应的节点
                preNode.next = temp.next;
                temp = null;
                break;
            }
        }
    }

    public boolean isEmpty() {
        return header.next == null;
    }

    public void printList() {
        if (isEmpty()) {
            throw new RuntimeException("empty list");
        }
        HeroNode temp = header.next;
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }

    public HeroNode getHeader() {
        return header;
    }

    public static class HeroNode {

        private int heroNo;

        private String name;

        private String nickName;

        public HeroNode next;

        public HeroNode(int heroNo, String name, String nickName) {
            this.heroNo = heroNo;
            this.name = name;
            this.nickName = nickName;
        }

        public int getHeroNo() {
            return heroNo;
        }

        public void setHeroNo(int heroNo) {
            this.heroNo = heroNo;
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

        public HeroNode getNext() {
            return next;
        }

        public void setNext(HeroNode next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "HeroNode{" +
                    "heroNo=" + heroNo +
                    ", name='" + name + '\'' +
                    ", nickName='" + nickName + '\'' +
                    '}';
        }
    }

}

