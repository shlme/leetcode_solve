package com.honey.study.reference;

import com.honey.leetcode.model.ListNode;

import java.lang.ref.WeakReference;

/**
 * @author hualin.su
 * @date 2020-07-04 18:48
 */
public class Weak_Reference {
    public static void main(String[] args) {
        WeakReference<ListNode> weakReference = new WeakReference<>(new ListNode(1));

        System.out.println(weakReference.get());
        // 垃圾回收会直接回收弱引用
        System.gc();
        System.out.println(weakReference.get());

        ThreadLocal<ListNode> threadLocal = new ThreadLocal<>();
        threadLocal.set(new ListNode(1));
        threadLocal.remove();

    }
}
