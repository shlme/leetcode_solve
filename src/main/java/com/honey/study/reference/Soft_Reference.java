package com.honey.study.reference;

import java.lang.ref.SoftReference;

/**
 * 软引用：非常适合缓存使用
 *
 * @author hualin.su
 * @date 2020-07-04 18:36
 */
public class Soft_Reference {
    public static void main(String[] args) {
        SoftReference<byte[]> softReference = new SoftReference<>(new byte[1024 * 1024 * 10]);
        System.out.println(softReference.get());
        System.gc();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(softReference.get());
        // 再分配一个数组，heap放不下，这时候系统会垃圾回收，先回收一次，如果不够，会把软引用干掉
        byte[] bytes = new byte[1024 * 1024 * 12];
        System.out.println(softReference.get());
    }
}
