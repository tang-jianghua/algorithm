package main.java.algorithm.zcy.class02;

import main.java.algorithm.util.AlgorithmUtil;

/**
 * @author tangjianghua
 * date 2020/6/22
 * time 16:05
 */
public class Code08_GetMax {

    public static int getMax(int[] arr){

        if(arr.length==0){
            throw new RuntimeException("empty arr");
        }
        return process(arr,0,arr.length-1);
    }

    public static int process(int[] arr,int l,int r){
          if(l==r){
              return arr[l];
          }
          int mid=l+((r-l)>>1);
          return Math.max(process(arr,l,mid),process(arr,mid+1,r));
    }

    public static void main(String[] args) {
        final int[] ints = AlgorithmUtil.generatorRandomArr(10, 100);
        AlgorithmUtil.printArr(ints);
        System.out.println(getMax(ints));
    }
}
