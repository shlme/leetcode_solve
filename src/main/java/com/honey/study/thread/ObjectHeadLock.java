package com.honey.study.thread;

import com.honey.leetcode.model.ListNode;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author hualin.su
 * @date 2020-06-12 17:12
 */
public class ObjectHeadLock {
    public static void main(String[] args) throws InterruptedException {
        ListNode listNode = new ListNode(2);
        System.out.println("哈希值：");
        System.out.println(Integer.toHexString(listNode.hashCode()));
        System.out.println("无锁：");
        System.out.println(ClassLayout.parseInstance(listNode).toPrintable());
        Thread.sleep(5000);
        synchronized (listNode) {

            System.out.println(ClassLayout.parseInstance(listNode).toPrintable());

        }
    }
}
