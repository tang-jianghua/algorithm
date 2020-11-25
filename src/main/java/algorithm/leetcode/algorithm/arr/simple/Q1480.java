package main.java.algorithm.leetcode.algorithm.arr.simple;

import main.java.algorithm.util.AlgorithmUtil;

import java.util.Arrays;

/**
 * 给你一个数组 nums 。数组「动态和」的计算公式为：runningSum[i] = sum(nums[0]…nums[i]) 。
 *
 * 请返回 nums 的动态和。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3,4]
 * 输出：[1,3,6,10]
 * 解释：动态和计算过程为 [1, 1+2, 1+2+3, 1+2+3+4] 。
 * 示例 2：
 *
 * 输入：nums = [1,1,1,1,1]
 * 输出：[1,2,3,4,5]
 * 解释：动态和计算过程为 [1, 1+1, 1+1+1, 1+1+1+1, 1+1+1+1+1] 。
 * 示例 3：
 *
 * 输入：nums = [3,1,2,10,1]
 * 输出：[3,4,6,16,17]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/running-sum-of-1d-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author tangjianghua
 * @date 2020/11/24
 */
public class Q1480 {

    public static int[] runningSum(int[] nums) {
        int[] arr = new int[nums.length];
        arr[0]=nums[0];
        for (int i = 1; i < nums.length; i++) {
            arr[i] =arr[i-1]+nums[i];
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] ints = AlgorithmUtil.generatorRandomArr(10, 100);
        System.out.println(Arrays.toString(ints));
        int[] arr = runningSum(ints);
        System.out.println(Arrays.toString(arr));
    }
}
