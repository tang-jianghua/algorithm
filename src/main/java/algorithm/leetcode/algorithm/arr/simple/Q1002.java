package main.java.algorithm.leetcode.algorithm.arr.simple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * 1002. 查找常用字符
 * 给定仅有小写字母组成的字符串数组 A，返回列表中的每个字符串中都显示的全部字符（包括重复字符）组成的列表。例如，如果一个字符在每个字符串中出现 3 次，但不是 4 次，则需要在最终答案中包含该字符 3 次。
 * <p>
 * 你可以按任意顺序返回答案。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：["bella","label","roller"]
 * 输出：["e","l","l"]
 * 示例 2：
 * <p>
 * 输入：["cool","lock","cook"]
 * 输出：["c","o"]
 *
 * @author tangjianghua
 * @date 2020/11/24
 */
public class Q1002 {

    /**
     * 执行用时：
     * 3 ms
     * , 在所有 Java 提交中击败了
     * 97.06%
     * 的用户
     * 内存消耗：
     * 38.8 MB
     * , 在所有 Java 提交中击败了
     * 22.32%
     * 的用户
     *
     * @param A
     * @return
     */
    public static List<String> commonCharsV1(String[] A) {
        //记录最终数组
        int[] all = new int[26];
        //记录当前字符串字符出现频率
        int[] cur;

        //["bella","label","roller"]
        for (int i = 0; i < A.length; i++) {

            //记录当前字符串字符出现频率
            cur = new int[26];

            //b e l l a
            char[] chars = A[i].toCharArray();
            for (int j = 0; j < chars.length; j++) {
                int c = chars[j] - 97;
                //第一轮 初始化最终记录
                if (i == 0) {
                    cur[c]++;
                    continue;
                }


                //如果该字符小于0，则不共享，直接跳过
                if (all[c] < 0) {
                    continue;
                }
                cur[c]++;

            }

            //遍历完当前字符串以后，需要和上一个字符串进行对比
            if (i == 0) {
                //第一轮把未添加的置为-1,代表没有添加过
                for (int j = 0; j < 26; j++) {
                    if (cur[j] == 0) {
                        cur[j] = -1;
                    }
                }
                //第一次不对比
                all = cur;
            } else {
                for (int j = 0; j < 26; j++) {
                    //一旦有一个字符没有出现
                    if (all[j] > 0) {
                        if (cur[j] == 0) {
                            all[j] = -1;
                        } else {
                            all[j] = Math.min(all[j], cur[j]);
                        }
                        continue;
                    }
                }
            }
        }

        List<String> list = new ArrayList<>();
        for (
                int i = 0;
                i < all.length; i++) {
            if (all[i] > 0) {
                for (int j = 0; j < all[i]; j++) {
                    list.add(String.valueOf((char) (i + 97)));
                    //System.out.print((char) (i + 97) + ",");
                }
            }
        }
        return list;
    }

    /**
     * O(N^2)
     * <p>
     * 执行用时：
     * 20 ms
     * , 在所有 Java 提交中击败了
     * 12.89%
     * 的用户
     * 内存消耗：
     * 38.7 MB
     * , 在所有 Java 提交中击败了
     * 31.26%
     * 的用户
     *
     * @param A
     * @return
     */
    public static List<String> commonCharsV2(String[] A) {
        //记录最终数组
        int[] all = new int[26];

        //记录当前字符串字符出现频率
        int[] cur;

        //O(N)
        //["bella","label","roller"]
        for (int i = 0; i < A.length; i++) {

            //记录当前字符串字符出现频率
            cur = new int[26];

            //b e l l a
            char[] chars = A[i].toCharArray();
            //O(N)
            for (int j = 0; j < chars.length; j++) {
                int c = chars[j] - 97;
                //第一轮 初始化最终记录
                if (i == 0) {
                    cur[c]++;
                    continue;
                }


                //如果该字符小于0，则不共享，直接跳过
                if (all[c] == 0) {
                    continue;
                }
                cur[c]++;

            }

            //遍历完当前字符串以后，需要和上一个字符串进行对比
            if (i == 0) {
                //第一轮把未添加的置为-1,代表没有添加过
        /*        for (int j = 0; j < 26; j++) {
                    if (cur[j] == 0) {
                        cur[j] = -1;
                    }
                }*/
                //第一次不对比
                all = cur;
            } else {
                for (int j = 0; j < 26; j++) {
                    //一旦有一个字符没有出现
                    if (all[j] > 0) {
                        if (cur[j] == 0) {
                            all[j] = 0;
                        } else {
                            all[j] = Math.min(all[j], cur[j]);
                        }
                        continue;
                    }
                }
            }
        }

        List<String> list = new ArrayList<>();
        for (int i = 0; i < all.length; i++) {
            final String s = String.valueOf((char) (i + 97));
            IntStream.range(0, all[i])
                    //.peek(value -> System.out.print(s + ","))
                    .forEach(value -> list.add(s));
        }
        return list;
    }

