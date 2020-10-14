package com.leetcode.weekyRun;

import java.util.*;
import java.util.stream.Stream;

class MakeGood {
    public static String makeGood(String s) {
        boolean isChanged = true;
        while (isChanged) {
            isChanged = false;
            char[] chars = s.toCharArray();
            StringBuilder newString = new StringBuilder("");
            int i;
            for (i = 0; i < s.length() - 1; i++) {
                char c;
                if (chars[i] >= 97) c = (char) (chars[i] - 32);
                else c = (char) (chars[i] + 32);
                if (chars[i + 1] == c) {
                    i++;
                    isChanged = true;
                }
                else newString.append(chars[i]);
            }
            if (i == s.length() - 1) newString.append(chars[i]);
            s = newString.toString();
        }
        return s;
    }

    public static char findKthBit(int n, int k) {
        boolean reverse = false;
        while(true) {
            if (k == 1 && !reverse) return '0';
            if (k == 1 && reverse) return '1';
            int length = (int)Math.pow(2, n) - 1;
            int half = length - length / 2;
            if (k == half && !reverse) return '1';
            if (k == half && reverse) return '0';
            if (k > half) {
                k = length - k + 1;
                reverse = !reverse;
            }
            n--;
        }
    }

    public static int maxNonOverlapping(int[] nums, int target) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == target) {
                    count++;
                    sum = 0;
                }
            }
            if (count > max) max = count;
        }
        return max;
    }

    public static int countBinarySubstrings(String s) {
        int count0 = 0;
        int count1 = 0;
        int result = 0;
        char pre = '0';
        for (char c : s.toCharArray()) {
            if (count0 != 0 && count1 != 0 && c != pre) {
                result += Math.min(count0, count1);
                if (c == '0') count0 = 0;
                else count1 = 0;
            }
            if (c == '0') count0++;
            if (c == '1') count1++;
            pre = c;
        }
        result += Math.min(count0, count1);
        return result;
    }

    public int minDays(int n) {
        Map<Integer, Integer> dp = new HashMap<>();
        dp.put(0, 0);
        for (int i = 1; i <= n; i++) {
            int min = dp.get(i - 1);
            if (i % 3 == 0) {
                if (dp.get(i / 3) < min) min = dp.get(i / 3);
            }
            if (i % 2 == 0) {
                if (dp.get(i / 2) < min) min = dp.get(i / 2);
            }
            dp.put(i, min + 1);
        }
        return dp.get(n);
    }

    public String thousandSeparator(int n) {
        String s = String.valueOf(n);
        int count = 3 - s.length() % 3;
        count %= 3;
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (count == 3) {
                sb.append('.');
                count = 0;
            }
            sb.append(c);
            count++;
        }
        return sb.toString();
    }

    public static List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        Set<Integer> in = new HashSet<>();
        for (List<Integer> edge : edges) {
            in.add(edge.get(1));
        }
