package main.java.algorithm.zcy.class09;

import java.util.*;

/**
 * 贪心算法 最小字典序
 * 给出一个字符串数组，要求对字符串进行拼接，得到字典序最小的组合
 * 贪心：对字符串进行排序，要求两两组合最小，使用小根堆进行排序
 *
 * @auth tangjianghua
 * @date 2020/7/23
 */
public class Code01_LowestLexicography {

    /**
     * 小根堆解法
     * @param strings
     * @return
     */
    public static String lowestLexicography1(String[] strings) {

        if(strings==null||strings.length==0){
            return "";
        }
        if(strings.length<2){
            return strings[0];
        }
        //先使用小根堆进行排序
       final PriorityQueue<String> queue = new PriorityQueue<>((o1, o2) -> (o1 + o2).compareTo(o2 + o1));
        Arrays.stream(strings).forEach(queue::offer);

        String s="";
        //然后依次拼接即可
        while (!queue.isEmpty()){
            s +=queue.poll();
        }
        return s;
    }



    // for test
    public static String generateRandomString(int strLen) {
        char[] ans = new char[(int) (Math.random() * strLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            int value = (int) (Math.random() * 5);
            ans[i] = (Math.random() <= 0.5) ? (char) (65 + value) : (char) (97 + value);
        }
        return String.valueOf(ans);
    }
    // for test
    public static String[] generateRandomStringArray(int arrLen, int strLen) {
        String[] ans = new String[(int) (Math.random() * arrLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = generateRandomString(strLen);
        }
        return ans;
    }

    // for test
    public static String[] copyStringArray(String[] arr) {
        String[] ans = new String[arr.length];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = String.valueOf(arr[i]);
        }
        return ans;
    }

    public static String lowestString1(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        ArrayList<String> all = new ArrayList<>();
        HashSet<Integer> use = new HashSet<>();
        process(strs, use, "", all);
        String lowest = all.get(0);
        for (int i = 1; i < all.size(); i++) {
            if (all.get(i).compareTo(lowest) < 0) {
                lowest = all.get(i);
            }
        }
        return lowest;
    }

    // strs里放着所有的字符串
    // 已经使用过的字符串的下标，在use里登记了，不要再使用了
    // 之前使用过的字符串，拼接成了-> path
    // 用all收集所有可能的拼接结果
    public static void process(String[] strs, HashSet<Integer> use, String path, ArrayList<String> all) {
        if (use.size() == strs.length) {
            all.add(path);
        } else {
            for (int i = 0; i < strs.length; i++) {
                if (!use.contains(i)) {
                    use.add(i);
                    process(strs, use, path + strs[i], all);
                    use.remove(i);
                }
            }
        }
    }
    public static void main(String[] args) {
        int arrLen = 6;
        int strLen = 5;
        int testTimes = 100000;
        String[] arr = generateRandomStringArray(arrLen, strLen);
        System.out.println("先打印一个生成的字符串");
        for (String str : arr) {
            System.out.print(str + ",");
        }
        System.out.println();
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String[] arr1 = generateRandomStringArray(arrLen, strLen);
            String[] arr2 = copyStringArray(arr1);
            if (!lowestLexicography1(arr1).equals(lowestString1(arr2))) {
                for (String str : arr1) {
                    System.out.print(str + ",");
                }
                System.out.println();
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }


}
