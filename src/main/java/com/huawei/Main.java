package com.huawei;

import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int c = scanner.nextInt();
        int b = scanner.nextInt();
        int[] uncheckData = new int[10];
        for (int i = 0; i < 10; i++) {
            uncheckData[i] = scanner.nextInt();
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : uncheckData) {
            int res = 0;
            if (num < 0) {
                res = negAdder(num);
            } else {
                while (num != 0) {
                    res += num % 16;
                    num = num / 16;
                }
            }
            num = res % b;
            if (num < c) map.merge(num, 1, Integer::sum);
        }
        int max = 0;
        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            if (e.getValue() > max) max = e.getValue();
        }
        System.out.println(max);
    }
    private static int negAdder(int num) {
        char[] chars = Integer.toBinaryString(num).toCharArray();
        int count = 0;
        int res = 0;
        for (int i = chars.length - 1; i >= 0; i--) {
            if (chars[i] == '1') {
                switch (count) {
                    case 0:
                        res += 1;
                        break;
                    case 1:
                        res += 2;
                        break;
                    case 2:
                        res += 4;
                        break;
                    case 3:
                        res += 8;
                        break;
                    default:
                        break;
                }
            }
            count++;
            count %= 4;
        }
        return res;
    }
}