    /**
     * O(N^2)
     * <p>
     * 执行用时：
     * 11 ms
     * , 在所有 Java 提交中击败了
     * 22.49%
     * 的用户
     * 内存消耗：
     * 38.8 MB
     * , 在所有 Java 提交中击败了
     * 27.93%
     * 的用户
     *
     * @param A
     * @return
     */
    public static List<String> commonCharsV3(String[] A) {
        //记录最终数组
        int[] all = new int[26];

        //记录当前字符串字符出现频率
        int[] cur;

        //O(N)
        //["bella","label","roller"]
        for (int i = 0; i < A.length; i++) {

            //记录当前字符串字符出现频率
            cur = new int[26];

            //b e l l a
            char[] chars = A[i].toCharArray();
            //O(N)
            for (int j = 0; j < chars.length; j++) {
                int c = chars[j] - 97;
                //第一个字符串 初始化最终记录
                if (i == 0) {
                    cur[c]++;
                    continue;
                }


                //如果该字符小于0，则不共享，直接跳过
                if (all[c] == 0) {
                    continue;
                }

                //记录当前字符
                cur[c]++;

            }

            //遍历完当前字符串以后，需要和上一个字符串进行对比
            if (i == 0) {
                //第一个字符串不对比
                all = cur;
                continue;
            }

            for (int j = 0; j < 26; j++) {
                //只对比出现过的字符
                if (all[j] > 0) {
                    //if (cur[j] == 0) {
                    //    all[j] = 0;
                    //} else {
                    all[j] = Math.min(all[j], cur[j]);
                    //}
                    continue;
                }
            }
        }

        List<String> list = new ArrayList<>();
        for (int i = 0; i < all.length; i++) {
            final String s = String.valueOf((char) (i + 97));
            IntStream.range(0, all[i])
                    //.peek(value -> System.out.print(s + ","))
                    .forEach(value -> list.add(s));
        }
        return list;
    }

    /**
     * O(N^2)
     *
     * @param A
     * @return
     */
    public static List<String> commonCharsV4(String[] A) {
        //记录最终数组
        int[] all = new int[26];

        //记录当前字符串字符出现频率
        int[] cur;

        //O(N)
        //["bella","label","roller"]
        for (int i = 0; i < A.length; i++) {

            //记录当前字符串字符出现频率
            cur = new int[26];

            //b e l l a
            char[] chars = A[i].toCharArray();
            //O(N)
            for (int j = 0; j < chars.length; j++) {
                int c = chars[j] - 97;
                //第一个字符串 初始化最终记录
                if (i == 0) {
                    cur[c]++;
                    continue;
                }


                //如果该字符小于0，则不共享，直接跳过
                if (all[c] == 0) {
                    continue;
                }

                //记录当前字符
                cur[c]++;

            }

            //遍历完当前字符串以后，需要和上一个字符串进行对比
            if (i == 0) {
                //第一个字符串不对比
                all = cur;
                continue;
            }

            for (int j = 0; j < 26; j++) {
                //只对比出现过的字符
                all[j] = Math.min(all[j], cur[j]);
            }
        }

        List<String> list = new ArrayList<>();
        for (int i = 0; i < all.length; i++) {
            final String s = String.valueOf((char) (i + 97));
            IntStream.range(0, all[i])
                    .forEach(value -> list.add(s));
        }
        return list;
    }

    /**
     * O(N^2)
     *
     * @param A
     * @return
     */
    public static List<String> commonCharsV5(String[] A) {
        //记录最终数组
        int[] all = new int[26];

        //记录当前字符串字符出现频率
        int[] cur;

        //O(N)
        //["bella","label","roller"]
        for (int i = 0; i < A.length; i++) {

            //记录当前字符串字符出现频率
            cur = new int[26];

            //b e l l a
            char[] chars = A[i].toCharArray();
            //O(N)
            for (int j = 0; j < chars.length; j++) {
                int c = chars[j] - 97;
                //第一个字符串 初始化最终记录
                if (i == 0) {
                    cur[c]++;
                    continue;
                }


                //如果该字符==0，则不共享，直接跳过
                if (all[c] == 0) {
                    continue;
                }

                //记录当前字符
                cur[c]++;

            }

            //遍历完当前字符串以后，需要和上一个字符串进行对比
            if (i == 0) {
                //第一个字符串不对比
                all = cur;
                continue;
            }

            for (int j = 0; j < 26; j++) {
                //只对比出现过的字符
                all[j] = Math.min(all[j], cur[j]);
            }
        }

        List<String> list = new ArrayList<>();
        for (int i = 0; i < all.length; i++) {
            for (int j = 0; j < all[i]; j++) {
                list.add(String.valueOf((char) (i + 97)));
                //System.out.print((char) (i + 97) + ",");
            }
        }
        return list;
    }

    /**
     * O(N^2)
     *
     * @param A
     * @return
     */
    public static List<String> commonCharsV6(String[] A) {
        //记录最终数组
        int[] all = new int[26];

        //记录当前字符串字符出现频率
        int[] cur;

        //O(N)
        //["bella","label","roller"]
        for (int i = 0; i < A.length; i++) {

            //记录当前字符串字符出现频率
            cur = new int[26];

            //b e l l a
            char[] chars = A[i].toCharArray();
            //O(N)
            for (int j = 0; j < chars.length; j++) {
                //字母转化为下角标
                int c = chars[j] - 97;
                //第一个字符串 初始化最终记录
                if (i == 0) {
                    cur[c]++;
                    continue;
                }


                //如果该字符==0，则不共享，直接跳过
                if (all[c] == 0) {
                    continue;
                }

                //记录当前字符
                cur[c]++;

            }

            //遍历完当前字符串以后，需要和上一个字符串进行对比
            if (i == 0) {
                //第一个字符串不对比
                all = cur;
                continue;
            }

            for (int j = 0; j < 26; j++) {
                //只对比出现过的字符
                if (all[j] > 0) {
                    all[j] = cur[j] == 0 ? 0 : Math.min(all[j], cur[j]);
                }
            }
        }

        List<String> list = new ArrayList<>();
        for (int i = 0; i < all.length; i++) {
            for (int j = 0; j < all[i]; j++) {
                //下角标转化为字母
                list.add(String.valueOf((char) (i + 97)));
            }
        }
        return list;
    }

    public static void main(String[] args) {
        //commonChars(new String[]{"bella", "label", "roller"});
        commonCharsV5(new String[]{"cool", "lock", "cook"});
    }

}
