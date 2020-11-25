package main.java.algorithm.huawei;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * 题目描述
 * 给定n个字符串，请对n个字符串按照字典序排列。
 * 输入描述:
 * 输入第一行为一个正整数n(1≤n≤1000),下面n行为n个字符串(字符串长度≤100),字符串中只含有大小写字母。
 * 输出描述:
 * 数据输出n行，输出结果为按照字典序排列的字符串。
 *
 * @author tangjianghua
 * @date 2020/11/19
 */
public class HJ14 {


    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s;
        int size = 0;
        PriorityQueue<String> priorityQueue = new PriorityQueue<>();
        while ((s = bufferedReader.readLine()) != null) {
            if (size == 0) {
                size = Integer.parseInt(s);
                continue;
            }
            priorityQueue.add(s);
            if (--size == 0) {
                while (!priorityQueue.isEmpty()) {
                    System.out.println(priorityQueue.poll());
                }
            }
        }
    }
}
