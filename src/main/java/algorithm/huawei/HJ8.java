package main.java.algorithm.huawei;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 题目描述
 * 数据表记录包含表索引和数值（int范围的整数），请对表索引相同的记录进行合并，即将相同索引的数值进行求和运算，输出按照key值升序进行输出。
 * <p>
 * 输入描述:
 * 先输入键值对的个数
 * 然后输入成对的index和value值，以空格隔开
 * <p>
 * 输出描述:
 * 输出合并后的键值对（多行）
 *
 * @author tangjianghua
 * @date 2020/11/19
 */
public class HJ8 {

    public static void main1(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s;
        int size = 0;
        SortedMap<Integer, Integer> sortedMap = null;
        while ((s = bufferedReader.readLine()) != null) {
            if (size == 0) {
                size = Integer.parseInt(s);
                sortedMap = new TreeMap<>();
                continue;
            }
            String[] split = s.split(" ");
            Integer key = Integer.parseInt(split[0]);
            Integer value = Integer.parseInt(split[1]);
            if (sortedMap.containsKey(key)) {
                sortedMap.put(key, sortedMap.get(key) + value);
            } else {
                sortedMap.put(key, value);
            }
            if (--size == 0) {
                Iterator<Map.Entry<Integer, Integer>> iterator = sortedMap.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<Integer, Integer> next = iterator.next();
                    System.out.println(next.getKey() + " " + next.getValue());
                }
            }
        }
    }

    public static void main2(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s;
        int size = 0;
        int[] ints = new int[1024];
        while ((s = bufferedReader.readLine()) != null) {
            if (size == 0) {
                size = Integer.parseInt(s);
                continue;
            }
            String[] split = s.split(" ");
            Integer key = Integer.parseInt(split[0]);
            Integer value = Integer.parseInt(split[1]);
            ints[key] += value;
            if (--size == 0) {
                for (int i = 0; i < ints.length; i++) {
                    if (ints[i] > 0) {
                        System.out.println(i + " " + ints[i]);
                    }
                }

            }
        }
    }

    public static void main3(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s;
        int size = 0;
        Map<Integer, Integer> map = null;
        while ((s = bufferedReader.readLine()) != null) {
            if (size == 0) {
                size = Integer.parseInt(s);
                map = new HashMap<>(size);
                continue;
            }
            String[] split = s.split(" ");
            Integer key = Integer.parseInt(split[0]);
            Integer value = Integer.parseInt(split[1]);
            if (map.containsKey(key)) {
                map.put(key, map.get(key) + value);
            } else {
                map.put(key, value);
            }
            if (--size == 0) {

                PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(map.size());
                priorityQueue.addAll(map.keySet());
                while (!priorityQueue.isEmpty()) {
                    Integer poll = priorityQueue.poll();
                    System.out.println(poll + " " + map.get(poll));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s;
        int size = 0;
        Map<Integer, Integer> map = null;
        while ((s = bufferedReader.readLine()) != null) {
            if (size == 0) {
                size = Integer.parseInt(s);
                map = new HashMap<>(size);
                continue;
            }
            String[] split = s.split(" ");
            Integer key = Integer.parseInt(split[0]);
            Integer value = Integer.parseInt(split[1]);
            if (map.containsKey(key)) {
                map.put(key, map.get(key) + value);
            } else {
                map.put(key, value);
            }
            if (--size == 0) {

                PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(map.size());
                priorityQueue.addAll(map.keySet());
                while (!priorityQueue.isEmpty()) {
                    Integer poll = priorityQueue.poll();
                    System.out.println(poll + " " + map.get(poll));
                }
            }
        }
    }
}
