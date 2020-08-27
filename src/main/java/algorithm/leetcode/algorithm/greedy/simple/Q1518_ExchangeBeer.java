package main.java.algorithm.leetcode.algorithm.greedy.simple;

/**
 * 小区便利店正在促销，用 numExchange 个空酒瓶可以兑换一瓶新酒。你购入了 numBottles 瓶酒。
 * <p>
 * 如果喝掉了酒瓶中的酒，那么酒瓶就会变成空的。
 * <p>
 * 请你计算 最多 能喝到多少瓶酒。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/water-bottles
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @auth tangjianghua
 * @date 2020/7/28
 */
public class Q1518_ExchangeBeer {

    /**
     * 主函数
     *
     * @param numBottles  购入
     * @param numExchange 兑换代价
     * @return 最多喝多少酒
     */
    public static int numWaterBottles(int numBottles, int numExchange) {
        //过滤
        if (numBottles < numExchange) {
            return numBottles;
        }
        return process(0, numBottles, numExchange);
    }

    /**
     * 递归函数设计
     *
     * @param empty       空瓶子数
     * @param numBottles  有酒的瓶子数
     * @param numExchange 兑换代价 传递参数
     * @return 还有多少瓶可以喝
     */
    public static int process(int empty, int numBottles, final int numExchange) {
        //base case
        //当空酒瓶和实酒瓶总数不够换的时候，只能喝实酒瓶的酒
        if (empty + numBottles < numExchange) {
            return numBottles;
        }
        //当没有酒可以喝的时候只能换
        if(numBottles==0){
            return process(empty - numExchange, numBottles+1, numExchange);
        }
        //当空酒瓶不够兑换的时候，只能喝一瓶实酒瓶
        if (empty < numExchange) {
            return 1 + process(empty + 1, numBottles - 1, numExchange);
        }

        //当空酒瓶可兑换的时候，对比换与不换的结果，取更大
        int max = -1;

        //不换 喝一瓶
        int i1 = 1 + process(empty + 1, numBottles - 1, numExchange);
        //换 喝一瓶
        int i2 = process(empty - numExchange, numBottles+1, numExchange);

        max = Math.max(max, Math.max(i1, i2));
        return max;
    }

    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        System.out.println(numWaterBottles(9, 3));
        System.out.println(System.currentTimeMillis()-l);
    }

}
