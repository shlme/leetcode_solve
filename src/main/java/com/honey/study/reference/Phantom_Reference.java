package com.honey.study.reference;

import com.honey.leetcode.model.ListNode;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.LinkedList;
import java.util.List;

/**
 * 虚引用：永远get不到
 *
 * 只有一个作用，用于管理堆外内存
 *
 * @author hualin.su
 * @date 2020-07-04 18:54
 */
public class Phantom_Reference {

    private static final List<Object> LIST = new LinkedList<>();
    private static final ReferenceQueue<Object> QUEUE = new ReferenceQueue<>();

    public static void main(String[] args) {
        PhantomReference<ListNode> phantomReference = new PhantomReference<>(new ListNode(1), QUEUE);

        new Thread(() -> {
            while (true) {
                LIST.add(new byte[1024 * 1024]);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
                // 永远get不到
                System.out.println(phantomReference.get());
            }
        }).start();

        new Thread(()->{
            while (true) {
                Reference<?> poll = QUEUE.poll();
                if (poll == null) {
                    System.out.println("虚引用对象被jvm回收了" + poll);
                }
            }
        }).start();

    }
}
