package main.java.algorithm.leetcode.algorithm.link.simple;

/**
 * 反转一个单链表。
 * <p>
 * 示例:
 * <p>
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author tangjianghua
 * @date 2020/11/24
 */
public class Q206 {

    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = null;
        ListNode cur = head;
        ListNode next;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public static ListNode reverseListRecursive(ListNode cur, ListNode pre) {
        //basecase
        if (cur == null) {
            return pre;
        }
        ListNode next = cur.next;
        cur.next = pre;
        return reverseListRecursive(next, cur);
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        ListNode cur = head;
        for (int i = 1; i < 10; i++) {
            ListNode listNode1 = new ListNode(i);
            cur.next = listNode1;
            cur = listNode1;
        }
        cur = head;
        do {
            System.out.print(cur.val + ",");
            cur = cur.next;
        } while (cur != null);
        System.out.println();

        //ListNode listNode = reverseList(head);
        cur = head;
        ListNode listNode = reverseListRecursive(cur, null);

        cur = listNode;
        do {
            System.out.print(cur.val + ",");
            cur = cur.next;
        } while (cur != null);
    }
}
