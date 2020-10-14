package com.huawei;

import java.util.*;

public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            int n = s.length() - scanner.nextInt();
            Map<Integer, List<Integer>> indexMap = new HashMap<>();
            int[] digits = new int[s.length()];
            processData(indexMap, digits, s);
            System.out.println(calculateResult(indexMap, digits, n));
        }
    }
    private static String calculateResult(Map<Integer, List<Integer>> indexMap, int[] digits, int n) {
        Arrays.sort(digits);
        StringBuilder result = new StringBuilder();
        int count = n;
        int preIndex = -1;
        while (count > 0) {
            for (int digit : digits) {
                List<Integer> list = indexMap.get(digit);
                boolean flag = false;
                for (Integer i : list) {
                    if (i > preIndex && i <= digits.length - count) {
                        result.append(digit);
                        count--;
                        preIndex = i;
                        flag = true;
                        break;
                    }
                }
                if (flag) break;
            }
        }
        return result.toString();
    }
    private static void processData(Map<Integer, List<Integer>> indexMap, int[] digits, String s) {
        for (int i = 0; i < s.length(); i++) {
            int digit = Integer.parseInt(s.substring(i, i + 1));
            digits[i] = digit;
            if (!indexMap.containsKey(digit)) {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                indexMap.put(digit, list);
            } else {
                List<Integer> list = indexMap.get(digit);
                list.add(i);
            }
        }
    }

//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        String num = scanner.nextLine();
//        int n = scanner.nextInt();
//        boolean[] delete = new boolean[num.length()];
//        while (n != 0) {
//            boolean flag = false;
//            for (int i = 0; i < num.length() - 1; i++) {
//                if (delete[i]) continue;
//                int j = i + 1;
//                while (delete[j]) j++;
//                if (Integer.parseInt(num.substring(i, i + 1)) > Integer.parseInt(num.substring(j, j + 1))) {
//                    delete[i] = true;
//                    n--;
//                    break;
//                }
//                flag = true;
//            }
//            if (flag) break;
//        }
//        StringBuilder stringBuilder = new StringBuilder();
//        int count = 0;
//        for (int i = 0; i < num.length(); i++) {
//            if (!delete[i]) {
//                stringBuilder.append(num.toCharArray()[i]);
//                count++;
//            }
//            if (count == num.length() - n) break;
//        }
//        System.out.println(stringBuilder.toString());
//    }
}
