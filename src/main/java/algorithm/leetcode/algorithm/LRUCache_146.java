package main.java.algorithm.leetcode.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 * <p>
 * 获取数据 get(key) - 如果关键字 (key) 存在于缓存中，则获取关键字的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字/值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 * <p>
 *  
 * <p>
 * 进阶:
 * <p>
 * 你是否可以在 O(1) 时间复杂度内完成这两种操作？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lru-cache
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 思路：
 * LRU缓存涉及到增删改查
 *  -查询通过map来实现O(1)复杂度
 *  -增删改通过链表来实现O(1)复杂度
 *  -整个数据结构中会缓存两份一模一样的数据引用，一份缓存数据。
 *      -map中的value和head中的节点是同一个数据，map用来实现查询索引，链表用来修改数据源
 * @author tangjianghua
 * date 2020/6/19
 * time 13:15
 */
public class LRUCache_146 {

    /**
     * 通过map结构来达到O(1)查找节点
     */
    private Map<Object, Node> map;

    /**
     * 记录链表的头节点，代表最久未使用的
     */
    private Node head;

    /**
     * 记录链表的尾节点
     */
    private Node tail;

    /**
     * 指定缓存容量
     */
    private int capacity;

    /**
     * 构造函数首先要支持缓存量的设置
     * @param capacity
     */
    public LRUCache_146(int capacity) {
        map = new HashMap(capacity);
        this.capacity = capacity;
    }

    /**
     * 通过hashmap，以O(1)的速度获取到node，将node移动到队列末尾，返回node的值。
     * 如果node不存在，返回-1.
     *
     * @param key
     * @return
     */
    public Object get(Object key) {
        Node node = map.get(key);
        if (node == null) {
            return -1;
        } else if (node == head) {
            head = head.next;
            head.pre = null;
            swapTail(node);
        } else {
            final Node pre = node.pre;
            final Node next = node.next;
            pre.next = next;
            next.pre = pre;
            swapTail(node);
        }
        return tail.getValue();
    }

    /**
     * @param key
     * @param value
     */
    public void put(Object key, Object value) {
        Node node = map.get(key);
        if (node != null) {
            //存在则变更
            node.setValue(value);
        } else {
            //不存在需要新增，新增需要考虑LRU
            node = new Node();
            node.setKey(key);
            node.setValue(value);
            if (head == null && tail == null) {
                map.put(key, node);
                head = tail = node;
                return;
            }
            //如果缓存满了，移除最久未使用的节点，即头节点
            if (map.size() == capacity) {
                map.remove(head.getKey());
                head = head.next;
                head.pre = null;
                swapTail(node);
                map.put(key, node);
                return;
            }
            swapTail(node);
            map.put(key, node);
        }
    }

    private void swapTail(Node node) {
        if (tail == null) {
            tail = node;
        } else {
            tail.next = node;
            node.pre = tail;
            node.next = null;
            tail = node;
        }
    }

    class Node {
        private Object key;

        private Object value;

        private Node pre;

        private Node next;

        public Object getKey() {
            return key;
        }

        public void setKey(Object key) {
            this.key = key;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
      /*  示例
      LRUCache cache = new LRUCache( 2 *//* 缓存容量 *//* );

        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // 返回  1
        cache.put(3, 3);    // 该操作会使得关键字 2 作废
        cache.get(2);       // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得关键字 1 作废
        cache.get(1);       // 返回 -1 (未找到)
        cache.get(3);       // 返回  3
        cache.get(4);       // 返回  4

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/lru-cache
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。*/

        final LRUCache_146 cache = new LRUCache_146(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1)); // 返回  1
        cache.put(3, 3);    // 该操作会使得关键字 2 作废
        System.out.println(cache.get(2));// 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得关键字 1 作废
        System.out.println(cache.get(1));// 返回 -1 (未找到)
        System.out.println(cache.get(3)); // 返回  3
        System.out.println(cache.get(4)); // 返回  4
    }
}
