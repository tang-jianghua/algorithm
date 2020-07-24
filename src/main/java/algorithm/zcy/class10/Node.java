package main.java.algorithm.zcy.class10;

import java.util.ArrayList;

/**
 * 节点
 */
public class Node {
	//节点值
	public int value;
	//入度
	public int in;
	//出度
	public int out;
	//出向直连节点
	public ArrayList<Node> nexts;
	//所有的边
	public ArrayList<Edge> edges;

	public Node(int value) {
		this.value = value;
		in = 0;
		out = 0;
		nexts = new ArrayList<>();
		edges = new ArrayList<>();
	}
}
