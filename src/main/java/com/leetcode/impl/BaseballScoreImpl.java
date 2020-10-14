package com.leetcode.impl;

import com.leetcode.BaseballScore;
import com.util.Calculate;

import java.util.ArrayList;
import java.util.Stack;

public class BaseballScoreImpl implements BaseballScore {

    @Override
    public int callPoints(String[] ops) {
        int result = 0;
        ArrayList<Integer> validScores = new ArrayList<Integer>();
        for (String op : ops) {
            switch (op) {
                case "+":
                    validScores.add(validScores.get(validScores.size() - 1) + validScores.get(validScores.size() - 2));
                    break;
                case "D":
                    validScores.add(validScores.get(validScores.size() - 1) * 2);
                    break;
                case "C":
                    validScores.remove(validScores.size() - 1);
                    break;
                default:
                    validScores.add(Integer.parseInt(op));
                    break;
            }
        }
        result = Calculate.sumIntegerArray(validScores);
//        for (Integer score : validScores) {
//            result += score;
//        }
        return result;
    }
}
