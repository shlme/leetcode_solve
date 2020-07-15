package com.honey.leetcode.solution.problem;

/**
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 *
 * 输入：[1,8,6,2,5,4,8,3,7]
 *
 * 输出：49
 *
 * @author hualin.su
 * @date 2020-04-20 17:08
 */
public class Problem11MaxArea {
    public static void main(String[] args) {
        int[] height = {1,8,6,2,5,4,8,3,7};
        System.out.println(new Problem11MaxArea().maxArea(height));
    }
    /**
     * 双指针法
     */
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int maxArea = Math.min(height[left], height[right]) * (right - left);
        while (left < right) {
            if (height[left] > height[right]) {
                right--;
            } else {
                left++;
            }
            maxArea = Math.max(maxArea, Math.min(height[left], height[right]) * (right - left));
        }
        return maxArea;
    }
}
