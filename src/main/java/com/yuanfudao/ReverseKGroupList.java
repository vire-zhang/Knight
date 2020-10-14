package com.yuanfudao;

public class ReverseKGroupList {

    public static ListNode reverseKGroup(ListNode head, int k) {

        if (head == null || head.next == null || k == 1) return head;
        ListNode headNew = null;
        ListNode lastTail = null, currTail = null;
        int count = 0;
        ListNode node = head;
        ListNode next = null;
        ListNode oldCurrTail = null;
        while (node != null) {
            ListNode temp = new ListNode(node.val);
            temp.next = next;
            next = temp;
            if (count == 0) {
                currTail = temp;
                oldCurrTail = node;
            }
            ++count;
            if (count == k) {
                if (headNew == null) headNew = temp;
                else lastTail.next = temp;
                count = 0;
                lastTail = currTail;
                next = null;
            }
            node = node.next;
        }
        if (count != 0) {
            lastTail.next = oldCurrTail;
        }
        return headNew;
    }


    public static void main(String[] args) {
        ListNode list = ListNode.getDefaultList();
        ListNode newList = ReverseKGroupList.reverseKGroup(list, 2);
    }
}
