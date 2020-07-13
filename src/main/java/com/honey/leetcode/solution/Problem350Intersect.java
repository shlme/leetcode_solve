package com.honey.leetcode.solution;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 给定两个数组，编写一个函数来计算它们的交集。
 *
 * @author hualin.su
 * @date 2020-07-13 21:04
 */
public class Problem350Intersect {

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};
        System.out.println(Arrays.toString(new Problem350Intersect().intersectSort(nums1, nums2)));
    }

    /**
     * 排序，省内存
     */
    public int[] intersectSort(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int[] result = new int[Math.min(nums1.length, nums2.length)];
        int index = 0, index1 = 0, index2 = 0;
        while (index1 < nums1.length && index2 < nums2.length) {
            if (nums1[index1] == nums2[index2]) {
                result[index++] = nums1[index1];
                index1++;
                index2++;
            } else if (nums1[index1] > nums2[index2]) {
                index2++;
            } else {
                index1++;
            }
        }
        return Arrays.copyOf(result, index);
    }

    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return intersect(nums2, nums1);
        }
        HashMap<Integer, Integer> numTimesMap = new HashMap<>();
        for (int num : nums1) {
            int times = numTimesMap.getOrDefault(num, 0);
            numTimesMap.put(num, times + 1);
        }
        int[] result = new int[nums1.length];
        int index = 0;
        for (int num : nums2) {
            if (numTimesMap.getOrDefault(num, 0) > 0) {
                result[index++] = num;
                numTimesMap.put(num, numTimesMap.get(num) - 1);
            }
        }
        return Arrays.copyOf(result, index);
    }
}
