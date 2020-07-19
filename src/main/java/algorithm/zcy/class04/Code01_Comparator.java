package main.java.algorithm.zcy.class04;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @auth tangjianghua
 * @date 2020/7/19
 */
public class Code01_Comparator {

    public static class Mycom implements Comparator<Student> {

        @Override
        public int compare(Student o1, Student o2) {
            return o1.id-o2.id;
        }
    }
    public static class AgeCom implements Comparator<Student> {

        @Override
        public int compare(Student o1, Student o2) {
            return o1.age-o2.age;
        }
    }

    public static class Student{
        private int age;

        private int id;

        public Student(int age, int id) {
            this.age = age;
            this.id = id;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "age=" + age +
                    ", id=" + id +
                    '}';
        }
    }

    public static void main(String[] args) {
        PriorityQueue<Student> heap = new PriorityQueue<>(new Mycom());
        //O(N*logN)
        heap.add(new Student(5,1));
        heap.add(new Student(4,2));
        heap.add(new Student(3,3));

        //O(N*logN)
        while (!heap.isEmpty()){
            System.out.println(heap.poll());
        }

        PriorityQueue<Student> heap2 = new PriorityQueue<>(new AgeCom());
        heap2.add(new Student(5,1));
        heap2.add(new Student(4,2));
        heap2.add(new Student(3,3));

        while (!heap2.isEmpty()){
            System.out.println(heap2.poll());
        }

    }
}
