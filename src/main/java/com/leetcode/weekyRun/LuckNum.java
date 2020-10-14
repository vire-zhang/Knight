package com.leetcode.weekyRun;

import javax.swing.*;

public class LuckNum {

    public int findLucky(int[] arr) {

        // 排序
        int[] orderArr = sort(arr);
        int lucky = -1;
        int count = 1;
        int num = arr[0];
        for (int i = 1; i < orderArr.length; i++) {
            if (orderArr[i] != num) {
                if (count == num && lucky < num) lucky =  num;
                count = 1;
                num = orderArr[i];
            } else {
                ++count;
            }
        }
        if (count == num && lucky < num) lucky =  num;
        return lucky;
    }

    private int[] sort(int[] arr) {
        int[] res = arr;
        int temp;
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res.length - 1; j++) {
                if (res[j] > res[j + 1]) {
                    temp = res[j];
                    res[j] = res[j + 1];
                    res[j + 1] = temp;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {4,3,2,2,4,1,3,4,3};
        LuckNum luckNum = new LuckNum();
        int lucky = luckNum.findLucky(arr);
        System.out.println(lucky);
    }
}
