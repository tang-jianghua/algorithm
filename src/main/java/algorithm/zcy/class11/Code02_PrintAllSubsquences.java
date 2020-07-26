package main.java.algorithm.zcy.class11;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * 给定一个字符串，打印所有可能的子序列
 * 从左向右的尝试模型
 *
 * @auth tangjianghua
 * @date 2020/7/26
 */
public class Code02_PrintAllSubsquences {

    /**
     * @param s 给定的字符串
     * @return 所有的子序列
     */
    public static List<String> subSquences(String s) {
        char[] chars = s.toCharArray();
        String string = "";
        ArrayList<String> strings = new ArrayList<>();
        process(chars, 0, string, strings);

        return strings;
    }

    /**
     * 构建一个递归函数，递归出字符数组上i位置选择或者不选的结果集
     */
    public static void process(char[] chars, int i, String str, List<String> result) {

        //先写basecase
        if (i == chars.length) {
            result.add(str);
            return;
        }

        //不选择
        process(chars, i + 1, str, result);

        //选择
        process(chars, i + 1, str + chars[i], result);

    }

    /**
     * @param s 给定的字符串
     * @return 所有不重复的子序列
     */
    public static HashSet<String> subSquencesNoRepeat(String s) {
        char[] chars = s.toCharArray();
        String string = "";
        HashSet<String> strings = new HashSet<>();
        processNoRepeat(chars, 0, string, strings);

        return strings;
    }

    /**
     * 在构建一个递归函数，这次要求去重，重复的不能打印
     * @param chars
     * @param i
     * @param str
     * @param result
     */
    public static void processNoRepeat(char[] chars, int i, String str, HashSet<String> result) {

        //先写basecase
        if (i == chars.length) {
            result.add(str);
            return;
        }

        //不选择
        processNoRepeat(chars, i + 1, str, result);

        //选择
        processNoRepeat(chars, i + 1, str + chars[i], result);

    }


    public static void main(String[] args) {
        Collection<String> abcd = subSquencesNoRepeat("aba");
        //List<String> abcd = subSquences("aba");
        abcd.stream().forEach(System.out::println);
    }
}
