package com.honey.study.thread;

/**
 * @author hualin.su
 * @date 2020-07-05 9:17
 */
public class Disorder {
    private static int x = 0, y = 0;
    private static int a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        for (; ; ) {
            i++;
            x = 0;
            y = 0;
            a = 0;
            b = 0;
            Thread one = new Thread(() -> {
                a = 1;
                x = b;
            });
            Thread other = new Thread(() -> {
                b = 1;
                y = a;
            });
            one.start();
            other.start();
            one.join();
            other.join();
            String result = String.format("第%s次, x=%s, y=%s", i, x, y);
            if (x == 0 && y == 0) {
                // 意味着指令发生了重排序
                System.out.println(result);
                break;
            }
        }
    }
}
