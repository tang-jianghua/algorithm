package main.java.algorithm.zcy.class11;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 规规定1和A对应、2和B对应、3和C对应～，
 * 那么一个数字字符串比如”111”就可以转化为： "AAA"、"KA"和"AK"
 * 给定一个只有数字字符组成的字符串Str，返回有多少种转化结果
 * 从左向右的尝试模型
 * @auth tangjianghua
 * @date 2020/7/26
 */
public class Code06_ConvertToLetterString {

    /**
     * 先构建主函数：
     * @param str
     * @return 结果集合
     */
    public static int number(String str){
        char[] chars = str.toCharArray();

        return process(chars,0);

    }

    /**
     * 构建一个递归函数，从左至右
     * 根据题目要求可知，构成一个字符的范围最多两位，[0,26]。
     * 要对一位和两位进行合并，
     * @param chars
     * @param i
     * @return 类型数
     */
    public static int process(char[] chars,int i){

        //先写base case
        if(i==chars.length){
            //空 或者i-1已选的
            return 1;
        }

        //0不可转
        if(chars[i]=='0'){
            return 0;
        }

        //如果i位置为1
        if(chars[i]=='1'){
            //选择单个
            int res = process(chars, i + 1);// (i)作为单独的部分，后续有多少种方法
            if(i+1<chars.length){
                //选择双个
                res += process(chars,i+2);// (i和i+1)作为单独的部分，后续有多少种方法
            }
            return res;
        }
        //如果i位置为2
        if(chars[i]=='2'){
            //选择单个
            int res = process(chars, i + 1);
            if(i+1<chars.length&&(chars[i+1]-'a')>=0&&(chars[i+1]-'a')<=6){
                //选择双个
                res += process(chars,i+2);// (i和i+1)作为单独的部分，后续有多少种方法
            }
            return res;
        }

        //不等于1也不等于2，只能单独作为一个部分
        return process(chars,i+1);
    }

    public static void main(String[] args) {
        //AAAA KAA AKA AAK KK
        System.out.println(number("1111"));
        //System.out.println(dpWays2("11111"));
    }
}
