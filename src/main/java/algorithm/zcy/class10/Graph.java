package main.java.algorithm.zcy.class10;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 图
 */
public class Graph {

	/**
	 * 每个位上的节点
	 */
	public HashMap<Integer, Node> nodes;

	/**
	 * 所有的边界
	 */
	public HashSet<Edge> edges;
	
	public Graph() {
		nodes = new HashMap<>();
		edges = new HashSet<>();
	}
}
