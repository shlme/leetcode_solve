package com.honey.leetcode.solution.problem;

import java.util.Arrays;

/**
 * 14. 最长公共前缀 编写一个函数来查找字符串数组中的最长公共前缀。 如果不存在公共前缀，返回空字符串 ""
 *
 * https://leetcode-cn.com/problems/longest-common-prefix/
 *
 * @author hualin.su
 * @date 2020-07-02 21:14
 */
public class Problem14LongestCommonPrefix {

    public static void main(String[] args) {
        System.out.println(new Problem14LongestCommonPrefix().longestCommonPrefixRecursion(new String[]{"flower", "flow", "fll", "f"}));
    }

    /**
     * 递归
     */
    public String longestCommonPrefixRecursion(String[] strs) {
        return commonPrefix(strs, 0, strs.length - 1);
    }

    private String commonPrefix(String[] strs, int start, int end) {
        if (start > end) {
            return "";
        }
        if (start == end) {
            return strs[start];
        }
        int mid = (start + end) >> 1;
        return commonPrefix(commonPrefix(strs, start, mid), commonPrefix(strs, mid + 1, end));
    }

    private String commonPrefix(String str1, String str2) {
        int len = Math.min(str1.length(), str2.length());
        int index = 0;
        while (index < len && str1.charAt(index) == str2.charAt(index)) {
            index++;
        }
        return str1.substring(0, index);
    }

    /**
     * 先排序
     */
    public String longestCommonPrefixSort(String[] strs) {
        if (strs.length < 1) {
            return "";
        }
        Arrays.sort(strs);
        int len = Math.min(strs[0].length(), strs[strs.length - 1].length());
        int index = 0;
        while (index < len && strs[0].charAt(index) == strs[strs.length - 1].charAt(index)) {
            index++;
        }
        return strs[0].substring(0, index);
    }

    public String longestCommonPrefix(String[] strs) {
        int index = 0;
        while (help(strs, index)) {
            index++;
        }
        return strs[0].substring(0, index);
    }

    private boolean help(String[] strs, int index) {
        Character c = null;
        for (String s : strs) {
            if (s.length() <= index) {
                return false;
            }
            if (c == null) {
                c = s.charAt(index);
            } else if (c != s.charAt(index)) {
                return false;
            }
        }
        return true;
    }
}
