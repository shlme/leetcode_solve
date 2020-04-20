package com.honey.leetcode.solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 给出一个区间的集合，请合并所有重叠的区间。
 *
 * 示例 1: 输入: [[1,3],[2,6],[8,10],[15,18]] 输出: [[1,6],[8,10],[15,18]] 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 *
 * @author hualin.su
 * @date 2020-04-16 21:27
 */
public class Problem56MergeSection {

    public static void main(String[] args) {
        // [[1,4],[0,1]] → [0,4]
        // [[1,4],[0,4]] → [0,4]
        int[][] matrix = {{1, 4}, {0, 1}};
        int[][] result = new Problem56MergeSection().merge(matrix);
        System.out.println(Arrays.deepToString(result));
    }

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        List<int[]> result = new ArrayList<>();
        int i = 0;
        while (i < intervals.length) {
            int left = intervals[i][0];
            int right = intervals[i][1];
            while (i < intervals.length - 1 && right < intervals[i + 1][1]) {
                right = intervals[i + 1][1];
                i++;
            }
            result.add(new int[]{left, right});
            i++;
        }
        return result.toArray(new int[result.size()][]);
    }
}
