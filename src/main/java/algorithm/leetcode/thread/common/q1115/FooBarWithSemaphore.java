package main.java.algorithm.leetcode.thread.common.q1115;

import java.util.concurrent.Semaphore;

class FooBarWithSemaphore {

    private Semaphore semaphore = new Semaphore(0);
    private Semaphore semaphore2 = new Semaphore(0);

    private int n;

    public FooBarWithSemaphore(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            // printFoo.run() outputs "foo". Do not change or remove this line.

            printFoo.run();
            semaphore.release();
            semaphore2.acquire();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            semaphore.acquire();
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            semaphore2.release();
        }
    }

    public static void main(String[] args) {

        FooBarWithSemaphore fooBar = new FooBarWithSemaphore(5);
        Thread foo = new Thread(() -> {
            try {
                fooBar.foo(() -> System.out.print("foo"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread bar = new Thread(() -> {
            try {
                fooBar.bar(() -> System.out.print("bar"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        foo.start();
        bar.start();

    }
}