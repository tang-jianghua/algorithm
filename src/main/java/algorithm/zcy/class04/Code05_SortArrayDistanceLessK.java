package main.java.algorithm.zcy.class04;

import main.java.algorithm.util.AlgorithmUtil;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 *
 * @auth tangjianghua
 * @date 2020/7/19
 */
public class Code05_SortArrayDistanceLessK {


    public static void sortedArrDistanceLessK(int[] arr,int k){

        //AlgorithmUtil.printArr(arr);

        //使用自己的大根堆
        Code02_Heap01.MyMinHeap myMinHeap = new Code02_Heap01.MyMinHeap(Math.min(arr.length,k+1));

        //先把前k个元素加入大根堆中。
        int index;
        for (index=0; index<=Math.min(arr.length-1,k); index++) {
            //logK
            myMinHeap.push(arr[index]);
        }
        //myMinHeap.print();

        //然后把剩下的依次加入，没加入一个取出最大的根，
        int i;
        for (i = 0; index<arr.length; i++,index++) {
            arr[i]=myMinHeap.pop();
            myMinHeap.push(arr[index]);
            //AlgorithmUtil.printArr(arr);
            //myMinHeap.print();
        }

        //N*logK
        while (!myMinHeap.isEmpty()){
            arr[i++]=myMinHeap.pop();
        }
    }

    public static void sortedArrDistanceLessKWithPriorityQueue(int[] arr,int k){

        //AlgorithmUtil.printArr(arr);

        //使用自己的大根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();

        //先把前k个元素加入大根堆中。
        int index;
        for (index=0; index<=Math.min(arr.length-1,k); index++) {
            heap.add(arr[index]);
        }
        //myMinHeap.print();

        //然后把剩下的依次加入，没加入一个取出最大的根，
        int i;
        for (i = 0; index<arr.length; i++,index++) {
            arr[i]=heap.poll();
            heap.add(arr[index]);
            //AlgorithmUtil.printArr(arr);
            //myMinHeap.print();
        }

        while (!heap.isEmpty()){
            arr[i++]=heap.poll();
        }

    }
    // for test
    public static int[] randomArrayNoMoveMoreK(int maxSize, int maxValue, int K) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        // 先排个序
        Arrays.sort(arr);
        // 然后开始随意交换，但是保证每个数距离不超过K
        // swap[i] == true, 表示i位置已经参与过交换
        // swap[i] == false, 表示i位置没有参与过交换
        boolean[] isSwap = new boolean[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int j = Math.min(i + (int) (Math.random() * (K + 1)), arr.length - 1);
            if (!isSwap[i] && !isSwap[j]) {
                isSwap[i] = true;
                isSwap[j] = true;
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
        }
        return arr;
    }
    public static void main(String[] args) {
        System.out.println("test begin");
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int k = (int) (Math.random() * 5) + 1;
            int[] arr = randomArrayNoMoveMoreK(maxSize, maxValue, k);
            int[] arr1 = AlgorithmUtil.copyArr(arr);
            int[] arr2 = AlgorithmUtil.copyArr(arr);
            //sortedArrDistanceLessK(arr1, k);
            sortedArrDistanceLessKWithPriorityQueue(arr1, k);
            Arrays.sort(arr2);
            if (!AlgorithmUtil.isEqual(arr1, arr2)) {
                succeed = false;
                System.out.println("K : " + k);
                AlgorithmUtil.printArr(arr);
                AlgorithmUtil.printArr(arr1);
                AlgorithmUtil.printArr(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }

}
