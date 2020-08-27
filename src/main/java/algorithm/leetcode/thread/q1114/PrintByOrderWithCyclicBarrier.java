package main.java.algorithm.leetcode.thread.q1114;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * 我们提供了一个类：
 * <p>
 * public class Foo {
 *   public void one() { print("one"); }
 *   public void two() { print("two"); }
 *   public void three() { print("three"); }
 * }
 * 三个不同的线程将会共用一个 Foo 实例。
 * <p>
 * 线程 A 将会调用 one() 方法
 * 线程 B 将会调用 two() 方法
 * 线程 C 将会调用 three() 方法
 * 请设计修改程序，以确保 two() 方法在 one() 方法之后被执行，three() 方法在 two() 方法之后被执行。
 * <p>
 *  
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/print-in-order
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @auth tangjianghua
 * @date 2020/7/28
 */
public class PrintByOrderWithCyclicBarrier {


    CyclicBarrier cyclicBarrier = new CyclicBarrier(2, () -> {
    });
    CyclicBarrier cyclicBarrier2 = new CyclicBarrier(2, () -> {
    });

    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        try {
            cyclicBarrier.await();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

    }

    public void second(Runnable printSecond) throws InterruptedException {

        // printSecond.run() outputs "second". Do not change or remove this line.
        try {
            cyclicBarrier.await();
            printSecond.run();
            cyclicBarrier2.await();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public void third(Runnable printThird) throws InterruptedException {

        // printThird.run() outputs "third". Do not change or remove this line.
        try {
            cyclicBarrier2.await();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        printThird.run();
    }


    public static void main(String[] args) throws InterruptedException {
        PrintByOrderWithCyclicBarrier printByOrderWithVolatile = new PrintByOrderWithCyclicBarrier();

        new Thread(() -> {
            try {
                printByOrderWithVolatile.third(() -> System.out.println("third"));
            } catch (InterruptedException  e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                printByOrderWithVolatile.first(() -> System.out.println("first"));
            } catch (InterruptedException  e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                printByOrderWithVolatile.second(() -> System.out.println("second"));
            } catch (InterruptedException  e) {
                e.printStackTrace();
            }
        }).start();

    }

}
