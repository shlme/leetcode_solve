package com.honey.study.thread.printabc;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程分别顺序打印ABC,用Lock实现
 *
 * @author hualin.su
 * @date 2020-07-15 10:37
 */
public class PrintABCSequenceByLock extends Thread {

    /**
     * 默认为非公平锁
     */
    private static Lock lock = new ReentrantLock();

    /**
     * 当前打印的字母个数
     */
    private static int currentCount = 0;

    /**
     * 线程数
     */
    private static final int THREAD_NUM = 10;

    /**
     * 循环打印次数
     */
    private static final int ROUND_TIMES = 3;

    /**
     * 打印的起始字符
     */
    private static final char CHAR = 'A';

    /**
     * 线程名称
     */
    private String name;

    public PrintABCSequenceByLock(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(THREAD_NUM, THREAD_NUM, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<>(), new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < THREAD_NUM; i++) {
            threadPoolExecutor.submit(new PrintABCSequenceByLock(String.valueOf((char) (CHAR + i))));
        }
        threadPoolExecutor.shutdown();
    }

    @Override
    public void run() {
        while (currentCount < THREAD_NUM * ROUND_TIMES) {
            try {
                lock.lock();
                // 里层判断不能少
                if (currentCount < THREAD_NUM * ROUND_TIMES) {
                    if (name.equals(String.valueOf((char) (CHAR + currentCount % THREAD_NUM)))) {
                        System.out.println("thread-name: " + name + ", char: " + (char) (CHAR + currentCount % THREAD_NUM));
                        currentCount++;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}

