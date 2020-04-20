package com.honey.leetcode.solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 542. 01 矩阵 给定一个由 0 和 1 组成的矩阵，找出每个元素到最近的 0 的距离。 两个相邻元素间的距离为 1 。
 */
public class Problem542Matrix {

    public static void main(String[] args) {
        int[][] matrix = {{1,0,1,1,0,0,1,0,0,1},{0,1,1,0,1,0,1,0,1,1},{0,0,1,0,1,0,0,1,0,0},{1,0,1,0,1,1,1,1,1,1},{0,1,0,1,1,0,0,0,0,1},{0,0,1,0,1,1,1,0,1,0},{0,1,0,1,0,1,0,0,1,1},{1,0,0,0,1,1,1,1,0,1},{1,1,1,1,1,1,1,0,1,0},{1,1,1,1,0,1,0,0,1,1}};
        System.out.println(Arrays.deepToString(matrix));
        int[][] result = new Problem542Matrix().updateMatrix(matrix);
        System.out.println(Arrays.deepToString(result));
    }

    class Dot {
        int i;
        int j;

        public Dot(int i, int j) {
            this.i = i;
            this.j = j;
        }

    }

    public int[][] updateMatrix(int[][] matrix) {
        LinkedList list = new LinkedList<Dot>();
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] result = new int[m][n];
        // 初始化
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    list.addLast(new Dot(i, j));
                    result[i][j] = 0;
                } else {
                    result[i][j] = m + n;
                }
            }
        }
        while (!list.isEmpty()) {
            Dot dot = (Dot) list.pollFirst();
            int distance = result[dot.i][dot.j];
            List<Dot> neighbours = obtainNeighbour(dot, m, n);
            for (Dot neighbour : neighbours) {
                if (result[neighbour.i][neighbour.j] > distance + 1) {
                    result[neighbour.i][neighbour.j] = distance + 1;
                    list.addLast(neighbour);
                }
            }

        }
        return result;
    }

    private List<Dot> obtainNeighbour(Dot dot, int m, int n) {
        ArrayList<Dot> result = new ArrayList<>();
        if (dot.i - 1 >= 0) result.add(new Dot(dot.i - 1, dot.j));
        if (dot.i + 1 < m) result.add(new Dot(dot.i + 1, dot.j));
        if (dot.j - 1 >= 0) result.add(new Dot(dot.i, dot.j - 1));
        if (dot.j + 1 < n) result.add(new Dot(dot.i, dot.j + 1));
        return result;
    }
}
