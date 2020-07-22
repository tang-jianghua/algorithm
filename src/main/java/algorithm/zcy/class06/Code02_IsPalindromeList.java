package main.java.algorithm.zcy.class06;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 判断一个单向链表是否是回文结构
 * 回文结构：如果一个数字序列逆置之后跟原序列是一样的就称这样的数字序列为回文序列.eg;{1,2,3,2,1}
 *
 * @auth tangjianghua
 * @date 2020/7/22
 */
public class Code02_IsPalindromeList {
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 使用栈
     * 时间复杂度：O(N)
     * 空间复杂度：O(N)
     *
     * @param head
     * @return
     */
    public static boolean isPalindrome1(Node head) {
        Stack<Node> stack = new Stack<>();
        Node cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            if (cur.value != stack.pop().value) {
                return false;
            }
            cur = cur.next;
        }
        return true;
    }


    /**
     * 使用快慢指针，当节点数为偶数时找出后中节点，当节点数为奇数时找出中节点。
     * 将中节点或后中节点开始压进栈中。
     * 再从头节点开始遍历到栈中的长度进行对比。如果一致则为回文结构
     * 时间复杂度O(N)
     * 空间复杂度O(N/2) 申请了一半的栈
     *
     * @param head
     * @return
     */
    public static boolean isPalindrome2(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        Node fast = head.next;
        Node slow = head.next;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        Stack<Node> stack = new Stack<>();
        while (slow != null) {
            stack.push(slow);
            slow = slow.next;
        }
        slow = head;
        while (!stack.isEmpty()) {
            if (slow.value != stack.pop().value) {
                return false;
            }
            slow = slow.next;
        }
        return true;
    }

    /**
     * 先使用快慢指针找到中节点，注意这里不能再获取后中节点，应获取前一个。
     * 然后不使用栈，将后面的节点使用单向链表的反转指向中节点，开始从头对比
     * 最后把反转的链表再恢复现场
     *
     * @param head
     * @return
     */
    public static boolean isPalindrome3(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        Node fast = head;
        //中点
        Node slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        //反转链表
        Node pre = null;
        Node cur = slow.next;
        slow.next = null;
        Node next;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        //拿到头尾节点进行对比
        cur = head;
        Node tail = pre;

        while (cur != null && tail != null) {
            if (cur.value != tail.value) {
                return false;
            }
            cur = cur.next;
            tail = tail.next;
        }

        //恢复现场
        //重新拿到头节点
        tail = pre;
        next = null;
        while (tail.next != null) {
            //存储前节点
            pre = tail.next;
            //指定后节点
            tail.next = next;
            //下个节点指向当前
            next = tail;
            //当前指向前进点
            tail = pre;
        }
        slow.next=tail;

        return true;
    }


    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {

        Node head = null;
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");
        head = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(2);
        head.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(2);
        head.next.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

    }
}
