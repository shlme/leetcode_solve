package com.honey.leetcode.solution;

/**
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 *
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 *
 * 你可以假设 nums1 和 nums2 不会同时为空。
 *
 * @author hualin.su
 * @date 2020-05-02 21:56
 */
public class Problem4MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        // nums1 = [1, 3] nums2 = [2]，则中位数是 2.0
        // nums1 = [1, 2] nums2 = [3, 4]，则中位数是 (2 + 3)/2 = 2.5

        int[] nums1 = {1, 2};
        int[] nums2 = {3, 4};
        System.out.println(new Problem4MedianOfTwoSortedArrays().findMedianSortedArrays(nums1, nums2));
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        double result;
        int len = nums1.length + nums2.length;
        int[] merge = new int[len];
        int i1 = 0, i2 = 0, i = 0;
        int mid = len >> 1;
        while (i <= mid && i1 < nums1.length && i2 < nums2.length) {
            if (nums1[i1] <= nums2[i2]) {
                merge[i++] = nums1[i1++];
            } else {
                merge[i++] = nums2[i2++];
            }
        }
        while (i <= mid && i1 < nums1.length) {
            merge[i++] = nums1[i1++];
        }
        while (i <= mid && (i2 < nums2.length)) {
            merge[i++] = nums2[i2++];
        }
        result = len % 2 == 0 ? (merge[mid] + merge[mid - 1]) / 2.0 : merge[mid];
        return result;
    }
}
