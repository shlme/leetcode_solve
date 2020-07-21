package com.honey.leetcode.solution.problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author hualin.su
 * @date 2020-07-21 19:08
 */
public class Problem15ThreeSum {

    public static void main(String[] args) {
        // [-1, 0, 1, 2, -1, -4]
//        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
//        int[] nums = new int[]{0, 0, 0};
//        int[] nums = new int[]{-2, 0, 0, 2, 2};
        int[] nums = new int[]{-4, -4, -3, -2, -1, 0, 2};
        List<List<Integer>> lists = new Problem15ThreeSum().threeSum(nums);
        System.out.println(lists);
    }

    /**
     * 双指针
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int start = 0; start < nums.length - 2; start++) {
            // 去重
            if (start > 0 && nums[start] == nums[start - 1]) {
                continue;
            }
            int left = start + 1, right = nums.length - 1;
            while (left < right) {
                if (left > start + 1 && nums[left] == nums[left - 1]) {
                    // 去重
                    left++;
                } else if (right < nums.length - 1 && nums[right] == nums[right + 1]) {
                    // 去重
                    right--;
                } else if (nums[start] + nums[left] + nums[right] == 0) {
                    result.add(Arrays.asList(nums[start], nums[left], nums[right]));
                    left++;
                    right--;
                } else if (nums[start] + nums[left] + nums[right] > 0) {
                    right--;
                } else if (nums[start] + nums[left] + nums[right] < 0) {
                    left++;
                }
            }
        }
        return result;
    }
}
