package main.java.algorithm.zcy.class11;

import java.util.Stack;

/**
 * 给出一个栈，要求不申请额外空间的情况下将栈中的元素逆序
 *
 * @auth tangjianghua
 * @date 2020/7/26
 */
public class Code04_ReverseStackUsingRecursive {

    /**
     * 先设计一个递归方法，每次从栈中拿出最底下的元素
     *
     * @param stack
     * @return 最底层的元素
     */
    public static int f1(Stack<Integer> stack) {
        //先写basecase
        Integer pop = stack.pop();
        if (stack.isEmpty()) {
            return pop;
        }
        //拿出最底层返回
        int i = f1(stack);
        //将上一层的压入
        stack.push(pop);
        return i;
    }

    /**
     * 再设计一个递归方法，依次压入方法1中返回的元素
     *
     * @param stack
     */
    public static void reverse(Stack<Integer> stack) {

        //先写basecase
        if (stack.isEmpty()) return;

        int i = f1(stack);
        reverse(stack);
        stack.push(i);
    }

    public static void main(String[] args) {
        Stack<Integer> test = new Stack<Integer>();
        test.push(1);
        test.push(2);
        test.push(3);
        test.push(4);
        test.push(5);
        reverse(test);
        while (!test.isEmpty()) {
            System.out.println(test.pop());
        }

    }


}
