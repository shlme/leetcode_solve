package com.honey.leetcode.solution.problem;

import java.util.Arrays;
import java.util.List;

/**
 * 120. 三角形最小路径和
 *
 * @author hualin.su
 * @date 2020-07-14 23:49
 */
public class Problem120MinimumTotal {
    public static void main(String[] args) {

    }

    /**
     * 动态规划
     * 内存优化方案：
     * 1、优化distance处理，只需要前一行的数据
     * 2、在triangle原地计算
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int size = triangle.size();
        int[][] distance = new int[size][size];
        distance[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < size; i++) {
            distance[i][0] = triangle.get(i).get(i) + distance[i - 1][0];
            for (int j = 1; j < i; j++) {
                distance[i][j] = triangle.get(i).get(j) + Math.min(distance[i - 1][j - 1], distance[i - 1][j]);
            }
            distance[i][i] = triangle.get(i).get(i) + distance[i - 1][i - 1];
        }
        int result = distance[size - 1][0];
        for (int dis : distance[size - 1]) {
            result = Math.min(result, dis);
        }
        return result;
    }


    /**
     * 递归
     */
    public int minimumTotalRecursion(List<List<Integer>> triangle) {
        return minimumTotal(triangle, 0, 0);
    }

    /**
     * @param matrix 三角形矩阵
     * @param i      当前结点的行
     * @param j      当前结点的列
     * @return 最小路径和
     */
    private int minimumTotal(List<List<Integer>> matrix, int i, int j) {
        // 最后一行
        if (i == matrix.size() - 1) {
            return matrix.get(i).get(j);
        }
        return matrix.get(i).get(j) + Math.min(minimumTotal(matrix, i + 1, j), minimumTotal(matrix, i + 1, j + 1));
    }
}
