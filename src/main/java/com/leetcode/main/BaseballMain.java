package com.leetcode.main;

import com.leetcode.BaseballScore;
import com.leetcode.impl.BaseballScoreImpl;

public class BaseballMain {

    public static void main(String[] args) {
        String[] ops = {"5","-2","4","C","D","9","+","+"};
        BaseballScore baseballScore = new BaseballScoreImpl();
        int result = baseballScore.callPoints(ops);
        System.out.println(result);
    }
}
