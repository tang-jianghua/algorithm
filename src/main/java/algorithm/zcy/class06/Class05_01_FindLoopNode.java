package main.java.algorithm.zcy.class06;

/**
 * 获取一个链表的第一个入环节点
 * @auth tangjianghua
 * @date 2020/7/24
 */
public class Class05_01_FindLoopNode {

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
     * @param head
     * @return
     */
    public static Node findLoopNode(Node head){
        if(head==null || head.next==null||head.next.next==null){
            return null;
        }
        Node s=head.next;
        Node f=head.next.next;
        while (s!=f){
            if(s==null||f==null){
                return null;
            }
            s=s.next;
            f=f.next.next;
        }
        //此时一定有环
        f=head;
        while (f!=s){
            f=f.next;
            s=s.next;
        }
        return s;

    }

}