//        Stream<Integer> stream = Stream.iterate(0, i -> i + 1);
//        Integer[] array = (Integer[]) stream.filter(i -> i < n).filter(i -> !in.contains(i)).toArray();
//        return Arrays.asList(array);
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!in.contains(i)) result.add(i);
        }
        return result;
    }

    public int minOperations(int[] nums) {
        int max = 0;
        int count = 0;
        for (int num : nums) {
            int temp = num;
            int tempCnt = 0;
            while (temp != 0) {
                if (temp % 2 != 0) count++;
                temp /= 2;
                tempCnt++;
            }
            if (tempCnt - 1 > max) max = tempCnt - 1;
        }
        return count + max;
    }

    public static boolean containsCycle(char[][] grid) {
        boolean[][] isVisit = new boolean[grid.length][grid[0].length];
        // 0：上 1：下 2：左 3：右
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (!isVisit[i][j]) {
                    boolean[] canBeVisit = {true, true, true, true};
                    isVisit[i][j] = true;
                    char now = grid[i][j];
                    int i1 = i;
                    int j1 = j;
                    boolean haveNext = false;
                    do {
                        if (canBeVisit[0] && i1 > 0 && grid[i1 - 1][j1] == now) {
                            i1--;
                            canBeVisit = new boolean[]{true, false, true, true};
                            if (isVisit[i1][j1]) return true;
                            isVisit[i1][j1] = true;
                            haveNext = true;
                            continue;
                        }
                        if (canBeVisit[1] && i1 < grid.length - 1 && grid[i1 + 1][j1] == now) {
                            i1++;
                            canBeVisit = new boolean[]{false, true, true, true};
                            if (isVisit[i1][j1]) return true;
                            isVisit[i1][j1] = true;
                            haveNext = true;
                            continue;
                        }
                        if (canBeVisit[2] && j1 > 0 && grid[i1][j1 - 1] == now) {
                            j1--;
                            canBeVisit = new boolean[]{true, true, true, false};
                            if (isVisit[i1][j1]) return true;
                            isVisit[i1][j1] = true;
                            haveNext = true;
                            continue;
                        }
                        if (canBeVisit[3] && j1 < grid[0].length - 1 && grid[i1][j1 + 1] == now) {
                            j1++;
                            canBeVisit = new boolean[]{true, true, false, true};
                            if (isVisit[i1][j1]) return true;
                            isVisit[i1][j1] = true;
                            haveNext = true;
                            continue;
                        }
                        haveNext = false;
                    } while (haveNext);
                }
            }
        }
        return false;
    }

    public static boolean repeatedSubstringPattern(String s) {
        return (s + s).substring(1, s.length() * 2 - 1).indexOf(s) != -1;
    }

    public String shortestPalindrome(String s) {
        char[] chars = s.toCharArray();
        int j = s.length() / 2;
        int i = j - 1;
        if (s.length() % 2 == 1) j++;
        while (i != -1) {
            int ii = i;
            int jj = j;
            while (ii >= 0 && chars[ii] == chars[jj]) {
                ii--;
                jj++;
            }
            if (ii == -1) {
                break;
            }
            if (j == i + 2) j--;
            else i--;
        }
        StringBuilder result = new StringBuilder();
        for (int i1 = chars.length - 1; i1 > j + i; i1--) {
            result.append(chars[i1]);
        }
        result.append(s);
        return result.toString();
    }

    public static boolean containsPattern(int[] arr, int m, int k) {
        int i = 0;
        while (arr.length - i >= m * k) {
            int[] pattern = Arrays.copyOfRange(arr, i, i + m);
            int count = 1;
            while (true) {
                if (equals(Arrays.copyOfRange(arr, i + m *count, i + m * (count + 1)), pattern))
                    count++;
                else break;
            }
            if (count == k) return true;
            i++;
        }
        return false;
    }
    public static boolean equals(int[] a,int[] b)
    {
        for (int i=0;i!=(a.length<b.length?a.length:b.length);i++)
            if (a[i]!=(b[i])) return false;
            return true;
    }

    public static int getMaxLen(int[] nums) {
        int max = 0;
        int i = 0;
        while (i < nums.length) {
            while (i < nums.length && nums[i] == 0) i++;
            int j = i;
            while (j < nums.length && nums[j] != 0) j++;
            int count = getMax(Arrays.copyOfRange(nums, i, j));
            if (count > max) max = count;
            i = j;
        }
        return max;
    }
    private static int getMax(int[] nums) {
        int count1 = 0, count2 = 0;
        boolean flag = false;
        int negCnt = 0;
        int first = -1;
        int end = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                if (!flag) count1++;
                else count2++;
            }
            else {
                negCnt++;
                if(negCnt == 1) first = i;
                end = i;
                if (!flag) {
                    count2 = count1;
                    count2++;
                }
                else {
                    count1 = count2;
                    count1++;
                }
                flag = !flag;
            }
        }
        int max;
        if (count1 > count2) max = count1;
        else {
            max = count1 > count2 - count1 - 1 ? count1 : count2 - count1 - 1;
            if (nums.length - first - 1 > max) max = nums.length - first - 1;
        }
        return max;
    }

    public static void main(String[] args) {
        List<List<Integer>> edges = new ArrayList<>();
        edges.add(Arrays.asList(0,1));
        edges.add(Arrays.asList(0,2));
        edges.add(Arrays.asList(2,5));
        edges.add(Arrays.asList(3,4));
        edges.add(Arrays.asList(4,2));
//        System.out.println(MakeGood.findSmallestSetOfVertices(6, edges));
//        System.out.println(MakeGood.containsCycle(new char[][]{{'b','a','c'},{'c','a','c'},{'d','d','c'},{'b','c','c'}}));
//        System.out.println(MakeGood.repeatedSubstringPattern("abaababaab"));
//        System.out.println(MakeGood.containsPattern(new int[] {1,2,4,4,4,4}, 1, 3));
        System.out.println(MakeGood.getMaxLen(new int[] {5,-20,-20,-39,-5,0,0,0,36,-32,0,-7,-10,-7,21,20,-12,-34,26,2}));
    }
}
