package com.honey.leetcode.solution.problem;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * @author hualin.su
 * @date 2020-04-22 22:23
 */
public class Problem3LongestSubstring {
    public static void main(String[] args) {
        // 输入: "abcabcbb" 输出: 3 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
        System.out.println(new Problem3LongestSubstring().lengthOfLongestSubstring3("abba"));

        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println(threadInfo.getThreadId() + ":" + threadInfo.getThreadName());
        }
        "a".notifyAll();
    }

    /**
     * 利用字母新建数组存储下标
     */
    public int lengthOfLongestSubstring1(String s) {
        int start = 0;
        int len = 0;
        int[] index = new int[128];
        char[] chars = s.toCharArray();
        for (int end = 0; end < chars.length; end++) {
            start = Math.max(index[chars[end]], start);
            len = Math.max(len, end - start + 1);
            index[chars[end]] = end + 1;
        }
        return len;
    }

    /**
     * 贪心算法
     */
    public int lengthOfLongestSubstring2(String s) {
        int start = 0;
        int len = 0;
        char[] chars = s.toCharArray();
        for (int end = 0; end < chars.length; end++) {
            start = Math.max(getCharPos(chars, start, end, chars[end]) + 1, start);
            len = Math.max(len, end - start + 1);
        }
        return len;
    }

    /**
     * 滑动窗口
     */
    public int lengthOfLongestSubstring3(String s) {
        int start = 0;
        int end = 0;
        int len = 0;
        char[] chars = s.toCharArray();
        Set<Character> set = new HashSet<>();
        while (end < chars.length && start < chars.length) {
            if (set.contains(chars[end])) {
                set.remove(chars[start++]);
            } else {
                len = Math.max(len, end - start + 1);
                set.add(chars[end++]);
            }
        }
        return len;
    }

    private int getCharPos(char[] chars, int start, int end, char c) {
        for (int i = start; i < end; i++) {
            if (chars[i] == c) {
                return i;
            }
        }
        return -1;
    }
}
