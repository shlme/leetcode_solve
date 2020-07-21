package com.honey.study.thread.printabc;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 多线程分别顺序打印ABC,用 synchronized + wait() + notifyAll() 实现
 *
 * @author hualin.su
 * @date 2020-07-15 11:41
 */
public class PrintABCSequenceBySynchronized extends Thread {

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


    private String name;

    public PrintABCSequenceBySynchronized(String name) {
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
        while (currentCount < THREAD_NUM * ROUND_TIMES) {
            synchronized (this) {
                if (currentCount < THREAD_NUM * ROUND_TIMES) {
                    if (name.equals(String.valueOf((char) (CHAR + currentCount % THREAD_NUM)))) {
                        System.out.println("thread-name: " + name + ", char: " + (char) (CHAR + currentCount % THREAD_NUM));
                        currentCount++;
                        notifyAll();
                    } else {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
