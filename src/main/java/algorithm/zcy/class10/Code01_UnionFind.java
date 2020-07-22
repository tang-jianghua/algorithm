package main.java.algorithm.zcy.class10;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * 并查集
 * 1 ）有若干个样本 a b c...类型假设是 V
 * 2 ）在并查集中一开始认为每个样本都在单独的集合里
 * 3 ）用户可以在任何时候调用如下两个方法；
 * boolean isSameSet ( Vx , Vy ) 查询样本 x 和样本 y 是否属于一个集合
 * void union( V x , V y )  把x和y各自所在集合的所有样本合并成一个集合
 * 4 ) isSameSet 和 union 方法的代价越低越好
 * 设计出这个数据结构
 * 思路：
 * 1.每个样本单独在一个集合里，那么初始化时需要一个只想自己的表，设计一个封装样本的类，用hash表存储样本指向封装实例
 * 2.要满足isSameSet 和 union方法，先定义出这两个方法。
 * 3.要判断是否是在一个集合里，那么两个样本应该有一个共同属性，我们把两个样本指向同一个最终父节点。此时，只需要再union的时候制定一个共同的最终父节点即可。
 * 4.使用hash表来存储父子关系，或者在封装样本时添加最终父节点属性。
 * 5.提供一个私有的获取最终父节点方法来简化开发。--findRoot
 */
public class Code01_UnionFind {

    /**
     * 封装一个节点
     * 这个节点应该辅助我们两个功能
     * 1.提供一个父节点，将各个节点连接成一个样本集合
     * 2.提供该节点有多少样本，辅助我们在union时将小样本的最终父节点指向大样本的最终父节点
     *
     * @param <V>
     */
    public static class Node<V> {

        /**
         * 封装的样本
         */
        V value;

        /**
         * 父节点初始化为自己，这样父节点查找最终父节点也是自己
         */
        Node parent = this;

        /**
         * 刚开始的样本数都是自己
         */
        int nodes = 1;

        public Node(V v) {
            value = v;
        }
    }

    //构建一个集合类，支持泛型
    public static class UnionSet<V> {

        //使用hash表来支持集合功能
        private HashMap<V, Node<V>> vSet;

        public UnionSet(List<V> vs) {
            this.vSet = new HashMap<>(vs.size());
            vs.stream().filter(v -> !vSet.containsKey(v))
                    .forEach(v -> vSet.put(v, new Node<>(v)));

        }

        public boolean isSameSet(V x, V y) {
            //首先判断是否有样本，没有样本返回false
            if (!vSet.containsKey(x) || !vSet.containsKey(y)) return false;

            //其次拿出两个样本的最终父节点，如果最终父节点一致则在同一个集合
            return findRoot(x) == findRoot(y);
        }

        /**
         * 创造找最终父节点---最核心的部分
         *
         * @param v
         * @return
         */
        private Node<V> findRoot(V v) {
            if (!vSet.containsKey(v)) return null;

            Stack<Node<V>> stack = new Stack<>();
            //从vNode开始往最终父节点遍历，每次都将经过的节点压进栈中，
            //这一步很重要，因为我们要在找到父节点后将所有经过的节点直接指向父节点
            //这能大大优化findRoot的时间复杂度，经过验证，时间复杂度是O(1)。
            Node<V> vNode = vSet.get(v);
            while (vNode.parent != vNode) {
                stack.push(vNode);
                vNode = vNode.parent;
            }
            while (!stack.isEmpty()) {
                Node<V> node = stack.pop();
                Node parent = node.parent;
                //如果父节点不是最终父节点，那么将自己的父节点指向最终父节点时，需要将原先的父节点样本数减去自己的样本数
                if (parent!=vNode){
					node.parent = vNode;
					parent.nodes -= node.nodes;
				}
            }

            return vNode;
        }

        public void union(V x, V y) {

            //首先判断是否有样本，没有样本返回false
            if (!vSet.containsKey(x) || !vSet.containsKey(y)) return;

            //为了满足union后的两个样本在同一个集合里，我们将两个样本中的小样本的最终父节点指向大样本的最终父节点
            Node<V> xRoot = findRoot(x);
            Node<V> yRoot = findRoot(y);
            Node bigRoot = xRoot.nodes >= yRoot.nodes ? xRoot : yRoot;
            Node smallRoot = bigRoot == xRoot ? yRoot : xRoot;
            smallRoot.parent = bigRoot;
            bigRoot.nodes += smallRoot.nodes;

        }

    }

	public static void main(String[] args) {
		UnionSet<Integer> unionSet = new UnionSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
		Integer value = unionSet.findRoot(1).value;
		System.out.println(value);

		boolean sameSet = unionSet.isSameSet(1, 2);
		System.out.println(sameSet);

		unionSet.union(1,2);
		sameSet = unionSet.isSameSet(1, 2);
		System.out.println(sameSet);


		unionSet.union(2,3);
		sameSet = unionSet.isSameSet(1, 3);
		System.out.println(sameSet);
		unionSet.union(4,2);
		sameSet = unionSet.isSameSet(1, 4);
		System.out.println(sameSet);

		System.out.println(unionSet.findRoot(1).value);
		System.out.println(unionSet.findRoot(2).value);
		System.out.println(unionSet.findRoot(3).value);
		System.out.println(unionSet.findRoot(4).value);
	}

}
