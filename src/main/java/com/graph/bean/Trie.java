package com.graph.bean;

public class Trie {

    private boolean isString = false;
    private Trie[] next = new Trie[26];

    /** Initialize your data structure here. */
    public Trie() {}

    /** Inserts a word into the trie. */
    public void insert(String word) {
        char[] chars = word.toCharArray();
        Trie root = this;
        for (char c : chars) {
            if (root.next[c - 'a'] == null) {
                root.next[c - 'a'] = new Trie();
            }
            root = root.next[c - 'a'];
        }
        root.isString = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        char[] chars = word.toCharArray();
        Trie root = this;
        for (char c : chars) {
            if (root.next[c - 'a'] == null) return false;
            root = root.next[c - 'a'];
        }
        if (!root.isString) return false;
        return true;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        char[] chars = prefix.toCharArray();
        Trie root = this;
        for (char c : chars) {
            if (root.next[c - 'a'] == null) return false;
            root = root.next[c - 'a'];
        }
        return true;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("love");
        System.out.println(trie.search("love"));
        System.out.println(trie.search("lov"));
        System.out.println(trie.startsWith("lov"));
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
