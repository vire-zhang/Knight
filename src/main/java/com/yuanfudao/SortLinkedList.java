package com.yuanfudao;

public class SortLinkedList {

    public ListNode deleteDuplicates(ListNode head) {

        if (head == null) return head;
        boolean flag = false;
        if (head.next != null && head.next.val == head.val)
            flag = true;
        ListNode newHead = head;
        ListNode preValidNode = head;
        ListNode node = head.next;
        int preVal = head.val;
        while (node != null) {
            if (node.val != preVal && (node.next == null || node.next.val != node.val)) {
                preValidNode.next = node;
                preValidNode = node;
                preVal = node.val;
            }
            preVal = node.val;
            node = node.next;
        }
        preValidNode.next = null;
        if (flag) newHead = newHead.next;
        return newHead;
    }
}
