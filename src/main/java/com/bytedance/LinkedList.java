package com.bytedance;

import com.graph.bean.ListNode;
import com.graph.util.CreateList;

public class LinkedList {

    public static final ListNode kFromButtom(ListNode head, int k) {
        ListNode first = head;
        ListNode second = head;
        int count = 1;
        while(count != k) {
            first = first.getNext();
            ++count;
        }
        while (first.getNext() != null) {
            first = first.getNext();
            second = second.getNext();
        }
        return second;
    }

    public static void main(String[] args) {
        ListNode list = CreateList.getDefultList();
        ListNode aim = kFromButtom(list, 9);
        System.out.println(aim.getVal());
        int[] vals = {1,34,56,78,8,9,0,4,54,23,23,78,9,9999,786,4535,23,2,56,7,8,9,67,24,42,78,574,6,3,653,4};
        ListNode list2 = CreateList.createList(vals);
        System.out.println(kFromButtom(list2, 18).getVal());
    }
}
