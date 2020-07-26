package main.java.algorithm.zcy.class11;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 给定一个字符串，要求打印这个字符串所有的排列方式
 * 从左向右的尝试模型
 * @auth tangjianghua
 * @date 2020/7/26
 */
public class Code03_PrintAllPermutations {

    /**
     * 先构建一个主函数
     * @param str 一个字符串
     * @reture 返回一个字符串集合
     */
    public static Collection permutations(String str){
        //先把字符串转为字符数组
        char[] chars = str.toCharArray();
        ArrayList<String> arrayList = new ArrayList<>();
        process(chars,0,arrayList);
        return arrayList;
    }

    /**
     * 构建一个递归函数，从0位置开始收集每个位置可能的情况.
     * 这里通过改变chars的顺序，basecase的chars顺序即为最终的字符串字符顺序，所以要记得恢复现场
     * @param chars 首先肯定要传递chars
     * @param i 要传递下一个要选择的下角标
     * @param strs 要传递结果集，在basecase时进行添加
     */
    public static void process(char[] chars,int i,Collection<String> strs){
        //先写basecase
        if(i==chars.length){
            strs.add(new String(chars));
            //有没有return都行，因为后序不会进行
            return;
        }
        //从i开始，每个位置进行选定
        for (int j = i; j < chars.length; j++) {
            swap(chars,i,j);
            process(chars,i+1,strs);
            swap(chars,i,j);
        }
    }

    /**
     * 先构建一个主函数
     * @param str 一个字符串
     * @reture 返回一个字符串集合
     */
    public static Collection permutationsNoRepeat(String str){
        //先把字符串转为字符数组
        char[] chars = str.toCharArray();
        ArrayList<String> arrayList = new ArrayList<>();
        processNoRepeat(chars,0,arrayList);
        return arrayList;
    }

    /**
     * 构建一个递归函数，从0位置开始收集每个位置可能的情况.
     * 这里通过改变chars的顺序，basecase的chars顺序即为最终的字符串字符顺序，所以要记得恢复现场
     * 不重复打印，采用分支限界的方式
     * @param chars 首先肯定要传递chars
     * @param i 要传递下一个要选择的下角标
     * @param strs 要传递结果集，在basecase时进行添加
     */
    public static void processNoRepeat(char[] chars,int i,Collection<String> strs){
        //先写basecase
        if(i==chars.length){
            strs.add(new String(chars));
            //有没有return都行，因为后序不会进行
            return;
        }
        boolean[] booleans = new boolean[26];
        //从i开始，每个位置进行选定
        for (int j = i; j < chars.length; j++) {
            //在i位置，如果char[j]已经选择过，则直接跳过。
            if(!booleans[chars[j]-'a']){
                booleans[chars[j]-'a']=true;
                swap(chars,i,j);
                process(chars,i+1,strs);
                swap(chars,i,j);
            }
        }
    }

    private static void swap(char[] chars, int i, int i1) {
        if(i==i1) return;
        chars[i]=(char) (chars[i]^chars[i1]);
        chars[i1]=(char) (chars[i]^chars[i1]);
        chars[i]=(char) (chars[i]^chars[i1]);
    }


    public static void main(String[] args) {
        //Collection<String> abcd = permutations("aba");
        Collection<String> abcd = permutationsNoRepeat("aba");
        abcd.stream().forEach(System.out::println);
    }

}
