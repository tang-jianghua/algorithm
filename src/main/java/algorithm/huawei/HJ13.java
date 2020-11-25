package main.java.algorithm.huawei;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 题目描述
 * 将一个英文语句以单词为单位逆序排放。例如“I am a boy”，逆序排放后为“boy a am I”
 * 所有单词之间用一个空格隔开，语句中除了英文字母外，不再包含其他字符
 * <p>
 * 输入描述:
 * 将一个英文语句以单词为单位逆序排放。
 * <p>
 * 输出描述:
 * 得到逆序的句子
 *
 * @author tangjianghua
 * @date 2020/11/19
 */
public class HJ13 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s;
        Stack<String> stack = new Stack<>();
        while ((s = bufferedReader.readLine()) != null) {
            String[] strings = s.split(" ");
            for (int i = 0; i < strings.length; i++) {
                stack.push(strings[i]);
            }
            StringBuilder stringBuilder = new StringBuilder();
            while (!stack.isEmpty()) {
                stringBuilder.append(" ").append(stack.pop());
            }
            stringBuilder.delete(0, 1);
            System.out.println(stringBuilder);
        }
    }
}
