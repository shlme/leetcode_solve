package com.honey.leetcode.solution.problem;

/**
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 *
 * @author hualin.su
 * @date 2020-07-15 14:07
 */
public class Problem43Multiply {
    public static void main(String[] args) {
        // 56088
        System.out.println(new Problem43Multiply().multiply("123", "456"));
    }

    public String multiply(String num1, String num2) {
        int len2 = num2.length();
        int len1 = num1.length();
        int len = len1 + len2;
        int[] n1 = getIntArray(num1);
        int[] n2 = getIntArray(num2);
        int[] multiply = new int[len];
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                int sum = n1[i] * n2[j] + multiply[i + j];
                multiply[i + j] = sum % 10;
                multiply[i + j + 1] += sum / 10;
            }
        }
        return getNumString(multiply);
    }

    /**
     * 将逆序的int数组转换为字符串
     */
    private String getNumString(int[] ints) {
        // 最高非0位
        int t = 0;
        for (int i = ints.length - 1; i >= 0; i--) {
            if (ints[i] != 0) {
                t = i;
                break;
            }
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i <= t; i++) {
            result.insert(0, ints[i]);
        }
        return result.toString();
    }

    /**
     * 将字符串转换为逆序的int数组
     */
    private int[] getIntArray(String str) {
        int length = str.length();
        int[] ints = new int[length];
        char[] chars = str.toCharArray();
        for (int i = 0; i < length; i++) {
            ints[length - 1 - i] = chars[i] - '0';
        }
        return ints;
    }

}
