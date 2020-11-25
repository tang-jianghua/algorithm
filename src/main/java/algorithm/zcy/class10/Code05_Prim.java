package main.java.algorithm.zcy.class10;

import java.util.*;

/**
 * 图的最小生成树问题--P算法
 * 最小生成树：使用最小权重将所有的节点连接起来
 * 思路：
 *  1.在图中选择一个随机的节点开始
 *  2.先将节点记录到一个set集合中，再将节点的所有边解锁到一个小根堆中
 *  3.从小根堆中弹出权重最小的边，如果边的终点没有再set中记录，则记录这个终点，选用这个边
 *  4.如果终点已经记录过了，则弃用这个边。为了防止边重复，将已选中的边放到set中。
 *
 *  P算法和K算法的区别：
 *  K算法是先将所有的边统计起来，从最小的开始将选择，直到所有的节点都被链接。
 *  P算法是边记录节点边记录边，知道所有的节点都被链接。
 *
 * @auth tangjianghua
 * @date 2020/7/24
 */
public class Code05_Prim {

    /**
     * 先构建一个方法
     * 接受一个图参数
     * 返回一个使用的边集合
     */
    public Set<Edge> primMST(Graph graph){
        if(graph.nodes.isEmpty()){
            return Collections.EMPTY_SET;
        }
        //选取一个开始节点
        Node node = graph.nodes.get(0);
        //构建一个已记录节点的set集合
        HashSet<Node> nodes = new HashSet<>();
        nodes.add(node);
        //构建一个储存边小根堆
        PriorityQueue<Edge> edges = new PriorityQueue<>();
        for (Edge edge:node.edges ) {
            //将所有的边推入小根堆中
            edges.offer(edge);
        }

        //构建一个储存已使用边的set集合
        HashSet<Edge> result = new HashSet<>();
        while (!edges.isEmpty()){
            Edge edge = edges.poll();
            //如果终点没有被记录，则使用这个边，同时记录终点
            if(!nodes.contains(edge.to)){
                result.add(edge);
                nodes.add(edge.to);
                for (Edge edge1:edge.to.edges) {
                    edges.offer(edge1);
                }
            }
        }
        return result;
    }
}
