package main.java.algorithm.leetcode.thread.common.q1115;


import java.util.concurrent.locks.LockSupport;

class FooBarWithLockSupport {

    private Thread thread1;
    private Thread thread2;


    private int n;

    public FooBarWithLockSupport(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        thread1 = Thread.currentThread();
        for (int i = 0; i < n; i++) {

            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            while (thread2 == null) {

            }
            LockSupport.unpark(thread2);
            LockSupport.park();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        thread2 = Thread.currentThread();
        for (int i = 0; i < n; i++) {
            LockSupport.park();
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            LockSupport.unpark(thread1);
        }
    }

    public static void main(String[] args) {

        FooBarWithLockSupport fooBar = new FooBarWithLockSupport(10);
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