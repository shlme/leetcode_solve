package com.honey.study;

/**
 * @author hualin.su
 * @date 2020-07-05 13:17
 */
public class TestDemo {
    public static void main(String[] args) {
        Object object = null;
        synchronized (object) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
