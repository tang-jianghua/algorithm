package main.java.algorithm.zcy.class01;

import main.java.algorithm.util.AlgorithmUtil;

/**
 * 认识异或运算
 *   概念：无进位相加。即相同位上相同为0，不同为1
 * -一个数组中，有一种数出现奇数次，其他数出现偶数次，请找出这个奇数，要求时间复杂度O(N)
 * -一个数组中，有两种数出现奇数次，其他数出现偶数次，请找出这两个奇数，要求时间复杂度O(N)
 * @author tangjianghua
 * date 2020/6/19
 * time 16:52
 */
public class Code007_EvenTimesOddTimes {

    public static void main(String[] args) {
      /*  int[] arr1={1,1,1,2,2,3,3};
        System.out.println(findOne(arr1));*/


        int[] arr2={1,1,1,2,2,2,3,3,4,4};
        AlgorithmUtil.printArr(findTwo(arr2));
    }


    /**
     * 一个数组中，有一种数出现奇数次，其他数出现偶数次，请找出这个奇数，要求时间复杂度O(N)
     * @param arr
     * @return
     */
    public static int findOne(int[] arr){
        int ret=0;
        for (int i = 0; i < arr.length; i++) {
            ret ^=arr[i];
        }
        return ret;
    }



    /**
     * 一个数组中，有两种数出现奇数次，其他数出现偶数次，请找出这两个奇数，要求时间复杂度O(N)
     * 思路：
     * -先把所有数异或，得到两个奇数的异或值
     * -找出这个异或值最右端的1
     * -用这个1将数组分为两类
     * @param arr
     * @return
     */
    public static int[] findTwo(int[] arr){
        //先把所有数异或，得到两个奇数的异或值
        int ret=0;
        //O(N)
        for (int i = 0; i < arr.length; i++) {
            ret ^=arr[i];
        }
        int rightOne = ret&(~ret+1);
        int one=0;
        //O(N)
        for (int i = 0; i < arr.length; i++) {
            if((rightOne&arr[i])==rightOne){
                one ^= arr[i];
            }
        }
        return new int[]{one,ret^one};
    }

}
