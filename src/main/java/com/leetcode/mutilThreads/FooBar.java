package com.leetcode.mutilThreads;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

class FooBar {
    private int n;
    private CyclicBarrier cyclicBarrier;

    public FooBar(int n) {
        this.n = n;
        cyclicBarrier = new CyclicBarrier(2);
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            try {
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                cyclicBarrier.await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            cyclicBarrier.reset();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            try {
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                cyclicBarrier.await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
