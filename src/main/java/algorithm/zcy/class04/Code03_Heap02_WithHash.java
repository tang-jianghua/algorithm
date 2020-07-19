package main.java.algorithm.zcy.class04;

import java.util.*;

/**
 * @auth tangjianghua
 * @date 2020/7/19
 */
public class Code03_Heap02_WithHash {

    public static class MyHeap<T> {
        private List<T> heap;
        private int heapSize;
        private Comparator comparator;
        private Map<T, Integer> indexMap;

        public MyHeap(Comparator comparator) {
            this.comparator = comparator;
        }

        public void push(T t) {
            if (heap == null) {
                heap = new ArrayList<>();
                indexMap = new HashMap<>();
                heapSize = 0;
            }
            heap.add(t);
            indexMap.put(t, heapSize);
            heapInsert(heap, heapSize++);
        }

        public T pop() {
            if (isEmpty()) {
                throw new RuntimeException("heap is empty");
            }
            T t = heap.get(0);
            swap(0, --heapSize);
            heapify(heap, 0, heapSize);
            return t;
        }

        public void resign(T t){
            Integer index = indexMap.get(t);
            heapInsert(heap,index);
            heapify(heap,index,heapSize);
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        private void heapInsert(List heap, int index) {
            while (comparator.compare(heap.get(index), heap.get((index - 1) / 2)) > 0) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        private void heapify(List heap, int index, int heapSize) {

            while (index * 2 + 1 < heapSize) {
                int largest = index * 2 + 1 < heapSize && comparator.compare(heap.get(index * 2 + 2), heap.get(index * 2 + 1)) > 0 ? index * 2 + 2 : index * 2 + 1;
                if (comparator.compare(heap.get(index), heap.get(largest)) > 0) {
                    swap(index, largest);
                }
            }
        }

        private void swap(int index, int target) {
            T t = heap.get(index);
            T t1 = heap.get(target);
            heap.set(index, t1);
            indexMap.put(t1, index);
            heap.set(target, t);
            indexMap.put(t, target);
        }
    }

    public static class Student {
        public int classNo;
        public int age;
        public int id;

        public Student(int c, int a, int i) {
            classNo = c;
            age = a;
            id = i;
        }

    }

    public static class StudentComparator implements Comparator<Student> {

        @Override
        public int compare(Student o1, Student o2) {
            return o1.age - o2.age;
        }

    }

    public static void main(String[] args) {
        Student s1 = null;
        Student s2 = null;
        Student s3 = null;
        Student s4 = null;
        Student s5 = null;
        Student s6 = null;

        s1 = new Student(2, 50, 11111);
        s2 = new Student(1, 60, 22222);
        s3 = new Student(6, 10, 33333);
        s4 = new Student(3, 20, 44444);
        s5 = new Student(7, 72, 55555);
        s6 = new Student(1, 14, 66666);

        PriorityQueue<Student> heap = new PriorityQueue<>(new StudentComparator());
        heap.add(s1);
        heap.add(s2);
        heap.add(s3);
        heap.add(s4);
        heap.add(s5);
        heap.add(s6);
        while (!heap.isEmpty()) {
            Student cur = heap.poll();
            System.out.println(cur.classNo + "," + cur.age + "," + cur.id);
        }

        System.out.println("===============");

        MyHeap<Student> myHeap = new MyHeap<>(new StudentComparator());
        myHeap.push(s1);
        myHeap.push(s2);
        myHeap.push(s3);
        myHeap.push(s4);
        myHeap.push(s5);
        myHeap.push(s6);
        while (!myHeap.isEmpty()) {
            Student cur = myHeap.pop();
            System.out.println(cur.classNo + "," + cur.age + "," + cur.id);
        }

        System.out.println("===============");

        s1 = new Student(2, 50, 11111);
        s2 = new Student(1, 60, 22222);
        s3 = new Student(6, 10, 33333);
        s4 = new Student(3, 20, 44444);
        s5 = new Student(7, 72, 55555);
        s6 = new Student(1, 14, 66666);

        heap = new PriorityQueue<>(new StudentComparator());

        heap.add(s1);
        heap.add(s2);
        heap.add(s3);
        heap.add(s4);
        heap.add(s5);
        heap.add(s6);

        s2.age = 6;
        s4.age = 12;
        s5.age = 10;
        s6.age = 84;

        while (!heap.isEmpty()) {
            Student cur = heap.poll();
            System.out.println(cur.classNo + "," + cur.age + "," + cur.id);
        }

        System.out.println("===============");

        s1 = new Student(2, 50, 11111);
        s2 = new Student(1, 60, 22222);
        s3 = new Student(6, 10, 33333);
        s4 = new Student(3, 20, 44444);
        s5 = new Student(7, 72, 55555);
        s6 = new Student(1, 14, 66666);

        myHeap = new MyHeap<>(new StudentComparator());

        myHeap.push(s1);
        myHeap.push(s2);
        myHeap.push(s3);
        myHeap.push(s4);
        myHeap.push(s5);
        myHeap.push(s6);

        s2.age = 6;
        myHeap.resign(s2);
        s4.age = 12;
        myHeap.resign(s4);
        s5.age = 10;
        myHeap.resign(s5);
        s6.age = 84;
        myHeap.resign(s6);

        while (!myHeap.isEmpty()) {
            Student cur = myHeap.pop();
            System.out.println(cur.classNo + "," + cur.age + "," + cur.id);
        }


        // 对数器
        System.out.println("test begin");
        int maxValue = 100000;
        int pushTime = 1000000;
        int resignTime = 100;
        MyHeap<Student> test = new MyHeap<>(new StudentComparator());
        ArrayList<Student> list = new ArrayList<>();
        for (int i = 0; i < pushTime; i++) {
            Student cur = new Student(1, (int) (Math.random() * maxValue), 1000);
            list.add(cur);
            test.push(cur);
        }
        for (int i = 0; i < resignTime; i++) {
            int index = (int) (Math.random() * pushTime);
            list.get(index).age = (int) (Math.random() * maxValue);
            test.resign(list.get(index));
        }
        int preAge = Integer.MIN_VALUE;
        while (test.isEmpty()) {
            Student cur = test.pop();
            if (cur.age < preAge) {
                System.out.println("Oops!");
            }
            preAge = cur.age;
        }
        System.out.println("test finish");


    }
}