package main.java.algorithm.leetcode.algorithm.link.simple;

/**
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 * <p>
 * 说明:
 * 1 ≤ m ≤ n ≤ 链表长度。
 * <p>
 * 示例:
 * <p>
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
 * 输出: 1->4->3->2->5->NULL
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-linked-list-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author tangjianghua
 * @date 2020/11/24
 */
public class Q92 {


    public static ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode next;

        //m上一个节点
        ListNode mPre = null;
        //m节点
        ListNode mNode = null;

        int index = 1;
        while (cur != null) {
            if (index < m) {
                pre = cur;
                cur = cur.next;
                index++;
            } else if (index == m) {
                mPre = pre;
                mNode = cur;
                pre = cur;
                cur = cur.next;
                index++;
            } else if (index <= n) {
                next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
                index++;
            } else {
                //cur!=null 代表 n< length
                if (mPre == null) {
                    //mPre==null 代表m==1
                    head = pre;
                } else {
                    //m>1
                    mPre.next = pre;
                }
                mNode.next = cur;
                break;
            }
        }

        if (cur == null) {
            //cur!=null 代表 n< length
            if (mPre == null) {
                //mPre==null 代表m==1
                head = pre;
            } else {
                //m>1
                mPre.next = pre;
            }
            mNode.next = cur;
        }

        return head;
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

        ListNode listNode = reverseBetween(head, 1, 6);
        //ListNode listNode = reverseList(head);
        cur = listNode;
        do {
            System.out.print(cur.val + ",");
            cur = cur.next;
        } while (cur != null);
    }

  /*  public ListNode reverseBetween(ListNode curNode, ListNode pre, int m, int n, int cur,ListNode mPre) {
        if (cur++ < (m-1)) {
            return reverseBetween(curNode.next, curNode, m, n, cur,cur==m-1?);
        } else if (cur <= n) {
            ListNode next = curNode.next;
            curNode.next = pre;
            reverseBetween(next, curNode, m, n, cur);
        }

    }*/
}
