package com.honey.study.reference;

import com.honey.leetcode.model.ListNode;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author hualin.su
 * @date 2020-07-04 23:04
 */
public class ThreadLocalDemo {

    /**
     * threadLocal是本线程可见的变量，非线程贡献
     * 弱引用
     */
    static ThreadLocal<ListNode> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 3, 1, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

        threadPoolExecutor.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // threadLocal的key是弱引用
            threadLocal.set(new ListNode(1));
            System.out.println(Thread.currentThread().getName() + threadLocal.get());
            threadLocal.remove();
        });

        threadPoolExecutor.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 读不到上边线程给threadLocal中设置的值
            System.out.println(Thread.currentThread().getName() + threadLocal.get());
        });
    }
}
