package main.java.algorithm.zcy.class10;

import java.util.*;

/**
 * 图的最小生成树问题--K算法
 * 最小生成树：使用最小权重将所有的节点连接起来
 * 思路：将所有的边排序，从小开始连，当满足所有的节点都连接以后停，丢弃未使用的边。
 * 节点是否连接在一起了，即节点是否在一个集合里。因此采用并查集。
 *
 * @auth tangjianghua
 * @date 2020/7/24
 */
public class Code04_Kruskal {

    //首先设计出并查集的结构
    public static class UnionFind {

        /**
         * 1.提供一个记录元素的集合
         * 2.合并两个节点时要根据统一的规则合并，所以需要记录元素的集合节点数
         * 使用hashmap来满足以上两点
         */
        public HashMap<Node, Integer> sizeMap;


        /**
         * 提供一个可查父节点的关系记录
         */
        public HashMap<Node, Node> parentMap;

        /**
         * 提供一个添加到并查集的public方法
         */
        public void add(Collection<Node> nodes) {
            nodes.parallelStream()
                    .forEach(node -> {
                        sizeMap.put(node, 1);
                        parentMap.put(node, node);
                    });
        }


        /**
         * 并查集方法1：两个节点是否属于同一个集合
         */
        public boolean isSameSet(Node node1, Node node2) {
            if(!sizeMap.containsKey(node1)||!sizeMap.containsKey(node2)) return false;

            return findRoot(node1)==findRoot(node2);
        }

        /**
         * 并查集方法2：将两个节点合并到同一个集合
         */
        public void union(Node node1, Node node2) {
            if(!sizeMap.containsKey(node1)||!sizeMap.containsKey(node2)) return;
            Node root = findRoot(node1);
            Node root2 = findRoot(node2);
            if(root==root2) return;
            Node big=sizeMap.get(root) >= sizeMap.get(root2)? root:root2;
            Node small=big==root?root2:root;
            parentMap.put(small,big);
            sizeMap.put(big,sizeMap.get(big)+sizeMap.get(small));
        }

        /**
         * 私有复用方法：获取最终父节点
         */
        private Node findRoot(Node node) {
            if(!sizeMap.containsKey(node)){
                return null;
            }
            Stack<Node> nodes = new Stack<>();
            Node root=node;
            while (parentMap.get(root)!=root){
                nodes.push(root);
                root=parentMap.get(root);
            }
            while (!nodes.isEmpty()){
                Node pop = nodes.pop();
                Node node1 = parentMap.get(pop);
                sizeMap.put(node1,sizeMap.get(node1)-sizeMap.get(pop));
                parentMap.put(pop,root);
            }
            return root;
        }

    }
    public static class EdgeComparator implements Comparator<Edge> {

        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }

    }

    /**
     * K算法 最小生成树
     * @param graph
     * @return
     */
    public static Set<Edge> kruskalMST(Graph graph) {
        //先把图中的所有节点压入并查集中
        UnionFind unionFind = new UnionFind();
        unionFind.add(graph.nodes.values());
        //将图中的边利用小根堆排序
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
        for (Edge edge : graph.edges) { // M 条边
            priorityQueue.add(edge);  // O(logM)
        }
        //从权重小的开始链接
        Set<Edge> result = new HashSet<>();
        while (!priorityQueue.isEmpty()) { // M 条边
            Edge edge = priorityQueue.poll(); // O(logM)
            //如果入节点和出节点不在同一个集合中就添加
            if (!unionFind.isSameSet(edge.from, edge.to)) { // O(1)
                result.add(edge);
                unionFind.union(edge.from, edge.to);
            }
        }
        return result;
    }

}
