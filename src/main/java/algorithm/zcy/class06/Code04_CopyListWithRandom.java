package main.java.algorithm.zcy.class06;

import java.util.HashMap;

/**
 * 给出一个链表头节点，copy一份链表出来
 * 单个节点包含两个指针
 * @auth tangjianghua
 * @date 2020/7/23
 */
public class Code04_CopyListWithRandom {

    public static class Node {
        public int value;
        public Node next;
        public Node rand;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 方式1：采用hashmap复制节点，模仿指针动作
     * @param head
     * @return
     */
    public static Node copyListWithRand1(Node head) {
        HashMap<Node, Node> map = new HashMap<>();
        //先把节点按照next指针copy到map里
        Node cur=head;
        while (cur!=null){
            map.put(cur,new Node(cur.value));
            cur=cur.next;
        }


        //然后在从头遍历，模仿指针动作
        cur=head;
        while (cur!=null){
            map.get(cur).next=map.get(cur.next);
            map.get(cur).rand=map.get(cur.rand);
            cur=cur.next;
        }
        return map.get(head);
    }
    /**
     * 方式2: 单纯使用指针来实习
     * 1.创建节点副本，串联起来
     * 2.遍历一遍搞定next指针
     * 3.遍历一遍搞定rand指针
     * 4.恢复现场
     * @param head
     * @return
     */
    public static Node copyListWithRand2(Node head) {
        HashMap<Node, Node> map = new HashMap<>();
        //先把节点按照next指针copy到map里
        Node cur=head;
        while (cur!=null){
            map.put(cur,new Node(cur.value));
            cur=cur.next;
        }


        //然后在从头遍历，模仿指针动作
        cur=head;
        while (cur!=null){
            map.get(cur).next=map.get(cur.next);
            map.get(cur).rand=map.get(cur.rand);
            cur=cur.next;
        }
        return map.get(head);
    }

}
