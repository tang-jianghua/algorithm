package main.java.algorithm.huawei;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 题目描述
 * 输入一个int型整数，按照从右向左的阅读顺序，返回一个不含重复数字的新的整数。
 * <p>
 * 输入描述:
 * 输入一个int型整数
 * <p>
 * 输出描述:
 * 按照从右向左的阅读顺序，返回一个不含重复数字的新的整数
 *
 * @author tangjianghua
 * @date 2020/11/19
 */
public class HJ9 {

    public static void main1(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s;
        boolean[] ints = new boolean[10];
        while ((s = bufferedReader.readLine()) != null) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = s.length() - 1; i >= 0; i--) {
                char c = s.charAt(i);
                int index = c - 48;
                if (!ints[index]) {
                    ints[index] = true;
                    stringBuilder.append(c);
                }
            }
            System.out.println(stringBuilder);
        }
    }

    public static void main(String[] args) throws IOException {
        InputStream in = System.in;
        char c;
        boolean[] ints = new boolean[10];
        Stack<Character> stack = new Stack<>();
        while ((c = (char) in.read()) != -1) {
            if (c == '\n') {
                StringBuilder stringBuilder = new StringBuilder();
                while (!stack.isEmpty()) {
                    c = stack.pop();
                    int index = c - 48;
                    if (!ints[index]) {
                        ints[index] = true;
                        stringBuilder.append(c);
                    }
                }
                System.out.println(stringBuilder);
                ints = new boolean[10];
            }else{
                stack.push(c);
            }
        }
    }
}
