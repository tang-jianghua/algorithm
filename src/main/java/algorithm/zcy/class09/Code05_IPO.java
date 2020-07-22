package main.java.algorithm.zcy.class09;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 贪心算法的解题套路实战
 * 输入正数数组costs、正数数组profits、正数K、正数M
 * costs[i]表示i号项目的花费
 * 正数数组profits[i］表示i号项目在扣除花费之后还能挣到的钱（利润）
 * K表示你只能串行的最多做k个项目
 * M表示你初始的资金 说明
 * 每做完一个项目 马上获得的收益 可以支持你去做下一个项目。不能并行的做项目
 * 愉出你最后获得的最大钱数。
 * <p>
 * 思路：使用小根堆+大根堆
 * 小根堆存所有未检查的项目
 * 大根堆存所有可做的项目
 *
 * @auth tangjianghua
 * @date 2020/7/22
 */
public class Code05_IPO {

    /**
     * 构建一个项目类，用来存放对应的花费和利润
     */
    public static class Project {
        private int cost;

        private int profit;

        public Project(int cost, int profit) {
            this.cost = cost;
            this.profit = profit;
        }
    }


    /**
     * @param costs   项目的花费
     * @param profits 利润
     * @param k       能串行的最多做k个项目
     * @param m       初始的资金
     * @return
     */
    public static int findMaximizedCapital(int[] costs, int[] profits, int k, int m) {

        //创建一个小根堆用来锁住项目，花费从小到大
        PriorityQueue<Project> lock = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));


        //创建一个大根堆用来存放解锁的项目，利润从大到小
        PriorityQueue<Project> unlock = new PriorityQueue<>((o1, o2) -> o2.profit - o1.profit);

        //把所有项目构建出来，压入lock中
        for (int i = 0; i < costs.length; i++) {
            lock.offer(new Project(costs[i], profits[i]));
        }
        //定义一个现有资金
        int sum = m;

        while (!lock.isEmpty()) {
            //如果资金足够，则压入解锁堆中
            for (int i = 0; i < k; i++) {
                if (!lock.isEmpty() && lock.peek().cost <= sum) {
                    Project poll = lock.poll();
                    sum -= poll.cost;
                    unlock.offer(poll);
                }
            }
            while (!unlock.isEmpty()) {
                Project poll = unlock.poll();
                sum += poll.profit + poll.cost;
            }
        }
        return sum;
    }
}
