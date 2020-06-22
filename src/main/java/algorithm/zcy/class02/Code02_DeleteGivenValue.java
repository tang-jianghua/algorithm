package main.java.algorithm.zcy.class02;

/**
 * 删除给定节点
 *
 * @author tangjianghua
 * date 2020/6/22
 * time 11:12
 */
public class Code02_DeleteGivenValue {

    class Node {
        private int value;

        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * 给出头节点和节点值，删除这个节点
     *
     * @param head
     * @param value
     * @return
     */
    public static Node removeValue(Node head, int value) {
        //先把头部过滤，获取一个新的头节点
        while (head != null) {
            if (head.value == value) {
                head = head.next;
            } else {
                break;
            }
        }
        Node pre = head;
        Node cur = head.next;
        while (cur != null) {
            //如果当前节点命中，则将该节点跳过
            if (cur.value == value) {
                pre.next = cur.next;
            } else {
                //否则前节点的指针移动到当前节点，继续往后查找
                pre = cur;
            }
            //当前节点的指针往后移动一位
            cur = cur.next;
        }
        return head;
    }
}
