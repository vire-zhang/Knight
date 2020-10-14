package com.yuanfudao;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ListNode node6 = new ListNode(3);
        ListNode node5 = new ListNode(2, node6);
        ListNode node4 = new ListNode(1, node5);
        ListNode node3 = new ListNode(1, node4);
        ListNode node2 = new ListNode(1, node3);
//        ListNode node1 = new ListNode(2, node2);
//        ListNode head = new ListNode(1, node1);
        SortLinkedList test = new SortLinkedList();
        ListNode res = test.deleteDuplicates(node2);
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
        System.out.println(node6.toString());
        Integer i = 90;
        System.out.println(i.toString());
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(list);
        System.out.println(list.toString());
        System.out.println(8);
        System.out.println("9");
    }
}
