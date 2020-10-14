package com.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class LFUCache {

    private class DoubleListNode {
        int key;
        int val;
        int freq;
        DoubleListNode pre;
        DoubleListNode next;
    }

    int capacity;
    Map<Integer, DoubleListNode> freqCache;
    Map<Integer, DoubleListNode> keyFreq;
    int minFreq;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.freqCache = new HashMap<Integer, DoubleListNode>();
        this.keyFreq = new HashMap<Integer, DoubleListNode>();
        this.minFreq = 0;
    }

    public int get(int key) {
        DoubleListNode node = keyFreq.get(key);
        if (node == null) return -1;
        int result = node.val;
        // 删除旧的freq-List
        deleteNodeInFreqCache(node);
        // 更新新的freq-List
        ++node.freq;
        addNodeInFreqCache(node);
        // 更新minFreq
        minFreq = (int)getMinKey(freqCache);
        return result;
    }

    public void put(int key, int value) {
        if (capacity == 0 && keyFreq.size() == 0) return;
        DoubleListNode node = keyFreq.get(key);
        if (node == null) {
            //新增
            node = new DoubleListNode();
            node.key = key;
            node.val = value;
            node.freq = 1;
            keyFreq.put(key, node);
            if (capacity != 0) {
                addNodeInFreqCache(node);
                --capacity;
            } else {
                DoubleListNode minFreqNode = freqCache.get(minFreq).pre;
                keyFreq.remove(minFreqNode.key);
                deleteNodeInFreqCache(minFreqNode);
                addNodeInFreqCache(node);
            }
        } else {
            //修改
            deleteNodeInFreqCache(node);
            ++node.freq;
            node.val = value;
            addNodeInFreqCache(node);
        }
        minFreq = (int)getMinKey(freqCache);
    }

    private void deleteNodeInFreqCache(DoubleListNode node) {
        if (node.pre != node) {
            if (freqCache.get(node.freq) == node) freqCache.replace(node.freq, node.next);
            node.pre.next = node.next;
            node.next.pre = node.pre;
        } else {
            freqCache.remove(node.freq);
        }
    }

    private void addNodeInFreqCache(DoubleListNode node) {
        DoubleListNode freqNode = freqCache.get(node.freq);
        if (freqNode != null) {
            freqCache.replace(node.freq, node);
            node.pre = freqNode.pre;
            freqNode.pre.next = node;
            freqNode.pre = node;
            node.next = freqNode;
        } else {
            freqCache.put(node.freq, node);
            node.pre = node;
            node.next = node;
        }
    }

    /**
     * 求Map<K,V>中Key(键)的最小值
     * @param map
     * @return
     */
    private static Object getMinKey(Map<Integer, DoubleListNode> map) {
        if (map == null) return null;
        Set<Integer> set = map.keySet();
        Object[] obj = set.toArray();
        Arrays.sort(obj);
        return obj[0];
    }

    public static void main(String[] args) {
        LFUCache cache = new LFUCache(5);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        cache.get(2);
        cache.put(2,10);
        cache.get(3);
        cache.get(1);
        cache.put(4, 4);
        cache.put(5, 5);
        cache.put(6, 6);
    }
}
