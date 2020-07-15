package com.honey.leetcode.solution.problem;

import com.honey.leetcode.model.ListNode;

/**
 * K 个一组翻转链表:
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 *
 * https://leetcode-cn.com/problems/reverse-nodes-in-k-group/
 *
 * @author hualin.su
 * @date 2020-07-07 10:53
 */
public class Problem25ReverseKGroup {

    public static void main(String[] args) {
        // 1->2->3->4->5
        int[] ints = {1, 2, 3, 4};
        ListNode listNode = ListNode.buildListNodeByArray(ints);
        System.out.println(new Problem25ReverseKGroup().reverseKGroup(listNode, 1));
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k <= 0) {
            return head;
        }
        ListNode nextHead = head;
        int i = 0;
        while (i < k && nextHead != null) {
            i++;
            nextHead = nextHead.next;
        }
        if (i < k) {
            return head;
        }
        ListNode prev = reverseKGroup(nextHead, k);
        ListNode curr = head;
        ListNode next = head.next;
        i = 0;
        while (i < k) {
            curr.next = prev;
            prev = curr;
            curr = next;
            next = next == null ? null : curr.next;
            i++;
        }

        return prev;
    }
}
