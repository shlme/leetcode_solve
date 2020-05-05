package com.honey.leetcode.solution;

import com.honey.leetcode.model.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 合并K个排序链表
 *
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 *
 * @author hualin.su
 * @date 2020-04-26 22:55
 */
public class Problem23MergeKSortedLists {

    /**
     * todo
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        for (ListNode listNode : lists) {
            queue.offer(listNode);
        }
        ListNode head = queue.poll();
        ListNode curr = head;
        while (!queue.isEmpty()) {
            curr.next = queue.poll();
            curr = curr.next;
            if (curr.next != null) {
                queue.offer(curr.next);
            }
        }
        return head;
    }

    /**
     * 分而治之
     */
    public ListNode mergeKLists3(ListNode[] lists) {
        return mergeLists(lists, 0, lists.length - 1);
    }

    private ListNode mergeLists(ListNode[] lists, int left, int right) {
        if (left == right) {
            return lists[left];
        }
        int mid = (left + right) >> 1;
        return mergeTwoLists(mergeLists(lists, left, mid), mergeLists(lists, mid + 1, right));
    }

    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode curr = head;
        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                curr.next = l2;
                l2 = l2.next;
            } else {
                curr.next = l1;
                l1 = l1.next;
            }
            curr = curr.next;
        }
        while (l1 != null) {
            curr.next = l1;
        }
        while (l2 != null) {
            curr.next = l2;
        }
        return head.next;
    }

    /**
     * 逐个合并链表
     */
    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        ListNode result = lists[0];
        for (int i = 1; i < lists.length; i++) {
            result = mergeTwoLists(lists[i], result);
        }
        return result;
    }

    private ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode curr = head;
        while (l1 != null || l2 != null) {
            if (l1 == null || (l2 != null && l1.val > l2.val)) {
                curr.next = l2;
                l2 = l2.next;
            } else {
                curr.next = l1;
                l1 = l1.next;
            }
            curr = curr.next;
        }
        return head.next;
    }

}
