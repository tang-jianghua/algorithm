package main.java.algorithm.practice;

/**
 * 认识链表
 * -单链表和双向链表的反转
 * -删除给定节点
 * -使用双向链表做一个自己的栈和队列
 *
 * @author tangjianghua
 * @date 2020/11/17
 */
public class Code01_ReverseList {

    /**
     * 首先设计链表节点
     */
    class Node {

        /**
         * 下个节点
         */
        private Node next;

        /**
         * 节点值
         */
        private int value;

        public Node(int value) {
            this.value = value;
        }
    }
    /**
     * 首先设计链表节点
     */
    class DoubleNode {

        /**
         * 下个节点
         */
        private DoubleNode next;
        /**
         * 下个节点
         */
        private DoubleNode pre;

        /**
         * 节点值
         */
        private int value;

        public DoubleNode(int value) {
            this.value = value;
        }
    }

    /**
     * 反转单向链表
     *
     * @param head
     */
    public Node reverse(Node head) {
        Node pre = null;
        Node next ;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head=next;
        }
        return pre;
    }


    /**
     * 反转双向链表
     *
     * @param head
     */
    public DoubleNode reverseDoubleLink(DoubleNode head) {

        DoubleNode pre = null;
        DoubleNode next ;
        while (head != null) {
            next = head.next;
            head.next = pre;
            head.pre = next;
            pre = head;
            head=next;
        }
        return pre;
    }


}
