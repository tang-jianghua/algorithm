package main.java.algorithm.zcy.class12;

/**
 * 背包问题动态规划
 * <p>
 * 从左往右的尝试模型2
 * 给定两个长度都为N的数组weights和values,
 * weights和values分别代表i号物品的重量和价值。 给定一个正数bag，表示一个载重bag的袋子， 你装的物品不能超过这个重量。
 * 返回你能装下最多的价值是多少？
 *
 * @auth tangjianghua
 * @date 2020/7/27
 */
public class Code03_Knapsack {


    /**
     * 暴力递归
     *
     * @param weights
     * @param values
     * @param bag
     * @return
     */
    public static int maxValue(int[] weights, int[] values, int bag) {
        //过滤
        if (bag <= 0) {
            return 0;
        }
        return process(weights, values, bag, 0);
    }

    public static int process(final int[] weights, final int[] values, int rest, int i) {
        //base case
        if (rest < 0) return -1;

        if (i == weights.length) return 0;

        int process = process(weights, values, rest, i + 1);

        int p2 = -1;
        if (rest - weights[i] > 0) {
            p2 = process(weights, values, rest - weights[i], i + 1);
        }
        if (p2 != -1) {
            p2 += values[i];
        }
        return Math.max(process, p2);
    }

    /**
     * 记忆搜索优化
     *
     * @param weights
     * @param values
     * @param bag
     * @return
     */
    public static int maxValue2(int[] weights, int[] values, int bag) {
        //过滤
        if (bag <= 0) {
            return 0;
        }
        int N = weights.length;
        int[][] cache = new int[bag + 1][N+1];
        for (int i = 0; i <= bag; i++) {
            for (int j = 0; j <= N; j++) {
                cache[i][j] = -1;
            }
        }
        return process2(weights, values, bag, 0, cache);
    }

    public static int process2(final int[] weights, final int[] values, int rest, int i, int[][] cache) {

        if (cache[rest][i] != -1) {
            return cache[rest][i];
        }

        //base case
        if (rest < 0) {
            cache[rest][i] = -1;
            return cache[rest][i];
        }

        if (i == weights.length) {
            cache[rest][i] = 0;
            return cache[rest][i];
        }

        int process = process2(weights, values, rest, i + 1, cache);

        int p2 = -1;
        if (rest - weights[i] > 0) {
            p2 = process2(weights, values, rest - weights[i], i + 1, cache);
        }
        if (p2 != -1) {
            p2 += values[i];
        }
        cache[rest][i] = Math.max(process, p2);
        return cache[rest][i];
    }


    /**
     * 经典动态规划
     *
     * @param weights
     * @param values
     * @param bag
     * @return
     */
    public static int maxValue3(int[] weights, int[] values, int bag) {
        //过滤
        int[][] cache = new int[bag + 1][weights.length + 1];
        for (int i = 0; i <= bag; i++) {
            cache[i][weights.length] = 0;
        }
        for (int i = weights.length-1; i >=0 ; i--) {
            for (int rest = 0; rest <= bag; rest++) {

                int process = cache[rest][i + 1];

                int p2 = -1;
                if (rest - weights[i] >= 0) {
                    p2 = cache[rest - weights[i]][i + 1];
                }
                if (p2 != -1) {
                    p2 += values[i];
                }
                cache[rest][i] = Math.max(process, p2);
            }
        }

        return cache[bag][0];
    }


    public static void main(String[] args) {
        int[] weights = { 3, 2, 4, 7 };
        int[] values = { 5, 6, 3, 19 };
        int bag = 11;
        System.out.println(maxValue(weights, values, bag));
        System.out.println(maxValue2(weights, values, bag));
        System.out.println(maxValue3(weights, values, bag));
    }

}
