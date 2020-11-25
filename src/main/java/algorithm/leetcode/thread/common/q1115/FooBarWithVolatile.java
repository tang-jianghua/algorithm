package main.java.algorithm.leetcode.thread.common.q1115;


class FooBarWithVolatile {

    private volatile int lock = 0;

    private int n;

    public FooBarWithVolatile(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {

            // printFoo.run() outputs "foo". Do not change or remove this line.
            while (lock == 1) {

            }
            printFoo.run();
            lock = 1;

        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            while (lock == 0) {

            }
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            lock = 0;
        }
    }

    public static void main(String[] args) {

        FooBarWithVolatile fooBar = new FooBarWithVolatile(5);
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