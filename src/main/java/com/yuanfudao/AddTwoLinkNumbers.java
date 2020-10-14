package com.yuanfudao;

public class AddTwoLinkNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        StringBuilder s1 = new StringBuilder("");
        StringBuilder s2 = new StringBuilder("");
        ListNode head1 = l1;
        ListNode head2 = l2;
        while (head1 != null) {
            s1.append(head1.val);
            head1 = head1.next;
        }
        while (head2 != null) {
            s2.append(head2.val);
            head2 = head2.next;
        }
        String s3 = bigNumAdd(s1.toString().toCharArray(), s2.toString().toCharArray());
        ListNode next = null;
        for (int i = s3.length(); i > 0; i--) {
            ListNode node = new ListNode(Integer.parseInt(s3.substring(i - 1, i)));
            node.next = next;
            next = node;
        }
        return next;
    }

    private String bigNumAdd(char[] c1, char[] c2) {

        StringBuilder result = new StringBuilder("");

        int i = c1.length - 1, j = c2.length - 1;
        boolean plus = false;
        while (i >= 0 || j >= 0) {
            int digit = 0;
            if (i >= 0 && j >= 0) digit = (c1[i] - '0') + (c2[j] - '0');
            if (i >= 0 && j < 0) digit = c1[i] - '0';
            if (i < 0 && j >= 0) digit = c2[j] - '0';
            if (plus) ++digit;
            plus = false;
            if (digit > 9) {
                digit = digit - 10;
                plus = true;
            }
            result.insert(0,digit);
            --i;
            --j;
        }
        if (plus) result.insert(0, 1);
        return result.toString();
    }
}
