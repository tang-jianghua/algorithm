package main.java.algorithm.zcy.class07;

import main.java.algorithm.zcy.Node;

import java.util.*;

/**
 * 打印二叉树
 * 1.先获取二叉树的最大宽度
 * 2.利用层级遍历，每一层记录一个字符串
 * 3.遍历每层的记录
 * @auth tangjianghua
 * @date 2020/7/25
 */
public class Code05_PrintBinaryTree {

/*
    public static List<String> maxWidthUseMap(Node head) {
        if(head==null){
            return Collections.EMPTY_LIST;
        }
        //先准备一个队列
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);

        //记录层级
        int curLevel = 1;
        //记录宽度
        String curString = "";
        int maxWidth = 0;
        //记录节点所属层级
        Map<Node, Integer> nodeLevelMap = new HashMap();
        nodeLevelMap.put(head, 1);
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            Integer pollLevel = nodeLevelMap.get(poll);
            if (poll.left != null) {
                queue.add(poll.left);
                nodeLevelMap.put(poll.left, pollLevel + 1);
            }
            if (poll.right != null) {
                queue.add(poll.right);
                nodeLevelMap.put(poll.right, pollLevel + 1);
            }
            if (curLevel == pollLevel) {
                curWidth++;
            } else {
                maxWidth = Math.max(maxWidth, curWidth);
                //此时是下一层的第一个节点
                curWidth = 1;
                curLevel++;
            }
        }
        return Math.max(maxWidth, curWidth);
    }*/

}
