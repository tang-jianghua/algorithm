package main.java.algorithm.zcy.class10;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 图的宽度优先遍历
 * 宽度优先遍历：从一个节点出发，分层遍历，层内不要求排序
 * @auth tangjianghua
 * @date 2020/7/24
 */
public class Code02_BFS {

    public static void print(Node head){
        if(head==null){
            return;
        }

        //利用一个队列来存储要打印的节点
        Queue<Node> queue=new LinkedList();

        //利用set不重复的特性，避免重复打印
        HashSet<Node> printed = new HashSet<>();

        queue.offer(head);
        printed.add(head);
        Node cur;
        while (!queue.isEmpty()){
            cur=queue.poll();
            System.out.println(cur.value);
            if (cur.nexts!=null && !cur.nexts.isEmpty()){
                for (int i = 0; i < cur.nexts.size(); i++) {
                    if(!printed.contains(cur.nexts.get(i))){
                        queue.offer(cur.nexts.get(i));
                        printed.add(cur.nexts.get(i));
                    }
                }
            }
        }
    }
}
