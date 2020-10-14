package com.leetcode.weekyRun;

import com.graph.bean.Trie;

import java.util.*;

public class StringMatching {

    public List<String> stringMatching(String[] words) {

        List<String> result = new ArrayList<String>();
        for (int i = 0; i < words.length - 1; i++) {
            for (int j = i + 1; j < words.length; j++) {
                int loc = -1;
                if (words[j].length() >= words[i].length() && words[j].contains(words[i])){
                    loc = i;
                }
                if (words[i].length() > words[j].length() && words[i].contains(words[j])) {
                    loc = j;
                }
                if (loc != -1 && !result.contains(words[loc])) result.add(words[loc]);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        StringMatching stringMatching = new StringMatching();
        String[] words = {"mass","as","hero","superhero"};
        for (String s : stringMatching.stringMatching(words)) {
            System.out.println(s);
        }
    }
}
