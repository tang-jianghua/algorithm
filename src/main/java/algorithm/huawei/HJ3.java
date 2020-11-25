package main.java.algorithm.huawei;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * 明明想在学校中请一些同学一起做一项问卷调查，为了实验的客观性，他先用计算机生成了N个1到1000之间的随机整数（N≤1000），对于其中重复的数字，只保留一个，把其余相同的数去掉，不同的数对应着不同的学生的学号。然后再把这些数从小到大排序，按照排好的顺序去找同学做调查。请你协助明明完成“去重”与“排序”的工作(同一个测试用例里可能会有多组数据，希望大家能正确处理)。
 * <p>
 * <p>
 * 注：测试用例保证输入参数的正确性，答题者无需验证。测试用例不止一组。
 * <p>
 * 当没有新的输入时，说明输入结束。
 *
 * @author tangjianghua
 * @date 2020/11/18
 */
public class HJ3 {

    public static void m1() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int start = 0;
        String s;
        while (start == 0 && (s = bufferedReader.readLine()) != null) {
            int size = Integer.parseInt(s);
            start = size;
            SortedSet<Integer> set = new TreeSet<>();
            for (int i = 0; i < size; i++) {
                s = bufferedReader.readLine();
                set.add(Integer.parseInt(s));
                start--;
            }
            set.stream().forEach(System.out::println);
        }
    }

    /**
     * 桶排序
     *
     * @throws IOException
     */
    public static void m2() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while ((s = bufferedReader.readLine()) != null) {
            StringBuilder stringBuilder = new StringBuilder();
            int size = Integer.parseInt(s);
            //[1,1000]
            boolean[] count = new boolean[1001];
            for (int i = 0; i < size; i++) {
                s = bufferedReader.readLine();
                count[Integer.parseInt(s)] = true;
            }
            for (int i = 0; i < 1001; i++) {
                if (count[i]) {
                    stringBuilder.append(i).append("\n");
                }
            }
            System.out.print(stringBuilder);
        }
    }

    public static void main(String[] args) throws IOException {

        m2();
    }
}
