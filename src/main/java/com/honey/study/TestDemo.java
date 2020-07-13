package com.honey.study;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author hualin.su
 * @date 2020-07-05 13:17
 */
public class TestDemo {

    private static final ReentrantLock LOCK = new ReentrantLock();
    // volatile只能保证可见性，不能保证原子性
    private static volatile int m = 0;


    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[100];
        for (int i = 0; i < 100; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    try {
                        LOCK.lock();
                        m++;
                    } finally {
                        LOCK.unlock();
                    }
                }
            });
        }
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println(m);
    }
}
