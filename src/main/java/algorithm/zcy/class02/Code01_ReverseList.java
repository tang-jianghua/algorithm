package main.java.algorithm.zcy.class02;

/**
 * 认识链表
 * -单链表和双向链表的反转
 * -删除给定节点
 * -使用双向链表做一个自己的栈和队列
 *
 * @author tangjianghua
 * date 2020/6/22
 * time 10:34
 */
public class Code01_ReverseList {

    class Node {
        private int value;

        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    class DoubleNode {
        private int value;

        private DoubleNode pre;

        private DoubleNode next;

        public DoubleNode(int value) {
            this.value = value;
        }
    }

    /**
     * 反转单向链表
     * 1->2->3->4
     * pre->head->next
     *
     * @param head
     * @return 返回新的头节点
     */
    public static Node reverseLinkedList(Node head) {
        Node pre = null;
        Node next;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    /**
     * 反转双向链表
     *
     * @param head
     * @return 返回新的头节点
     */
    public static DoubleNode reverseDoubleList(DoubleNode head) {

        DoubleNode pre = null;
        DoubleNode next;
        while (head != null) {
            next = head.next;
            head.next = pre;
            head.pre = next;
            pre = head;
            head = next;
        }
        return pre;
    }



}
