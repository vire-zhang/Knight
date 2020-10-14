package com.graph.util;

import com.graph.bean.ListNode;

public class CreateList {

    public static final ListNode createList(int[] vals) {
        ListNode next = null;
        for (int i = vals.length - 1; i >= 0; i--) {
            ListNode temp = new ListNode(vals[i], next);
            next = temp;
        }
        return next;
    }

    public static final ListNode getDefultList() {
        int[] vals = {1,2,3,4,5,6,7,8,9};
        return createList(vals);
    }
}
