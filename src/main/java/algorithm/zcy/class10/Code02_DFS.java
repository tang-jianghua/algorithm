package main.java.algorithm.zcy.class10;

import java.util.HashSet;
import java.util.Stack;

/**
 * 图的深度优先遍历
 * 深度优先遍历：从一个节点触发，当节点走到头或者最终回到自己的时候走另一条路。
 */
public class Code02_DFS {

	public static void dfs(Node node) {
		if (node == null) {
			return;
		}
		//由于一条路走完后要接回之前的某个节点重新选择，这里采用栈的先进后出特性来存储节点
		Stack<Node> nodes = new Stack<>();

		//再利用set的不重复特性来排除重复打印
		HashSet<Node> printed = new HashSet<>();

		//第一个节点先押金栈中
		nodes.push(node);
		printed.add(node);
		System.out.println(node.value);

		Node pop;
		while (!nodes.isEmpty()){
			pop = nodes.pop();
			if(pop.nexts!=null&& !pop.nexts.isEmpty()){
				for (Node next: pop.nexts) {
					if(!printed.contains(next)){
						nodes.push(pop);
						printed.add(next);
						nodes.push(next);
						System.out.println(next.value);
					}

				}

			}
		}

	}

}
