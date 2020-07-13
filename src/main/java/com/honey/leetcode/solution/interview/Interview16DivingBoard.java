package com.honey.leetcode.solution.interview;

/**
 * @author hualin.su
 * @date 2020-07-08 22:35
 */
public class Interview16DivingBoard {

    public int[] divingBoard(int shorter, int longer, int k) {
        if (k == 0) {
            return new int[0];
        }
        if (shorter == longer) {
            return new int[]{shorter * k};
        }
        int[] result = new int[k + 1];
        for (int i = 0; i <= k; i++) {
            result[i] = shorter * i + longer * (k - i);
        }
        return result;
    }
}
