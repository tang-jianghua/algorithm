package main.java.algorithm.leetcode.thread.common.q1115;


import java.util.concurrent.Exchanger;

class FooBarWithExchanger {

    private Exchanger exchanger1 = new Exchanger();
    private Exchanger exchanger2 = new Exchanger();

    private int n;

    public FooBarWithExchanger(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {

            // printFoo.run() outputs "foo". Do not change or remove this line.

            exchanger2.exchange(n);
            printFoo.run();
            exchanger1.exchange(n);

        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            exchanger2.exchange(n);
            exchanger1.exchange(n);
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
        }
    }

    public static void main(String[] args) {

        FooBarWithExchanger fooBar = new FooBarWithExchanger(5);
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