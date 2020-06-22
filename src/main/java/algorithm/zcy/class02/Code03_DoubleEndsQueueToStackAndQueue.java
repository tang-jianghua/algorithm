package main.java.algorithm.zcy.class02;

import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 利用双向链表构建栈和队列
 *
 * @author tangjianghua
 * date 2020/6/22
 * time 11:36
 */
public class Code03_DoubleEndsQueueToStackAndQueue {

    class DoubleNode<T> {
        private T value;

        private DoubleNode pre;

        private DoubleNode next;

        public DoubleNode(T value) {
            this.value = value;
        }
    }


    public class DoubleEndsQueue<T> {

        DoubleNode<T> head;

        DoubleNode<T> tail;

        private AtomicInteger lenth = new AtomicInteger(0);

        /**
         * 从头部添加
         *
         * @param value
         */
        public void addFromHead(T value) {
            final DoubleNode<T> tDoubleNode = new DoubleNode<>(value);
            if (head != null) {
                head.pre = tDoubleNode;
                tDoubleNode.next = head;
            }
            head = tDoubleNode;
            lenth.incrementAndGet();
        }

        /**
         * 从尾部添加
         *
         * @param value
         */
        public void addFromTail(T value) {
            final DoubleNode<T> tDoubleNode = new DoubleNode<>(value);
            if (tail != null) {
                tail.next = tDoubleNode;
                tDoubleNode.pre = tail;
            }
            tail = tDoubleNode;
            lenth.incrementAndGet();
        }

        /**
         * 从头部弹出一个节点值
         *
         * @return
         */
        public T popFromHead() {
            if (head == null) {
                return null;
            }
            DoubleNode<T> cur;
            cur = this.head;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                cur.next = null;
                head = head.next;
                head.pre = null;
            }
            lenth.decrementAndGet();
            return cur.value;
        }

        /**
         * 从尾部弹出一个节点值
         *
         * @return
         */
        public T popFromTail() {
            if (tail == null) {
                return null;
            }
            DoubleNode<T> cur;
            cur = this.tail;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                cur.pre = null;
                tail = tail.pre;
                tail.next = null;
            }
            lenth.decrementAndGet();
            return cur.value;
        }

        public int length(){
            return lenth.get();
        }

        public boolean isEmpty(){
            return lenth.get()<=0;
        }
    }

    public class Mystack<T>{

        private DoubleEndsQueue<T> tDoubleEndsQueue = new DoubleEndsQueue<>();

        public void add(T t){
            tDoubleEndsQueue.addFromHead(t);
        }

        public T pop(){
            return tDoubleEndsQueue.popFromTail();
        }

        public T peek(){
            return tDoubleEndsQueue.isEmpty()?null:tDoubleEndsQueue.tail.value;
        }

        public synchronized int search(T t) {
            if(tDoubleEndsQueue.isEmpty()){
                return -1;
            }
            int i = 1;
            DoubleNode<T> cur = tDoubleEndsQueue.tail;
            while (cur!=null){
                if(cur.value==t){
                    break;
                }else{
                    cur=cur.pre;
                    i++;
                }
            }
            return i;
        }
    }
}
