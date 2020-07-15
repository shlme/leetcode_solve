package com.honey.leetcode.solution.problem;

/**
 * 152. 乘积最大子数组
 *
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 *
 * https://leetcode-cn.com/problems/maximum-product-subarray/
 *
 * @author hualin.su
 * @date 2020-05-10 19:31
 */
public class Problem152MaximumProductSubarray {

    public static void main(String[] args) {
        // 6
//        int[] nums = {2, 3, -2, 4};
        // 0
        int[] nums = {-2, 0, -1};
        System.out.println(new Problem152MaximumProductSubarray().maxProduct(nums));
    }

    public int maxProduct(int[] nums) {
        int length = nums.length;
        int[] max = new int[length];
        int[] min = new int[length];
        for (int i = 0; i < length; i++) {
        }

        return 0;
    }

    public int max(int num1, int num2, int num3) {
        return Math.max(Math.max(num1, num2), num3);
    }

}
