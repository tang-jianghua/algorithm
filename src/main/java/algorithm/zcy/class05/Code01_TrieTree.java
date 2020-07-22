package main.java.algorithm.zcy.class05;

import java.util.HashMap;

/**
 * 前缀树
 * 设计一种数据结构，支持以下功能：
 * 1.字符串的添加
 * 2.字符串的删除
 * 3.字符串是否添加过
 * 4.字符串添加过几次
 * 5.以某个字符串开头为前缀的字符串添加过几次
 *
 * @auth tangjianghua
 * @date 2020/7/21
 */
public class Code01_TrieTree {

    /**
     * 节点结构
     */
    static class Node {

        /**
         * 通往下个节点的路
         */
        private Node[] routes = new Node[26];

        /**
         * 经过该节点几次
         */
        int pass;

        /**
         * 以该节点结尾的次数
         */
        int end;

        public Node() {
        }

        public String print() {
            String s = "";
            for (int i = 0; i < routes.length; i++) {
                if (routes[i] != null) {
                    s += (char) (i + 'a') + "(" + routes[i].pass + "," + routes[i].end + "){" + routes[i].print() + "}";
                }
            }
            return s;
        }
    }


    public static class Trie1 {

        private Node head = new Node();

        /**
         * 添加一个字符串
         *
         * @param word
         */
        public void insert(String word) {
            if (word == null || word.isEmpty()) {
                return;
            }
            Node cur = head;
            cur.pass++;
            int routeIndex;
            char[] chars = word.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                routeIndex = chars[i] - 'a';
                if (cur.routes[routeIndex] == null) {
                    cur.routes[routeIndex] = new Node();
                }
                cur = cur.routes[routeIndex];
                cur.pass++;
            }
            cur.end++;
            //System.out.println(head.print());
        }

