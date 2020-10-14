package com.interview;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Didi {
    public List<String> findRepeat(String a, int b) {
        Set<String> strings = new HashSet<>();
        List<String> result = new ArrayList<>();
        for (int i = 0; i <= a.length() - b; i++) {
            String tmp = a.substring(i, i + b);
            if (strings.contains(tmp)) result.add(tmp);
            else strings.add(tmp);
        }
        return result;
    }

    public static void main(String[] args) {
        Didi d = new Didi();
        System.out.println(d.findRepeat("zxcvbnmzxcv", 4));
    }
}
