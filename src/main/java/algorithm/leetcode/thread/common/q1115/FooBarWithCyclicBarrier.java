package main.java.algorithm.leetcode.thread.common.q1115;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * @author tangjianghua
 * @date 2020/11/25
 */
public class FooBarWithCyclicBarrier {


    private CyclicBarrier cyclicBarrier1 = new CyclicBarrier(2, () -> {
    });
    private CyclicBarrier cyclicBarrier2 = new CyclicBarrier(2, () -> {
    });

    private int n;

    public FooBarWithCyclicBarrier(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException, BrokenBarrierException {
        for (int i = 0; i < n; i++) {
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            cyclicBarrier1.await();
            cyclicBarrier2.await();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException, BrokenBarrierException {
        for (int i = 0; i < n; i++) {
            cyclicBarrier1.await();
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();

            cyclicBarrier2.await();
        }
    }

    public static void main(String[] args) {

        FooBarWithCyclicBarrier fooBar = new FooBarWithCyclicBarrier(100);
        new Thread(() -> {
            try {
                fooBar.foo(() -> System.out.print("foo"));
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                fooBar.bar(() -> System.out.print("bar"));
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();

    }
}
