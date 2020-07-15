package com.honey.leetcode.solution.problem;

/**
 * @author hualin.su
 * @date 2020-05-23 11:57
 */
public class ProblemLongestPalindrome {
    public static void main(String[] args) {
        System.out.println(new ProblemLongestPalindrome().longestPalindrome2("cbbd"));
//        System.out.println(new ProblemLongestPalindrome().longestPalindrome2("babad"));
    }

    /**
     * 动态规划
     */
    public String longestPalindrome2(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        int length = s.length();
        int start = 0;
        int len = 1;
        boolean[][] status = new boolean[length][length];
        for (int i = length - 1; i >= 0; i--) {
            for (int j = i; j < length ; j++) {
                if (i == j) {
                    status[i][j] = true;
                } else if (j - i == 1) {
                    status[i][j] = s.charAt(i) == s.charAt(j);
                } else {
                    status[i][j] = status[i + 1][j - 1] && (s.charAt(i) == s.charAt(j));
                }
                if (status[i][j] && (j - i + 1) > len) {
                    start = i;
                    len = j - i + 1;
                }
            }
        }
        return s.substring(start, start + len);
    }

    /**
     * 中心扩散法
     */
    public String longestPalindrome1(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        int start = 0;
        int end = 0;
        for (int i = 0; i < s.length(); i++) {
            // 奇数中心位扩展
            int even = expendPalindrome(s, i, i);
            // 偶数中心位扩展
            int odd = expendPalindrome(s, i, i + 1);
            int maxLen = Math.max(even, odd);
            // 大于当前最长长度
            if (maxLen > end - start + 1) {
                start = i - ((maxLen - 1) >> 1);
                end = i + (maxLen >> 1);
            }
        }
        return s.substring(start, end + 1);
    }

    /**
     * 扩展回文串，返回最长长度
     */
    private int expendPalindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // (right-1)-(left-1) +1
        return right - left - 1;
    }
}
