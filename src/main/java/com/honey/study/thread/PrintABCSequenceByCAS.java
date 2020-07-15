package com.honey.study.thread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 多线程分别顺序打印ABC,用AtomicInteger实现
 *
 * @author hualin.su
 * @date 2020-07-15 10:37
 */
public class PrintABCSequenceByCAS extends Thread {

    /**
     * 当前打印的字母个数
     */
    private static AtomicInteger currentCount = new AtomicInteger(0);

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

    public PrintABCSequenceByCAS(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(THREAD_NUM, THREAD_NUM, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<>(), new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < THREAD_NUM; i++) {
            threadPoolExecutor.submit(new PrintABCSequenceByCAS(String.valueOf((char) (CHAR + i))));
        }
        threadPoolExecutor.shutdown();
    }

    @Override
    public void run() {
        while (currentCount.get() < THREAD_NUM * ROUND_TIMES) {
            if (name.equals(String.valueOf((char) (CHAR + currentCount.get() % THREAD_NUM)))) {
                System.out.println("thread-name: " + name + ", char: " + (char) (CHAR + currentCount.get() % THREAD_NUM));
                currentCount.getAndIncrement();
            }
        }
    }
}
