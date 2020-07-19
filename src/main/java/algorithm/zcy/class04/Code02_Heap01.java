package main.java.algorithm.zcy.class04;

import main.java.algorithm.util.AlgorithmUtil;

/**
 * 大根堆
 * @auth tangjianghua
 * @date 2020/7/19
 */
public class Code02_Heap01 {


    /**
     * 大根堆
     */
    public static class MyMaxHeap{

        private int[] arr;
        private int limit;
        private int heapSize;

        public MyMaxHeap(int limit) {
            arr=new int[limit];
            this.limit=limit;
            heapSize=0;
        }

        /**
         *
         * @param num
         */
        public void push(int num){
            if(heapSize>=limit){
                throw new RuntimeException("heap is full.");
            }
            arr[heapSize]=num;
            heapInsert(arr,heapSize++);
            //AlgorithmUtil.printArr(arr);
        }

        /**
         * 每次弹出最大值
         * @return
         */
        public int pop(){
            if (isEmpty()) {
                throw new RuntimeException("heap is empty");
            }
            int i = arr[0];
            AlgorithmUtil.swapArr(arr,0,--heapSize);
            //AlgorithmUtil.printArr(arr);
            heapify(arr,0,heapSize);
            //AlgorithmUtil.printArr(arr);
            return i;
        }

        /**
         * 从index开始heapify到heapSize
         * @param arr
         * @param index
         * @param heapSize
         */
        private void heapify(int arr[],int index,int heapSize){
            int left;
            while ((left = index*2+1) <heapSize){
                int largiest = (left+1)<heapSize && arr[left+1]>arr[left]?(left+1):left;
                if(arr[index]<arr[largiest]){
                    AlgorithmUtil.swapArr(arr,index,largiest);
                    index=largiest;
                }else{
                    break;
                }
                //AlgorithmUtil.printArr(arr);
            }
        }

        public boolean isFull(){
            return heapSize==limit;
        }

        public boolean isEmpty(){
            return heapSize==0;
        }


        private void heapInsert(int arr[],int index){

            int parent;
            while ((parent=(index-1)/2)>=0 && arr[index]>arr[parent] ){
                AlgorithmUtil.swapArr(arr,index,parent);
                index = parent;
            }
        }
        public void print(){
            AlgorithmUtil.printArr(arr);
        }
    }


    /**
     * 大根堆
     */
    public static class MyMinHeap{

        private int[] arr;
        private int limit;
        private int heapSize;

        public MyMinHeap(int limit) {
            arr=new int[limit];
            this.limit=limit;
            heapSize=0;
        }

        /**
         *
         * @param num
         */
        public void push(int num){
            if(heapSize>=limit){
                throw new RuntimeException("heap is full.");
            }
            arr[heapSize]=num;
            heapInsert(arr,heapSize++);
            //AlgorithmUtil.printArr(arr);
        }

        /**
         * 每次弹出最大值
         * @return
         */
        public int pop(){
            if (isEmpty()) {
                throw new RuntimeException("heap is empty");
            }
            int i = arr[0];
            AlgorithmUtil.swapArr(arr,0,--heapSize);
            //AlgorithmUtil.printArr(arr);
            heapify(arr,0,heapSize);
            //AlgorithmUtil.printArr(arr);
            return i;
        }

        /**
         * 从index开始heapify到heapSize
         * @param arr
         * @param index
         * @param heapSize
         */
        private void heapify(int arr[],int index,int heapSize){
            int left;
            while ((left = index*2+1) <heapSize){
                int min = (left+1)<heapSize && arr[left+1]<arr[left]?(left+1):left;
                if(arr[index]>arr[min]){
                    AlgorithmUtil.swapArr(arr,index,min);
                    index=min;
                }else{
                    break;
                }
                //AlgorithmUtil.printArr(arr);
            }
        }

        public boolean isFull(){
            return heapSize==limit;
        }

        public boolean isEmpty(){
            return heapSize==0;
        }


        private void heapInsert(int arr[],int index){

            int parent;
            while ((parent=(index-1)/2)>=0 && arr[index]<arr[parent] ){
                AlgorithmUtil.swapArr(arr,index,parent);
                index = parent;
            }
        }
        public void print(){
            AlgorithmUtil.printArr(arr);
        }
    }

    public static class RightMaxHeap{


        private int[] arr;
        private int limit;
        private int heapSize;

        public RightMaxHeap(int limit) {
            arr=new int[limit];
            this.limit=limit;
            heapSize=0;
        }

        /**
         *
         * @param num
         */
        public void push(int num){
            if(heapSize>=limit){
                throw new RuntimeException("heap is full.");
            }
            arr[heapSize++]=num;
        }

        /**
         * 每次弹出最大值
         * @return
         */
        public int pop(){
            if (isEmpty()) {
                throw new RuntimeException("heap is empty");
            }
            int max =0;
            for (int i = 0; i < heapSize; i++) {
                if(arr[i]>arr[max]){
                    max=i;
                }
            }
            int i = arr[max];
            arr[max]=arr[--heapSize];
            return i;
        }


        public boolean isFull(){
            return heapSize==limit;
        }

        public boolean isEmpty(){
            return heapSize==0;
        }


    }

    public static void main(String[] args) {
        MyMaxHeap myMaxHeap = new MyMaxHeap(10);
        for (int i = 0; i < 10; i++) {
            myMaxHeap.push(i);
        }
        while (!myMaxHeap.isEmpty()){
            System.out.println(myMaxHeap.pop());
        }


		int value = 1000;
		int limit = 100;
		int testTimes = 1000000;
		for (int i = 0; i < testTimes; i++) {
			int curLimit = (int) (Math.random() * limit) + 1;
			MyMaxHeap my = new MyMaxHeap(curLimit);
			RightMaxHeap test = new RightMaxHeap(curLimit);
			int curOpTimes = (int) (Math.random() * limit);
			for (int j = 0; j < curOpTimes; j++) {
				if (my.isEmpty() != test.isEmpty()) {
					System.out.println("Oops!");
				}
				if (my.isFull() != test.isFull()) {
					System.out.println("Oops!");
				}
				if (my.isEmpty()) {
					int curValue = (int) (Math.random() * value);
					my.push(curValue);
					test.push(curValue);
				} else if (my.isFull()) {
					if (my.pop() != test.pop()) {
						System.out.println("Oops!");
					}
				} else {
					if (Math.random() < 0.5) {
						int curValue = (int) (Math.random() * value);
						my.push(curValue);
						test.push(curValue);
					} else {
						if (my.pop() != test.pop()) {
							System.out.println("Oops!");
						}
					}
				}
			}
		}
		System.out.println("finish!");
    }

}
