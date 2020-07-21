package com.honey.leetcode.solution.problem;

/**
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 *
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
 *
 * @author hualin.su
 * @date 2020-07-20 21:28
 */
public class Problem7Reverse {
    public static void main(String[] args) {
        int result = new Problem7Reverse().reverse(-123);
        System.out.println(result);
    }

    public int reverse(int x) {
        int result = 0;
        while (x != 0) {
            int tmp = x % 10;
            int newResult = result * 10 + tmp;
            if ((newResult - tmp) / 10 != result) {
                return 0;
            }
            x /= 10;
            result = newResult;
        }
        return result;
    }
}
