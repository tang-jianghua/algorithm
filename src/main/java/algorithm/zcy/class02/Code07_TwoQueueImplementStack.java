package main.java.algorithm.zcy.class02;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 使用两个队列实现栈
 * @author tangjianghua
 * date 2020/6/22
 * time 15:30
 */
public class Code07_TwoQueueImplementStack {

    public static class TwoQueueStack<T> {
        private Queue<T> mainQueue;
        private Queue<T> helpQueue;

        public TwoQueueStack() {
            mainQueue = new LinkedList();
            helpQueue = new LinkedList();
        }

        public void push(T t) {
            mainQueue.offer(t);
        }

        public T pop() {
            if (mainQueue.isEmpty()) {
                return null;
            } else {
                while (mainQueue.size() > 1) {
                    helpQueue.offer(mainQueue.poll());
                }
                final T t = mainQueue.poll();
                Queue tmp = helpQueue;
                helpQueue = mainQueue;
                mainQueue = tmp;
                return t;
            }
        }

        public T peek() {
            if (mainQueue.isEmpty()) {
                return null;
            } else {
                while (mainQueue.size() > 1) {
                    helpQueue.offer(mainQueue.poll());
                }
                final T t = mainQueue.poll();
                helpQueue.offer(t);
                Queue tmp = helpQueue;
                helpQueue = mainQueue;
                mainQueue = tmp;
                return t;
            }
        }

        public boolean isEmpty() {
            return mainQueue.isEmpty();
        }

        public int size(){
            return mainQueue.size();
        }

    }

    public static void main(String[] args) {
        System.out.println("test begin");
        TwoQueueStack<Integer> myStack = new TwoQueueStack<>();
        Stack<Integer> test = new Stack<>();
        int testTime = 1000000;
        int max = 1000000;
        for (int i = 0; i < testTime; i++) {
            if (myStack.isEmpty()) {
                if (!test.isEmpty()) {
                    System.out.println("Oops");
                }
                int num = (int) (Math.random() * max);
                myStack.push(num);
                test.push(num);
            } else {
                if (Math.random() < 0.25) {
                    int num = (int) (Math.random() * max);
                    myStack.push(num);
                    test.push(num);
                } else if (Math.random() < 0.5) {
                    if (!myStack.peek().equals(test.peek())) {
                        System.out.println("Oops");
                    }
                } else if (Math.random() < 0.75) {
                    if (!myStack.pop().equals(test.pop())) {
                        System.out.println("Oops");
                    }
                } else {
                    if (myStack.isEmpty() != test.isEmpty()) {
                        System.out.println("Oops");
                    }
                }
            }
        }
        System.out.println("test finish!");

    }
}
