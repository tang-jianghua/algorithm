package main.java.algorithm.zcy.class09;

import java.util.PriorityQueue;

/**
 * 贪心算法的解题套路实战 哈夫曼树
 * 一块金条切成两半，是需要花费和长度数值一样的铜板的
 * 比如长度为20的金条，不管怎么切都要花费20个铜板。一群人想整分整块金条，怎么分最省铜板，
 * 例如给定数组｛10,20,30},代表一共三个人整块金条长度为60金条,要分成10. 20. 30三个部分
 * 如果先把长度60的金条分成10和50花费60;再把长度50的金条分成20和30花费50；一共花费110铜板
 * 但如果先把长度60的金条分成30和30花费60;再把长度30金条分成10和20花费30,一共花费90铜板
 * 要求输入一个数组返回分割的最小代价。
 *
 * 解题思路：使用小根堆排序（O(N*logN),O(1)）,然后从小开始，每两个合成一个节点，得到的节点在跟下一个合成新的节点。最终形成一个二叉树
 * 所有的非叶子节点和即为要求的结果
 */
public class Code03_LessMoneySplitGold {
	public static int lessMoney1(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		return process(arr, 0);
	}

	public static int process(int[] arr, int pre) {
		if (arr.length == 1) {
			return pre;
		}
		int ans = Integer.MAX_VALUE;
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				ans = Math.min(ans, process(copyAndMergeTwo(arr, i, j), pre + arr[i] + arr[j]));
			}
		}
		return ans;
	}

	public static int[] copyAndMergeTwo(int[] arr, int i, int j) {
		int[] ans = new int[arr.length - 1];
		int ansi = 0;
		for (int arri = 0; arri < arr.length; arri++) {
			if (arri != i && arri != j) {
				ans[ansi++] = arr[arri];
			}
		}
		ans[ansi] = arr[i] + arr[j];
		return ans;
	}


	public static int lessMoneySplitGold(int[] arr){
		//创建一个小根堆
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		//将所有的样本压入小根堆
		for (int i = 0; i < arr.length; i++) {
			queue.offer(arr[i]);
		}
		//遍历小根堆合成结果
		int ret=0;
		int i;
		while (queue.size()>1){
			i = queue.poll()+queue.poll();
			ret +=i;
			queue.offer(i);
		}
		return ret;
	}


	// for test
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * (maxValue + 1));
		}
		return arr;
	}

	public static void main(String[] args) {
		int testTime = 100000;
		int maxSize = 6;
		int maxValue = 1000;
		for (int i = 0; i < testTime; i++) {
			int[] arr = generateRandomArray(maxSize, maxValue);
			if (lessMoney1(arr) != lessMoneySplitGold(arr)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}

}
