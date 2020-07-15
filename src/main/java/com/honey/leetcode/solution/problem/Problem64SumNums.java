package com.honey.leetcode.solution.problem;

/**
 * 求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）
 *
 * https://leetcode-cn.com/problems/qiu-12n-lcof/
 *
 * @author hualin.su
 * @date 2020-05-23 11:21
 */
public class Problem64SumNums {
    private int res = 0;

    public int sumNums(int n) {
        boolean b = n > 1 && sumNums(n - 1) > 0;
        res += n;
        return res;

    }
}
