package com.graph.impl;

import com.graph.SwapList;
import com.graph.bean.ListNode;

public class SwapListImpl implements SwapList {

    @Override
    public ListNode swapPairs(ListNode head) {

        // 保存新链表头
        ListNode newHead = head;
        if (head != null && head.getNext() != null)
            newHead = head.getNext();
        // 当前节点
        ListNode currNode = head;
        // 当前节点的前一节点
        ListNode preNode = null;
        while (currNode != null && currNode.getNext() != null) {
            // 交换
            ListNode temp = currNode.getNext();
            currNode.setNext(currNode.getNext().getNext());
            temp.setNext(currNode);
            // 前项节点next更改
            if (preNode != null)
                preNode.setNext(temp);
            // 继续迭代
            preNode = currNode;
            currNode = currNode.getNext();
        }

        return newHead;
    }
}
