package main.java.algorithm.util;

import java.util.logging.Logger;

/**
 * @author tangjianghua
 * date 2020/6/11
 * time 19:16
 */
public class AlgorithmUtil {

    private static Logger logger = Logger.getLogger(AlgorithmUtil.class.getName());

    /**
     * 交换数组中n和m的值
     *
     * @param arr
     * @param n
     * @param m
     */
    public static void swapArr(int[] arr, int n, int m) {
        if (n < 0 || n >= arr.length || m < 0 || m >= arr.length) {
            logger.warning("超出范围");
            return;
        }
        if(n == m){
            logger.warning("无需交换");
            return;
        }
        arr[n] = arr[n] ^ arr[m];
        arr[m] = arr[n] ^ arr[m];
        arr[n] = arr[n] ^ arr[m];
    }

    public static int[] generatorRandomArr(int maxSize,int maxValue){

    }


}
