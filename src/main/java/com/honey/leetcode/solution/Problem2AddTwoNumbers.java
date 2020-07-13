package com.honey.leetcode.solution;

import com.honey.leetcode.model.ListNode;

import org.springframework.util.Assert;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * @author hualin.su
 * @date 2020-07-13 15:57
 */
public class Problem2AddTwoNumbers {
    public static void main(String[] args) {
        // 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4) 输出：7 -> 0 -> 8 原因：342 + 465 = 807
        ListNode listNode = new Problem2AddTwoNumbers().addTwoNumbers(ListNode.buildListNodeByArray(new int[]{5}), ListNode.buildListNodeByArray(new int[]{5}));
//        ListNode listNode = new Problem2AddTwoNumbers().addTwoNumbers(null, ListNode.buildListNodeByArray(new int[]{0}));
        System.out.println(listNode);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Assert.notNull(l1, "param l1 cannot be null");
        Assert.notNull(l2, "param l2 cannot be null");
        ListNode result = new ListNode(0);
        ListNode node = result;
        int m = 0;
        while (l1 != null && l2 != null) {
            int sum = l1.val + l2.val + m;
            node.next = new ListNode(sum % 10);
            m = sum / 10;
            l1 = l1.next;
            l2 = l2.next;
            node = node.next;
        }
        while (l1 != null) {
            int sum = l1.val + m;
            node.next = new ListNode(sum % 10);
            m = sum / 10;
            l1 = l1.next;
            node = node.next;
        }
        while (l2 != null) {
            int sum = l2.val + m;
            node.next = new ListNode(sum % 10);
            m = sum / 10;
            l2 = l2.next;
            node = node.next;
        }
        if (m > 0) {
            node.next = new ListNode(m);
        }
        return result.next;
    }
}
