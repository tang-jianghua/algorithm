package main.java.algorithm.leetcode.thread.common.q1115;


import java.util.concurrent.CountDownLatch;

class FooBarWithCountDownLatch {

    private CountDownLatch countDownLatch = new CountDownLatch(1);
    private CountDownLatch countDownLatch2 = new CountDownLatch(1);

    private int n;

    public FooBarWithCountDownLatch(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            countDownLatch.countDown();
            countDownLatch2.await();
            countDownLatch2 = new CountDownLatch(1);
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            countDownLatch.await();
            countDownLatch = new CountDownLatch(1);
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();

            countDownLatch2.countDown();
        }
    }

    public static void main(String[] args) {

        FooBarWithCountDownLatch fooBar = new FooBarWithCountDownLatch(3);
        new Thread(() -> {
            try {
                fooBar.foo(() -> System.out.print("foo"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                fooBar.bar(() -> System.out.print("bar"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }
}