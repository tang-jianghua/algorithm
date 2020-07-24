package main.java.algorithm.zcy.class10;

import java.util.*;

/**
 * 打印一个单向图的拓扑结构
 * 拓扑结构：入度为0的节点打印
 *
 * @auth tangjianghua
 * @date 2020/7/24
 */
public class Code03_TopologySort {

    /**
     * 给定一个图或者自己按照图结构组装成Graph
     * 输出一个拓扑顺序列表
     *
     * @param graph
     * @return
     */
    public static List<Node> sortedTopology(Graph graph) {

        //先把图的所有节点存到hashmap里,value为节点的入度
        HashMap<Node, Integer> nodes = new HashMap<>();

        //使用队列来存储拓扑顺序元素
        Queue<Node> queue = new LinkedList<>();

        ArrayList<Node> list = new ArrayList<>();

        graph.nodes.values().forEach(node -> {
            if (node.in == 0) {
                queue.offer(node);
            }
            nodes.put(node, node.in);
        });
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            list.add(poll);
            if (poll.nexts != null) {
                for (Node node : poll.nexts) {
                    if (nodes.put(node, nodes.get(node) - 1) - 1 == 0) {
                        queue.offer(node);
                    }
                }
            }
        }
        return list;
    }
}
