package com.honey.leetcode.solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * 315. 计算右侧小于当前元素的个数
 *
 * https://leetcode-cn.com/problems/count-of-smaller-numbers-after-self/
 *
 * @author hualin.su
 * @date 2020-07-11 21:43
 */
public class Problem315CountSmaller {
    public static void main(String[] args) {
        int[] nums = {2, 0, 1, 1, 1};
        System.out.println(new Problem315CountSmaller().countSmaller(nums));
    }

    /**
     * 树状数组:https://leetcode-cn.com/problems/count-of-smaller-numbers-after-self/solution/shu-zhuang-shu-zu-by-liweiwei1419/
     */
    private int[] tree;

    /**
     * https://leetcode-cn.com/problems/count-of-smaller-numbers-after-self/solution/ji-suan-you-ce-xiao-yu-dang-qian-yuan-su-de-ge-s-7/
     */
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> resultList = new ArrayList<>();
        // 离散化
        int[] ints = discretization(nums);
        // 值-顺序
        HashMap<Integer, Integer> rankMap = new HashMap<>();
        int rank = 0;
        for (int n : ints) {
            rankMap.put(n, ++rank);
        }
        init(nums.length + 1);
        for (int i = nums.length - 1; i >= 0; --i) {
            int id = rankMap.get(nums[i]);
            resultList.add(query(id - 1));
            update(id);
        }
        Collections.reverse(resultList);
        return resultList;
    }

    private void init(int length) {
        tree = new int[length];
        Arrays.fill(tree, 0);
    }

    private int lowBit(int x) {
        return x & (-x);
    }

    private void update(int pos) {
        while (pos < tree.length) {
            tree[pos] += 1;
            pos += lowBit(pos);
        }
    }

    /**
     * 数组前缀和
     */
    private int query(int pos) {
        int ret = 0;
        while (pos > 0) {
            ret += tree[pos];
            pos -= lowBit(pos);
        }
        return ret;
    }

    /**
     * 离散化：去重&排序
     */
    private int[] discretization(int[] nums) {
        Set<Integer> set = new TreeSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int size = set.size();
        int[] a = new int[size];
        int index = 0;
        for (int num : set) {
            a[index++] = num;
        }
        return a;
    }

}
