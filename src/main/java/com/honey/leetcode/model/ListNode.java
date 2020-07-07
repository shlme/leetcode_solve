package com.honey.leetcode.model;

/**
 * @author hualin.su
 * @date 2020-04-26 22:56
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    /**
     * 将数组转换为ListNode
     */
    public static ListNode buildListNodeByArray(int[] ints) {
//        int[] ints = {1, 2, 3, 4, 5};
        ListNode head = new ListNode(0);
        ListNode curr = head;
        for (int i = 0; i < ints.length; i++) {
            curr.next = new ListNode(ints[i]);
            curr = curr.next;
        }
        return head.next;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ListNode curr = this;
        while (curr != null) {
            sb.append(curr.val);
            if (curr.next != null) {
                sb.append(" → ");
            }
            curr = curr.next;
        }
        return sb.toString();
    }
}
