package com.honey.leetcode.solution.problem;

/**
 * @author hualin.su
 * @date 2020-07-08 17:40
 */
public class Problem5LongestPalindrome {

    public static void main(String[] args) {
        System.out.println(new Problem5LongestPalindrome().longestPalindrome("cbbd"));
    }

    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        int max = 0;
        int left = 0, right = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            int len = Math.max(palindromeLength(s, i - 1, i + 1), palindromeLength(s, i, i + 1));
            if (max < len) {
                int half = len >> 1;
                right = i + half;
                left = len % 2 == 1 ? (i - half) : (i - (half - 1));
                max = len;
            }
        }
        return s.substring(left, right + 1);
    }

    private int palindromeLength(String s, int left, int right) {
        if (left < 0 || right >= s.length() || s.charAt(left) != s.charAt(right)) {
            return right - left - 1;
        }
        return palindromeLength(s, left - 1, right + 1);
    }
}
