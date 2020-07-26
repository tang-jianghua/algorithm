package main.java.algorithm.zcy.class10;

import java.util.*;

/**
 * 给出图中的一个节点，要求算出这个节点到其他点的最短距离
 *
 * @auth tangjianghua
 * @date 2020/7/24
 */
public class Code06_Dijkstra {


    /**
     * 一个节点入参
     * 节点-距离对应的表
     * 思路：
     * 1.从单节点出发，把所有边的to节点存到一个hash表中
     * 2.然后从hash表中查找最小的距离的点，用该点到to节点的距离去和hash表里的做对比，看是否可更新
     * 3.对比过的点就不需要再对比了，使用一个set来去重
     */
    public static HashMap<Node, Integer> dijkstra(Node f) {

        //构建一个储存结果的hash表
        HashMap<Node, Integer> distanceMap = new HashMap<>();
        distanceMap.put(f, 0);

        //构建一个去重的set集合
        HashSet<Node> selected = new HashSet<>();

        Node minNode = getMinDistanceAndUnselectedNode(distanceMap, selected);
        Integer minDistance;
        //当栈中有元素时往外pop
        while (minNode != null) {
            minDistance = distanceMap.get(minNode);
            ArrayList<Edge> edges = minNode.edges;
            for (Edge edge :
                    edges) {
                if (!distanceMap.containsKey(edge.to)) {
                    distanceMap.put(edge.to, minDistance + edge.weight);
                } else {
                    if (distanceMap.get(edge.to) > minDistance + edge.weight) {
                        distanceMap.put(edge.to, minDistance + edge.weight);
                    }
                }
            }
            selected.add(minNode);
            minNode = getMinDistanceAndUnselectedNode(distanceMap, selected);
        }
        return distanceMap;
    }

    /**
     * 提供一个可以获取最小距离的节点
     *
     * @param distanceMap
     * @param selected
     * @return
     */
    private static Node getMinDistanceAndUnselectedNode(HashMap<Node, Integer> distanceMap, HashSet<Node> selected) {

        Node minNode = null;
        int minDistance = Integer.MAX_VALUE;
        //遍历hash表，比较选出最小距离的节点
        Set<Map.Entry<Node, Integer>> entries = distanceMap.entrySet();
        for (Map.Entry<Node, Integer> entry : entries) {
            if (!selected.contains(entry.getKey()) && entry.getValue() < minDistance) {
                minNode = entry.getKey();
                minDistance = entry.getValue();
            }
        }
        return minNode;
    }


    /**
     * dijkstra优化
     * 在方法1中，每次从hashmap中获取最小节点的时间复杂度是O(N)，可以使用小根堆优化为O(logN)
     */
    public static Map<Node, Integer> dijkstra2(Node f,int n) {
        Heap heap = new Heap(n);
        heap.addOrUpdateOrIgnore(f,0);
        Map<Node, Integer> result = new HashMap<>();
        while (!heap.isEmpty()){
            NodeRecord record = heap.poll();
            Node node = record.node;
            ArrayList<Edge> edges = node.edges;
            for (Edge edge:edges
                 ) {
                heap.addOrUpdateOrIgnore(edge.to,record.distance+edge.weight);
            }
            result.put(node,record.distance);
        }
        return result;
    }

    /**
     * 设计一个小根堆代替distanceMap
     * 提供一个可修改元素值的方法
     */
    public static class Heap {
        Node[] nodes;
        HashMap<Node, Integer> distanceMap;
        HashMap<Node, Integer> indexMap;
        int size;

        public Heap(int size) {
            this.nodes = new Node[size];
            this.size = 0;
            this.distanceMap = new HashMap<>(size);
            this.indexMap = new HashMap<>(size);
        }

        public boolean isFull() {
            return size == nodes.length;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        //添加-修改-忽略
        public void addOrUpdateOrIgnore(Node node, int distance) {

            //在堆上且需要比较
            if (needCompare(node)) {
                Integer oldDistance = distanceMap.get(node);
                int newDistance = oldDistance + distance;
                if (oldDistance > newDistance) {
                    distanceMap.put(node, newDistance);
                    insertHeap(nodes, indexMap.get(node));
                }
            }
            //不在堆上就添加
            if (!inHeap(node)) {
                if (isFull()) return;
                nodes[size - 1] = node;
                distanceMap.put(node, distance);
                indexMap.put(node, size - 1);
                insertHeap(nodes, size - 1);
            }
        }

        /**
         * 已经对比过的节点不需要再比较
         * 在堆上且不能忽略
         *
         * @param node
         * @return
         */
        private boolean needCompare(Node node) {
            return inHeap(node) && indexMap.get(node) != -1;
        }

        /**
         * 在堆上
         *
         * @param node
         * @return
         */
        private boolean inHeap(Node node) {
            return indexMap.containsKey(node);
        }

        /**
         * @param nodes 元素数组
         * @param i     从0开始第i个元素
         */
        private void insertHeap(Node[] nodes, int i) {
            int parent = (i - 1) / 2;
            while (parent >= 0) {
                if (distanceMap.get(parent) > distanceMap.get(i)) {
                    swap(nodes, i, parent);

                    parent = (parent - 1) / 2;
                }
            }
        }

        private void swap(Node[] nodes, int i, int i1) {
            if (i == i1) return;
            Node node = nodes[i];
            nodes[i] = nodes[i1];
            nodes[i1] = node;
            indexMap.put(nodes[i], i);
            indexMap.put(nodes[i1], i1);
        }

        //弹出
        public NodeRecord poll() {
            if (isEmpty()) return null;
            Node node = nodes[0];
            NodeRecord nodeRecord = new NodeRecord(node, distanceMap.get(node), indexMap.get(node));

            swap(nodes, 0, --size);
            //从小弹出的节点，已经对比过的节点不需要再比较
            indexMap.put(node, -1);
            //distanceMap仅用来缓存，不做最后输出结果
            distanceMap.remove(node);
            //及时释放内存
            nodes[size] = null;
            heapify(nodes, 0, size);
            return nodeRecord;
        }

        /**
         * @param nodes 元素数组
         * @param i     从0开始第i个元素
         * @param size  总共size个元素
         */
        private void heapify(Node[] nodes, int i, int size) {

            int left = i * 2 + 1;
            while (left < size) {
                int smaller = left + 1 < size ? distanceMap.get(nodes[left]) < distanceMap.get(nodes[left + 1]) ?
                        left : left + 1 : left;
                if (distanceMap.get(nodes[i]) > distanceMap.get(nodes[smaller])) {
                    swap(nodes, i, smaller);
                    i = smaller;
                    left = i * 2 + 1;
                } else {
                    break;
                }
            }
        }
    }

    public static class NodeRecord {
        Node node;
        int distance;
        int index;

        public NodeRecord(Node node, int distance, int index) {
            this.node = node;
            this.distance = distance;
            this.index = index;
        }
    }



}
