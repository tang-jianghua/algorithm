package main.java.algorithm.zcy.class09;

import java.util.HashSet;

/**
 * 贪心算法的解题套路实战
 * 给定一个字符串Str,只由x和.两种字符构成。 X表示墙不能放灯也不需要点亮
 * .表示居民点可以放灯需要点亮
 * 如果灯放在i位置可以让i-1,i和i+1三个位置被点亮
 * 返回如果点亮Str中所有需要点亮的位置至少需要几盏灯
 */
public class Code02_Light {

    public static int light(String str){
        if(str==null ||str.isEmpty()) return 0;
        char[] chars = str.toCharArray();
        int count=0;
        for (int i = 0; i < chars.length; i++) {
            //X跳过
            if(chars[i]=='X'){
                continue;
            }
            //遇到. 先把灯++
            count++;
            if(i==chars.length-1){
                break;
            }
            //如果下一个是X 跳过1个
            if(chars[i+1]=='X'){
                i++;
            }else{
                //否则 跳过2个
                i+=2;
            }
        }
        return count;
    }


    // for test
    public static String randomString(int len) {
        char[] res = new char[(int) (Math.random() * len) + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = Math.random() < 0.5 ? 'X' : '.';
        }
        return String.valueOf(res);
    }

    public static int minLight1(String road) {
        if (road == null || road.length() == 0) {
            return 0;
        }
        return process(road.toCharArray(), 0, new HashSet<>());
    }

    // str[index....]位置，自由选择放灯还是不放灯
    // str[0..index-1]位置呢？已经做完决定了，那些放了灯的位置，存在lights里
    // 要求选出能照亮所有.的方案，并且在这些有效的方案中，返回最少需要几个灯
    public static int process(char[] str, int index, HashSet<Integer> lights) {
        if (index == str.length) { // 结束的时候
            for (int i = 0; i < str.length; i++) {
                if (str[i] != 'X') { // 当前位置是点的话
                    if (!lights.contains(i - 1)
                            && !lights.contains(i)
                            && !lights.contains(i + 1)) {
                        return Integer.MAX_VALUE;
                    }
                }
            }
            return lights.size();
        } else { // str还没结束
            // i   X  .
            int no = process(str, index + 1, lights);
            int yes = Integer.MAX_VALUE;
            if (str[index] == '.') {
                lights.add(index);
                yes = process(str, index + 1, lights);
                lights.remove(index);
            }
            return Math.min(no, yes);
        }
    }
    public static void main(String[] args) {
        int len = 20;
        int testTime = 100000;
        for (int i = 0; i < testTime; i++) {
            String test = randomString(len);
            int ans1 = minLight1(test);
            int ans2 = light(test);
            if (ans1 != ans2) {
                System.out.println("oops!");
            }
        }
        System.out.println("finish!");
    }

}
