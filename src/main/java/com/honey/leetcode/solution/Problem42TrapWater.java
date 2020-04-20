package com.honey.leetcode.solution;

import java.util.Stack;

/**
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 * 示例: 输入: [0,1,0,2,1,0,1,3,2,1,2,1] 输出: 6
 *
 * @author hualin.su
 * @date 2020-04-20 17:26
 */
public class Problem42TrapWater {
    public static void main(String[] args) {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(new Problem42TrapWater().trap(height));
    }

    /**
     * 使用堆栈
     */
    public int trap(int[] height) {
        int totalArea = 0;
        Stack<Integer> stack = new Stack<>();
        int current = 0;
        while (current < height.length) {
            while (!stack.isEmpty() && height[current] > height[stack.peek()]) {
                int bottom = height[stack.pop()];
                if (stack.isEmpty()) {
                    continue;
                }
                totalArea += (current - stack.peek() - 1) * (Math.min(height[stack.peek()], height[current]) - bottom);
            }
            stack.push(current++);
        }

        return totalArea;
    }
}
