package main.java.algorithm.zcy.class06;

/**
 * 将单向链表按某值划分成左边小、中间相等、右边大的形式
 * 1)把链表放入数组里在数组上做partition（笔试用）O(N)
 * 2)分成小、中、大三部分再把各个部分之间串起来（面试用）
 *
 * @auth tangjianghua
 * @date 2020/7/23
 */
public class Code03_SmallerEqualBigger {

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 荷兰国旗问题
     * 注意：partion完以后要重新建立连接
     *
     * @param head
     * @param value
     * @return
     */
    public static Node partion1(Node head, int value) {
        if (head == null) {
            return null;
        }
        int length = 0;
        Node cur = head;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        Node[] nodes = new Node[length];

        cur = head;
        int index=0;
        while (cur != null) {
            nodes[index++]=cur;
            cur = cur.next;
        }
        return arrPartion(nodes, value);

    }

    public static Node arrPartion(Node[] nodes, int value) {

        int left = -1;
        int right = nodes.length;
        int index = 0;
        while (index < right) {
            if (nodes[index].value < value) {
                swap(nodes, ++left, index++);
            } else if (nodes[index].value > value) {
                swap(nodes, index, --right);
            } else {
                index++;
            }
        }
        for (int i = 0; i < nodes.length-1; i++) {
            nodes[i].next=nodes[i+1];
        }
        nodes[nodes.length-1].next=null;
        return nodes[0];
    }


    public static void swap(Node[] nodes, int i, int j) {
        if (i == j) {
            return;
        }
        Node node = nodes[i];
        nodes[i] = nodes[j];
        nodes[j] = node;
    }

    public static Node partion2(Node head, int value) {
        if (head == null || head.next == null) {
            return head;
        }
        Node sh = null;
        Node st = null;

        Node eh = null;
        Node et = null;

        Node bh = null;
        Node bt = null;

        Node cur = head;
        while (cur!=null) {
            if(cur.value < value){
                if (sh == null) {
                    sh = cur;
                    st = cur;
                } else {
                    st.next = cur;
                    st = st.next;
                }
            }else if(cur.value == value){
                if (eh == null) {
                    eh = cur;
                    et = cur;
                } else {
                    et.next = cur;
                    et = et.next;
                }
            }else{
                if (bh == null) {
                    bh = cur;
                    bt = cur;
                } else {
                    bt.next = cur;
                    bt = bt.next;
                }
            }
            cur=cur.next;
        }
        if(bt!=null){
            bt.next=null;
        }
        if(et !=null){
            et.next=bh;
        }
        if(st!=null){
            if(eh!=null){
                st.next=eh;
            }else {
                st.next=bh;
            }
        }

        return sh!=null?sh:eh!=null?eh:bh;
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
        Node head1 = new Node(7);
        head1.next = new Node(9);
        head1.next.next = new Node(1);
        head1.next.next.next = new Node(8);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(2);
        head1.next.next.next.next.next.next = new Node(5);
        printLinkedList(head1);
        // head1 = listPartition1(head1, 4);
        head1 = partion1(head1, 5);
        printLinkedList(head1);
        head1 = partion2(head1, 5);
        printLinkedList(head1);

    }

}
