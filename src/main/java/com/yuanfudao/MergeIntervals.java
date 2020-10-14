package com.yuanfudao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class MergeIntervals {

    public int[][] merge(int[][] intervals) {

        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (intervals == null || intervals.length < 2) return intervals;
        int[][] newIntervals = intervals;
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        ArrayList<Integer> pairs = new ArrayList<>();
        pairs.add(newIntervals[0][0]);
        pairs.add(newIntervals[0][1]);
        result.add(pairs);
        for (int i = 1; i < newIntervals.length; i++) {
            ArrayList<Integer> pairTemp = new ArrayList<>();
            int lastEnd = result.get(result.size() - 1).get(1);
            if (newIntervals[i][0] <= lastEnd) {
                if (newIntervals[i][1] > lastEnd)
                    result.get(result.size() - 1).set(1, newIntervals[i][1]);
            } else {
                pairTemp.add(newIntervals[i][0]);
                pairTemp.add(newIntervals[i][1]);
                result.add(pairTemp);
            }
        }

        int[][] resultFormat = new int[result.size()][2];
        int i = 0;
        for (ArrayList<Integer> pair : result) {
            resultFormat[i][0] = pair.get(0);
            resultFormat[i][1] = pair.get(1);
            ++i;
        }
        return resultFormat;
    }
}
