package com.honey.leetcode.solution;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 编写一个程序，通过已填充的空格来解决数独问题。 可参考Problem36
 *
 * 一个数独的解法需遵循如下规则：
 *
 * 数字 1-9 在每一行只能出现一次。
 *
 * 数字 1-9 在每一列只能出现一次。
 *
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。 空白格用 '.' 表示。
 *
 * @author hualin.su
 * @date 2020-05-05 17:50
 */
public class Problem37SudokuSolver {

    public static void main(String[] args) {
        char[][] board = new char[][]{{'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        new Problem37SudokuSolver().solveSudoku(board);
        System.out.println(Arrays.deepToString(board));
    }

    /**
     * 行剩余可选字符
     */
    private HashSet<Character>[] rows = new HashSet[9];
    /**
     * 列剩余可选字符
     */
    private HashSet<Character>[] columns = new HashSet[9];
    /**
     * 方格剩余可选字符
     */
    private HashSet<Character>[] boxes = new HashSet[9];


    /**
     * 行出现的字符
     */
    private byte[][] rowBytes = new byte[9][9];
    /**
     * 列出现的字符
     */
    private byte[][] colBytes = new byte[9][9];
    /**
     * 方格出现的字符
     */
    private byte[][] boxBytes = new byte[9][9];

    public void solveSudoku(char[][] board) {
        if (board == null || board.length != 9 || board[0] == null || board[0].length != 9) {
            return;
        }
//        solve(board);
//        backTrace(board);
        backTraceByte(board);
    }

    /**
     * 回溯：用位标记字符出现的位置
     */
    private void backTraceByte(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    char c = board[i][j];
                    int boxIndex = (i / 3) * 3 + j / 3;
                    rowBytes[i][c - '1'] = 1;
                    colBytes[j][c - '1'] = 1;
                    boxBytes[boxIndex][c - '1'] = 1;
                }
            }
        }
        backTraceByte(board, 0, 0);
    }

    private boolean backTraceByte(char[][] board, int row, int col) {
        if (col == 9) {
            return backTraceByte(board, row + 1, 0);
        }
        if (row == 9) {
            return true;
        }
        if (board[row][col] != '.') {
            return backTraceByte(board, row, col + 1);
        }
        for (int index = 0; index < 9; index++) {
            int boxIndex = (row / 3) * 3 + col / 3;
            boolean isValid = rowBytes[row][index] + colBytes[col][index] + boxBytes[boxIndex][index] == 0;
            if (isValid) {
                board[row][col] = (char) (index + '1');
                rowBytes[row][index] = 1;
                colBytes[col][index] = 1;
                boxBytes[boxIndex][index] = 1;
                if (backTraceByte(board, row, col + 1)) {
                    return true;
                }
                // 失败，回填
                board[row][col] = '.';
                rowBytes[row][index] = 0;
                colBytes[col][index] = 0;
                boxBytes[boxIndex][index] = 0;
            }
        }
        return false;
    }

    /**
     * 回溯算法
     */
    private boolean backTrace(char[][] board) {
        HashSet<Character> characters = new HashSet<>();
        for (char c = '1'; c <= '9'; c++) {
            characters.add(c);
        }
        for (int i = 0; i < 9; i++) {
            rows[i] = new HashSet<>();
            rows[i].addAll(characters);
            columns[i] = new HashSet<>();
            columns[i].addAll(characters);
            boxes[i] = new HashSet<>();
            boxes[i].addAll(characters);
        }
        // 初始化
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int boxIndex = (i / 3) * 3 + j / 3;
                char num = board[i][j];
                rows[i].remove(num);
                columns[j].remove(num);
                boxes[boxIndex].remove(num);
            }
        }
        return backTrace(board, 0, 0);
    }

    private boolean backTrace(char[][] board, int row, int col) {
        if (col == 9) {
            return backTrace(board, row + 1, 0);
        }
        if (row == 9) {
            return true;
        }
        if (board[row][col] != '.') {
            return backTrace(board, row, col + 1);
        }
        for (char c = '1'; c <= '9'; c++) {
            int boxIndex = (row / 3) * 3 + col / 3;
            boolean isValid = rows[row].contains(c) && columns[col].contains(c) && boxes[boxIndex].contains(c);
            if (isValid) {
                board[row][col] = c;
                rows[row].remove(c);
                columns[col].remove(c);
                boxes[boxIndex].remove(c);
                if (backTrace(board, row, col + 1)) {
                    return true;
                }
                // 失败，回填
                board[row][col] = '.';
                rows[row].add(c);
                columns[col].add(c);
                boxes[boxIndex].add(c);
            }
        }
        return false;
    }


    /**
     * 暴力解法
     */
    public boolean solve(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '.') {
                    HashSet<Character> validChar = getValidChar(board, i, j);
                    if (validChar.isEmpty()) {
                        return false;
                    }
                    for (char c : validChar) {
                        board[i][j] = c;
                        if (solve(board)) {
                            return true;
                        }
                        board[i][j] = '.';
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private HashSet<Character> getValidChar(char[][] board, int i, int j) {
        HashSet<Character> result = new HashSet<>();
        for (char c = '1'; c <= '9'; c++) {
            result.add(c);
        }
        for (int m = 0; m < board[i].length; m++) {
            result.remove(board[i][m]);
        }
        for (int n = 0; n < board.length; n++) {
            result.remove(board[n][j]);
        }
        int boxIndex = (i / 3) * 3 + j / 3;
        int iBase = (boxIndex / 3) * 3;
        int jBase = (boxIndex % 3) * 3;
        for (int k = 0; k < 3; k++) {
            for (int l = 0; l < 3; l++) {
                result.remove(board[iBase + k][jBase + l]);
            }
        }
        return result;
    }
}
