package main.java.algorithm.zcy.class06;

/**
 * 给出两个链表的头节点，获取两个链表的第一个相交点
 * <p>
 * 分情况
 * 1.一个有环一个无环 不相交
 * 2.两个无环
 * --2.1 相交：终点一致
 * --2.2 未相交：终点不一致
 * 3.两个有环
 * --3.1 入环节点一致：环节点前相交
 * --3.2 入环节点不一致：
 * --3.2.1 未相交：一个环节点在另一个环节点上不存在
 * --3.2.2 环节点后相交：一个环节点在另一个环节点上
 * 使用快慢指针获取环节点
 *
 * @auth tangjianghua
 * @date 2020/7/24
 */
public class Code05_FindFirstIntersectNode {

    public static class Node {
        public int value;
        public Node next;

        public Node(int v) {
            value = v;
        }
    }

    /**
     * 使用快慢指针方法
     * 当两个指针第一次相遇时，将快指针重置为head，然后快慢指针每次走一步，再次相遇即为入环节点。
     *
     * @param head
     * @return
     */
    public static Node findLoopNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node s = head.next;
        Node f = head.next.next;
        while (s != f) {
            if (s == null || f == null) {
                return null;
            }
            s = s.next;
            f = f.next.next;
        }
        //此时一定有环
        f = head;
        while (f != s) {
            f = f.next;
            s = s.next;
        }
        return s;

    }


    /**
     * 给定两个链表的头节点
     * 返回一个相交的节点，如果不相交返回空
     */
    public static Node findFirstIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) return null;
        Node loopNode1 = findLoopNode(head1);
        Node loopNode2 = findLoopNode(head2);
        //2.两个无环
        if (loopNode1 == null && loopNode2 == null) {
            return noLoop(head1, head2);
        }

        //1.一个有环一个无欢 不相交
        if(loopNode1 == null || loopNode2 == null){
            return null;
        }

        //3.两个有环

        return loop(head1,head2,loopNode1,loopNode2);
    }

    /**
     * 两个无环时获取第一个相交点
     */
    public static Node noLoop(Node head1, Node head2) {
        Node node1 = head1;
        Node node2 = head2;
        //记录链表长度
        int length = 0;
        while (node1 != null) {
            length++;
            node1 = node1.next;
        }
        while (node2 != null) {
            length--;
            node2 = node2.next;
        }
        if (node1 != node2) return null;

        //此时，length为两个链表的长度差，让长的链表先跳过length
        node1 = length > 0 ? head1 : head2;
        node2 = node1 == head1 ? head2 : head1;
        length=Math.abs(length);
        while (length != 0) {
            node1 = node1.next;
            length--;
        }
        //此时两个链表长度一致，开始对比
        while (node1 != node2) {
            node1 = node1.next;
            node2 = node2.next;
        }
        return node1;
    }

    /**
     * 3.两个有环
     * --3.1 入环节点一致：环节点前相交
     * --3.2 入环节点不一致：
     * --3.2.1 未相交：一个环节点在另一个环节点上不存在
     * --3.2.2 环节点后相交：一个环节点在另一个环节点上
     */
    public static Node loop(Node head1, Node head2, Node loopNode1, Node loopNode2) {
        //3.1 入环节点一致：环节点前相交
        if (loopNode1 == loopNode2) {
            Node node1 = head1;
            Node node2 = head2;
            int length=0;
            while (node1!=loopNode1){
                node1=node1.next;
                length++;
            }
            while (node2!=loopNode2){
                length--;
                node2=node2.next;
            }
            node1=length>0?head1:head2;
            node2=node1==head1?head2:head1;
            length=Math.abs(length);
            while (length!=0){
                node1=node1.next;
                length--;
            }
            while (node1!=node2){
                node1=node1.next;
                node2=node2.next;
            }
            return node1;
        } else {
            //3.2 入环节点不一致：
            //3.2.1 未相交：一个环节点在另一个环节点上不存在
            Node temp = loopNode1.next;
            while (temp != loopNode1 && temp != loopNode2) {
                temp = temp.next;
            }
            return temp == loopNode2 ? loopNode1 : null;
        }
    }

}
