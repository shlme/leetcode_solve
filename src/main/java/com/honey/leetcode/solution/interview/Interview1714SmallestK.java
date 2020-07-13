package com.honey.leetcode.solution.interview;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author hualin.su
 * @date 2020-07-09 16:56
 */
public class Interview1714SmallestK {
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 2, 4, 6, 8};
        System.out.println(Arrays.toString(new Interview1714SmallestK().smallestK(arr, 0)));
    }

    public int[] smallestKArrays(int[] arr, int k) {
        return Arrays.stream(arr).sorted().limit(k).toArray();
    }
    /**
     * 最小堆
     */
    public int[] smallestK(int[] arr, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k);
        for (Integer a : arr) {
            minHeap.add(a);
        }
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = minHeap.poll();
        }
        return result;
    }

}