        /**
         * 查找某个字符串添加过几次
         *
         * @param word
         * @return
         */
        public int search(String word) {
            if (word == null || word.isEmpty() || head.pass == 0) {
                return 0;
            }
            Node cur = head;
            int routeIndex;
            char[] chars = word.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                routeIndex = chars[i] - 'a';
                if (cur.routes[routeIndex] == null) {
                    return 0;
                }
                cur = cur.routes[routeIndex];
            }
            return cur.end;
        }

        /**
         * 删除一个字符串
         *
         * @param word
         * @return
         */
        public void delete(String word) {
            if (word == null || word.isEmpty()) {
                return;
            }
            if (search(word) == 0) {
                return;
            }
            Node cur = head;
            cur.pass--;
            int routeIndex = 0;
            char[] chars = word.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                routeIndex = chars[i] - 'a';
                if (--cur.routes[routeIndex].pass == 0) {
                    cur.routes[routeIndex] = null;
                    return;
                }
                //todo
                //犯错：  在if (--cur.routes[routeIndex].pass == 0) 之前将cur指向了cur.routes[routeIndex]，导致判断了cur.routes[routeIndex]的子数组
                cur = cur.routes[routeIndex];
            }
            //System.out.println(head.print());
            //todo 注意
            //犯错：这里如果删除了end==0的节点，可能会移除了pass!=0的节点
            cur.end--;
        }

        /**
         * 所有加入的字符串中，有几个是以pre这个字符串作为前缀的
         *
         * @param word
         * @return
         */
        public int prefixNumber(String word) {
            if (word == null || word.isEmpty()) {
                return 0;
            }
            Node cur = head;
            int routeIndex;
            char[] chars = word.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                routeIndex = chars[i] - 'a';
                if (cur.routes[routeIndex] == null) {
                    return 0;
                }
                cur = cur.routes[routeIndex];
            }
            return cur.pass;
        }
    }

    public static class Trie2 {

        private Node2 head = new Node2();

        /**
         * 添加一个字符串
         *
         * @param word
         */
        public void insert(String word) {
            if (word == null || word.isEmpty()) {
                return;
            }
            Node2 cur = head;
            cur.pass++;
            char[] chars = word.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if (!cur.route.containsKey(chars[i])) {
                    cur.route.put(chars[i],new Node2());
                }
                cur = cur.route.get(chars[i]);
                cur.pass++;
            }
            cur.end++;
            //System.out.println(head.print());
        }

        /**
         * 查找某个字符串添加过几次
         *
         * @param word
         * @return
         */
        public int search(String word) {
            if (word == null || word.isEmpty() || head.pass == 0) {
                return 0;
            }
            Node2 cur = head;
            char[] chars = word.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if (!cur.route.containsKey(chars[i])) {
                    return 0;
                }
                cur = cur.route.get(chars[i]);
            }
            return cur.end;
        }

        /**
         * 删除一个字符串
         *
         * @param word
         * @return
         */
        public void delete(String word) {
            if (word == null || word.isEmpty()) {
                return;
            }
            if (search(word) == 0) {
                return;
            }
            Node2 cur = head;
            cur.pass--;
            char[] chars = word.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if (--cur.route.get(chars[i]).pass == 0) {
                    cur.route.remove(chars[i]);
                    return;
                }
                //todo
                //犯错：  在if (--cur.routes[routeIndex].pass == 0) 之前将cur指向了cur.routes[routeIndex]，导致判断了cur.routes[routeIndex]的子数组
                cur = cur.route.get(chars[i]);
            }
            //System.out.println(head.print());
            //todo 注意
            //犯错：这里如果删除了end==0的节点，可能会移除了pass!=0的节点
            cur.end--;
        }

        /**
         * 所有加入的字符串中，有几个是以pre这个字符串作为前缀的
         *
         * @param word
         * @return
         */
        public int prefixNumber(String word) {
            if (word == null || word.isEmpty()) {
                return 0;
            }
            Node2 cur = head;
            char[] chars = word.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if (!cur.route.containsKey(chars[i])) {
                    return 0;
                }
                cur = cur.route.get(chars[i]);
            }
            return cur.pass;
        }
    }

    static class Node2{
        private HashMap<Character,Node2> route=new HashMap<>();
        private int pass;
        private int end;
    }


    public static class Right {

        private HashMap<String, Integer> box;

        public Right() {
            box = new HashMap<>();
        }

        public void insert(String word) {
            if (!box.containsKey(word)) {
                box.put(word, 1);
            } else {
                box.put(word, box.get(word) + 1);
            }
        }

        public void delete(String word) {
            if (box.containsKey(word)) {
                if (box.get(word) == 1) {
                    box.remove(word);
                } else {
                    box.put(word, box.get(word) - 1);
                }
            }
        }

        public int search(String word) {
            if (!box.containsKey(word)) {
                return 0;
            } else {
                return box.get(word);
            }
        }

        public int prefixNumber(String pre) {
            int count = 0;
            for (String cur : box.keySet()) {
                if (cur.startsWith(pre)) {
                    count += box.get(cur);
                }
            }
            return count;
        }
    }

    // for test
    public static String generateRandomString(int strLen) {
        char[] ans = new char[(int) (Math.random() * strLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            int value = (int) (Math.random() * 6);
            ans[i] = (char) (97 + value);
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

    public static void main(String[] args) {
        int arrLen = 100;
        int strLen = 20;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            String[] arr = generateRandomStringArray(arrLen, strLen);
            Trie1 trie1 = new Trie1();
            Trie2 trie2 = new Trie2();
            Right right = new Right();
            for (int j = 0; j < arr.length; j++) {
                double decide = Math.random();
                if (decide < 0.25) {
                    trie1.insert(arr[j]);
                    trie2.insert(arr[j]);
                    right.insert(arr[j]);
                } else if (decide < 0.5) {
                    trie1.delete(arr[j]);
                    trie2.delete(arr[j]);
                    right.delete(arr[j]);
                } else if (decide < 0.75) {
                    int ans1 = trie1.search(arr[j]);
                    int ans2 = trie2.search(arr[j]);
                    int ans3 = right.search(arr[j]);
                    //if (ans1 != ans3) {
                    if (ans1 != ans2 || ans2 != ans3) {
                        System.out.println("Oops!");
                    }
                } else {
                    int ans1 = trie1.prefixNumber(arr[j]);
                    int ans2 = trie2.prefixNumber(arr[j]);
                    int ans3 = right.prefixNumber(arr[j]);
                    //if (ans1 !=  ans3) {
                    if (ans1 != ans2 || ans2 != ans3) {
                        System.out.println("Oops!");
                    }
                }
            }
        }
        System.out.println("finish!");

    }

}
