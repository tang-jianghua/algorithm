package main.java.algorithm.zcy.class02;

import java.util.Stack;

/**
 * @author tangjianghua
 * date 2020/6/22
 * time 14:40
 */
public class Code05_GetMinStack {


    public static class Mystack {

        private Stack<Integer> mainStack;
        private Stack<Integer> minStack;

        public Mystack() {
            mainStack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(Integer value) {
            //只有在值比当前最小值小时才push进minStack
            if (minStack.isEmpty() || value <= getMin()) {
                minStack.push(value);
            }
            mainStack.push(value);
        }

        public Integer pop() {
            if (mainStack.isEmpty()) {
                throw new RuntimeException("stack is empty.");
            }
            final Integer pop = mainStack.pop();
            if (pop.equals(getMin())) {
                minStack.pop();
            }
            return pop;
        }

        public Integer getMin() {
            if (minStack.isEmpty()) {
                throw new RuntimeException("stack is empty.");
            }
            return minStack.peek();
        }
    }

    public static class Mystack2 {

        private Stack<Integer> mainStack;
        private Stack<Integer> minStack;

        public Mystack2() {
            mainStack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(Integer value) {
            if (minStack.isEmpty() || value <= getMin()) {
                minStack.push(value);
            } else {
                minStack.push(getMin());
            }
            mainStack.push(value);
        }

        public Integer pop() {
            if (mainStack.isEmpty()) {
                throw new RuntimeException("stack is empty.");
            }
            minStack.pop();
            return mainStack.pop();
        }

        public Integer getMin() {
            if (minStack.isEmpty()) {
                throw new RuntimeException("stack is empty.");
            }
            return minStack.peek();
        }
    }

    public static void main(String[] args) {
        final Mystack mystack = new Mystack();
        mystack.push(2);
        mystack.push(3);
        mystack.push(5);
        mystack.push(2);
        System.out.println(mystack.getMin());
        System.out.println(mystack.pop());
        System.out.println(mystack.getMin());
        System.out.println(mystack.pop());
        System.out.println(mystack.getMin());
        System.out.println(mystack.pop());
        System.out.println(mystack.getMin());
        System.out.println(mystack.pop());


    }
}
