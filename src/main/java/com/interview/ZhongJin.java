package com.interview;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ZhongJin {
    public int quChong(int[] arr) {
        if (arr.length <= 1) return arr.length;
        int result = 0;//非重复数字个数
        int i = 1;
        for (int j = 1; j < arr.length; j++) {
            if (arr[j] != arr[j - 1]) {
                result++;
                arr[i++] = arr[j];
            }
        }
        result++;
        arr[i] = arr[arr.length - 1];
        return result;
    }

    public static void main(String[] args) {
        ZhongJin z = new ZhongJin();
        System.out.println(z.quChong(new int[] {1,1,3,3,4,4,5,5,5}));
        System.out.println(z.maxUniqueSubString("abcabcbb"));
    }

    public int maxUniqueSubString(String s) {
        char[] chars = s.toCharArray();
        int max = 0;
        int count = 0;
        Set<Character> set = new HashSet<>();
        for (char c : chars) {
            if (set.contains(c)) {
                if (max < count) max = count;
                count = 1;
                set = new HashSet<>();
                set.add(c);
            }
            else {
                count++;
                set.add(c);
            }
        }
        if (count > max) max = count;
        return max;
    }

    public void ss() {
        int[] drinks = new int[]{1,2,3,};
        int[] staple = drinks;
        int x = 2;
        long count = 0;
        int tj = drinks.length-1;
        Arrays.sort(staple);
        Arrays.sort(drinks);
        for(int i=0;i<staple.length;i++){
            int j=tj;
            while(j>=0&&staple[i]+drinks[j]>x){
                j--;
            }
            count=count+j+1;
            tj=j;
        }


        Arrays.sort(staple);
        Arrays.sort(drinks);
        for(int i=0;i<staple.length;i++){
            int j;
            for(j=tj;j>=0;j--){
                if(staple[i]+drinks[j]<=x) break;
            }
            count=count+j+1;
            tj=j;
        }
    }
}
