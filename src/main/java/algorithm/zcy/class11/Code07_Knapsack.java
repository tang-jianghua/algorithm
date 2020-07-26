package main.java.algorithm.zcy.class11;

/**
 * 从左往右的尝试模型2
 * 给定两个长度都为N的数组weights和values,
 * weights和values分别代表i号物品的重量和价值。 给定一个正数bag，表示一个载重bag的袋子， 你装的物品不能超过这个重量。
 * 返回你能装下最多的价值是多少？
 * @auth tangjianghua
 * @date 2020/7/26
 */
public class Code07_Knapsack {

    /**
     * 主函数
     * @param weights
     * @param values
     * @param bag
     * @return
     */
    public static int maxValue(final int[] weights,final int[] values,int bag){
        return process1(weights,values,bag,0,0);
    }

    /**
     * 递归函数
     * 从左至右
     * @param weights
     * @param values
     * @param bag 包裹能承载的重量
     * @param i 下一个物品
     * @param alreadyW 已经承装的重量
     * @return
     */
    public static int process1(final int[] weights,final int[] values,int bag,int i,int alreadyW){

        //todo 犯错：一定要先判断是否超载！！！！
        //base case 2 超载
        if(alreadyW>bag){
            return -1;
        }
        //base case 1 没有添加
        if(i==weights.length){
            return 0;
        }

        int no = process1(weights, values, bag, i + 1, alreadyW);
        int yes = process1(weights, values, bag, i + 1, alreadyW+weights[i]);

        int yesValue=-1;
        //如果加了，计算新的价格
        if(yes!=-1){
            yesValue=values[i]+yes;
        }
        return Math.max(no,yesValue);
    }

    /**
     *
     * @param weights
     * @param values
     * @param bag
     * @param i
     * @param rest 剩余空间
     * @return
     */
    public static int process2(final int[] weights,final int[] values,int i,int rest){
        if(rest<0){
            return -1;
        }
        if(i==weights.length){
            return 0;
        }

        int i1 = process2(weights, values, i + 1, rest);
        int i2 = process2(weights, values, i + 1, rest - weights[i]);
        int p2=i2==-1?-1:i2+values[i];
        return Math.max(i1,p2);

    }

    /**
     * 主函数
     * @param weights
     * @param values
     * @param bag
     * @return
     */
    public static int maxValue2(final int[] weights,final int[] values,int bag){
        return process2(weights,values,0,bag);
    }

    public static void main(String[] args) {
        int[] weights = { 3, 2, 4, 7 };
        int[] values = { 5, 6, 3, 19 };
        int bag = 11;
        System.out.println(maxValue(weights, values, bag));
        System.out.println(maxValue2(weights, values, bag));
        //System.out.println(dpWay(weights, values, bag));
    }


}
