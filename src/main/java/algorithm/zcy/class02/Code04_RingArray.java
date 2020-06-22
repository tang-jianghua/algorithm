package main.java.algorithm.zcy.class02;

/**
 * 数组实现队列和栈（ringbuffer）
 *
 * @author tangjianghua
 * date 2020/6/22
 * time 14:21
 */
public class Code04_RingArray {


    public class MyQueue {
        int[] arr;

        int size;

        int capacity;

        int popIndex;

        int pushIndex;

        public MyQueue(int capacity) {
            this.capacity = capacity;
            arr = new int[capacity];
        }

        public void push(int value) {
            if (size == capacity) {
                throw new RuntimeException("队列已满!");
            }
            arr[pushIndex] = value;
            popIndex = nextIndex(popIndex);
            size++;
        }

        public int pop() {
            if (size == 0) {
                throw new RuntimeException("队列已空!");
            }
            int ret = arr[popIndex];
            popIndex = nextIndex(popIndex);
            size--;
            return ret;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        //记得返回值重新赋值
        private int nextIndex(int index) {
            if (index == capacity - 1) {
                index = 0;
            } else {
                index++;
            }
            return index;
        }
    }

}
