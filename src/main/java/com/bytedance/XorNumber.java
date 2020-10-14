package com.bytedance;

public class XorNumber {

    private class NumTrie{

        NumTrie[] next = new NumTrie[2];
        int count = 1;

        public void buildTrie(int[] nums) {

            NumTrie trie = this;
            for (int num : nums) {
                for (int i = 31; i >= 0; i--) {
                    int digit = (num >> i) & 1;
                    if (trie.next[digit] == null) trie.next[digit] = new NumTrie();
                    else ++trie.next[digit].count;
                    trie = trie.next[digit];
                }
            }
        }
    }

    public long solve(int[] a, int m) {

        NumTrie trie = new NumTrie();
        trie.buildTrie(a);
        long result = 0;
        for (int num : a) {
            result += queryNumTrie(trie, num, m, 31);
        }
        return  result / 2;
    }

    private long queryNumTrie(NumTrie trie, int num, int m, int index) {

        if (trie == null) return 0;

        NumTrie currentTrie = trie;
        for (int i = index; i >= 0; i--) {
            int numDigit = (num >> i) & 1;
            int mDigit = (m >> i) & 1;
            if (mDigit == 1 && numDigit == 1) {
                if (currentTrie.next[0] == null) return 0;
                else currentTrie = currentTrie.next[0];
            }
            if (mDigit == 1 && numDigit == 0) {
                if (currentTrie.next[1] == null) return 0;
                else currentTrie = currentTrie.next[1];
            }
            if (mDigit == 0 && numDigit == 1) {
                long l = currentTrie.next[0] == null ? 0 : currentTrie.next[0].count;
                long r = queryNumTrie(currentTrie.next[1], num, m, i - 1);
                return l + r;
            }
            if (mDigit == 0 && numDigit == 0) {
                long l = queryNumTrie(currentTrie.next[0], num, m, i - 1);
                long r = currentTrie.next[1] == null ? 0 : currentTrie.next[1].count;
                return l + r;
            }
        }

        return  0;
    }


    // test
    public static void main(String[] args) {

        int[] a = {34,56,67,78,89,90,987,34421,54,31,677,0,22,4};
        XorNumber xorNumber = new XorNumber();
        System.out.println(xorNumber.solve(a, 67));
    }
}

/**
 * 给定整数m以及n各数字A1,A2,…An，将数列A中所有元素两两异或，共能得到n(n-1)/2个结果，请求出这些结果中大于m的有多少个。
 * 1、暴力，O(n^2)
 * 2、字典树
 * znm ps:暂时不考虑负数
 * */
