package com.huawei;

import java.util.*;

public class LFUCache {

    private class LastedUse {
        private final int UPPER_MUTIL = 10;
        Map<Integer, Integer> lasted = new HashMap<>();
        int max = 0;
    }
    private int capacity;
    private Map<Integer, Integer> KVMap = new HashMap<>();;
    private Map<Integer, Integer> frequency = new HashMap<>();
    // 最近使用的，数值最大
    private LastedUse lastedUse = new LastedUse();

    public LFUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (capacity == 0) return -1;
        if (KVMap.containsKey(key)){
            frequency.replace(key, frequency.get(key) + 1);
            lastedUse.lasted.replace(key, ++lastedUse.max);
            if (lastedUse.max > lastedUse.UPPER_MUTIL * capacity)
                reSetLastedUse();
            return KVMap.get(key);
        }
        else return -1;
    }

    public void put(int key, int value) {
        if (capacity == 0) return;
        if (KVMap.containsKey(key)) {
            KVMap.replace(key, value);
            frequency.replace(key, frequency.get(key) + 1);
            lastedUse.lasted.replace(key, ++lastedUse.max);
        }
        else{
            //full
            if (KVMap.size() == capacity) {
                List<Map.Entry<Integer, Integer>> list = new ArrayList<>();
                for (Map.Entry<Integer, Integer> entry : frequency.entrySet())
                    list.add(entry);
                Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
                    public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                        return o1.getValue() - o2.getValue();
                    }
                });
                List<Integer> deleteCandidate = new ArrayList<>();
                deleteCandidate.add(list.get(0).getKey());
                int i = 1;
                while (i < list.size() && list.get(i).getValue() == list.get(0).getValue()) {
                    deleteCandidate.add(list.get(i).getKey());
                    i++;
                }
                int min = deleteCandidate.get(0);
                for (Integer k : deleteCandidate) {
                    if (lastedUse.lasted.get(k) < lastedUse.lasted.get(min)) min = k;
                }
                KVMap.remove(min);
                frequency.remove(min);
                lastedUse.lasted.remove(min);
            }
            //notfull
            frequency.merge(key, 1, Integer::sum);
            KVMap.put(key, value);
            lastedUse.lasted.put(key, ++lastedUse.max);
        }
        if (lastedUse.max > lastedUse.UPPER_MUTIL * capacity)
            reSetLastedUse();
    }
    private void reSetLastedUse() {
        lastedUse.max = 0;
        for (Map.Entry<Integer, Integer> e : lastedUse.lasted.entrySet()) {
            e.setValue(++lastedUse.max);
        }
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

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
