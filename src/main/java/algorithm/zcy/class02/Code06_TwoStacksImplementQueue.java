package main.java.algorithm.zcy.class02;

import java.util.Stack;

/**
 * @author tangjianghua
 * date 2020/6/22
 * time 15:08
 */
public class Code06_TwoStacksImplementQueue {

    public static class TwoStacksQueue<T> {

        private Stack<T> mainStack;
        private Stack<T> helpStack;

        public TwoStacksQueue() {
            mainStack = new Stack<>();
            helpStack = new Stack<>();
        }

        public void add(T t) {
            mainStack.add(t);
        }

        public T poll() {
            pushToHelp();
            return helpStack.pop();
        }


        public T peek() {
            pushToHelp();
            if (!helpStack.isEmpty()) {
                return helpStack.peek();
            }
            return null;
        }

        private void pushToHelp() {
            if (helpStack.isEmpty() && !mainStack.isEmpty()) {
                while (!mainStack.isEmpty()) {
                    helpStack.push(mainStack.pop());
                }
            }
        }
    }


    public static void main(String[] args) {
        TwoStacksQueue test = new TwoStacksQueue();
        test.add(1);
        test.add(2);
        test.add(3);
        System.out.println(test.peek());
        System.out.println(test.poll());
        System.out.println(test.peek());
        System.out.println(test.poll());
        System.out.println(test.peek());
        System.out.println(test.poll());
    }
}
