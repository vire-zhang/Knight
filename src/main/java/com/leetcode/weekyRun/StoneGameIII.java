package com.leetcode.weekyRun;

import org.springframework.beans.factory.annotation.Value;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class StoneGameIII {

    public String stoneGameIII(int[] stoneValue) {

        int n = stoneValue.length;
        int[] dp = new int[n + 1];
        dp[n] = 0;

        int sum = 0;
        for (int i = n - 1; i >= 0; i--) {
            sum += stoneValue[i];
            dp[i] = Integer.MIN_VALUE;
            for (int j = i; j < n && j < i + 3; j++) {
                dp[i] = Math.max(dp[i], sum - dp[j + 1]);
            }
        }
        int alice = dp[0];
        int bob = sum - dp[0];
        if (alice == bob) return "Tie";
        if (alice > bob) return "Alice";
        return "Bob";
    }

    /**
     * error
     * @param nums
     * @return
     */
    public static boolean PredictTheWinner(int[] nums) {
        if (nums.length % 2 == 0) return true;
        int oddSum = 0;
        int evenSum = 0;
        for (int i = 1; i < nums.length; i++) {
            if (i % 2 == 1) oddSum += nums[i];
            else evenSum += nums[i];
        }
        int min = oddSum > evenSum ? evenSum : oddSum;
        int max = oddSum < evenSum ? evenSum : oddSum;
        if (min + nums[0] >= max) return true;
        evenSum = evenSum - nums[nums.length - 1] + nums[0];
        min = oddSum > evenSum ? evenSum : oddSum;
        max = oddSum < evenSum ? evenSum : oddSum;
        if (min + nums[nums.length - 1] >= max) return true;
        return false;
    }

    public static boolean isNumber(String s) {
        float f = Float.parseFloat(s);
        s = s.trim();
        if (s.length() == 0) return false;
        char[] chars = s.toCharArray();
        boolean e = false;
        boolean negative = false;
        boolean dot = false;
        for (int i = 0; i < chars.length; i++) {
            if (Character.isDigit(chars[i])) continue;
            if ((chars[i] == '+' || chars[i] == '-') && i == 0 && s.length()!= 1) continue;
            if (chars[i] == '.' && i == 0 && s.length()!= 1) {
                dot = true;
                continue;
            }
            if (chars[i] == '.' && i == 1 && s.length()!= 2) {
                dot = true;
                continue;
            }
            if (i != 0) {
                if (chars[i] == 'e' || chars[i] == 'E') {
                    if (e) return false;
                    if (!Character.isDigit(chars[i - 1])) return false;
                    if (i == s.length() - 1) return false;
                    e = true;
                    continue;
                }
                if (chars[i] == '-') {
                    if (negative) return false;
                    if (chars[i - 1] != 'e' && chars[i - 1] != 'E') return false;
                    if (i == s.length() - 1) return false;
                    negative = true;
                    continue;
                }
                if (chars[i] == '.') {
                    if (dot) return false;
                    if (!Character.isDigit(chars[i - 1])) return false;
                    dot = true;
                    continue;
                }
            }
//            if (chars[i] == ' ' && (i == 0 || i == s.length() - 1) && s.length() != 1) continue;
            return false;
        }
        return true;
    }

    /**
     * N皇后问题
     * @param n
     * @return
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        // 初始化false，即false时可放入皇后或已放入皇后
        boolean[][] canVisit = new boolean[n][n];
        Stack<Pair> locate = new Stack<>();
        locate.push(new Pair(0, 0));
        // 保存选这个点放皇后之前点矩阵状态
        Stack<boolean[][]> state = new Stack<>();
        state.push(deepCopy(canVisit));
        while (!locate.isEmpty()) {
            int i = locate.peek().i;
            int j = locate.peek().j;
            change(canVisit, i, j);
            i++;
            while (hasNextLine(canVisit, i, state, locate)) i++;
            if (i == n) {
                List<String> list = new ArrayList<>();
                for (boolean[] row : canVisit) {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (boolean b : row) {
                        if(b) stringBuilder.append('.');
                        else stringBuilder.append('Q');
                    }
                    list.add(stringBuilder.toString());
                }
                result.add(list);
            }
            Pair pair = locate.pop();
            i = pair.i;
            j = pair.j;
            canVisit = state.pop();
            while (!hasNext(canVisit, i, j, state, locate)) {
                if (locate.isEmpty()) break;
                pair = locate.pop();
                i = pair.i;
                j = pair.j;
                canVisit = state.pop();
            }
        }
        return result;
    }
    private boolean[][] deepCopy(boolean[][] arr) {
        boolean[][] newarr = new boolean[arr.length][arr.length];
        int i = 0;
        for (boolean[] row : arr)
            newarr[i++] = row.clone();
        return newarr;
    }
    private boolean hasNext(boolean[][] canVisit, int i, int j, Stack<boolean[][]> state, Stack<Pair> locate) {
        for (int k = j + 1; k < canVisit[i].length; k++) {
            if (!canVisit[i][k]) {
                locate.push(new Pair(i, k));
                state.push(deepCopy(canVisit));
                return true;
            }
        }
        return false;
    }
    private boolean hasNextLine(boolean[][] canVisit, int i, Stack<boolean[][]> state, Stack<Pair> locate) {
        if (i >= canVisit.length) return false;
        for (int j = 0; j < canVisit[i].length; j++) {
            if (!canVisit[i][j]) {
                locate.push(new Pair(i, j));
                state.push(deepCopy(canVisit));
                change(canVisit, i, j);
                return true;
            }
        }
        return false;
    }
    private class Pair{
        int i;
        int j;
        Pair (int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
    private void change(boolean[][] canVisit, int x, int y) {
        // 行
        for (int j = 0; j < canVisit.length; j++) canVisit[x][j] = true;
        // 列
        for (int i = x + 1; i < canVisit.length; i++) canVisit[i][y] = true;
        // 左下斜
        int i = x + 1;
        int j = y - 1;
        while (i < canVisit.length && j >= 0) {
            canVisit[i][j] = true;
            i++;
            j--;
        }
        // 右下斜
        i = x + 1;
        j = y + 1;
        while (i < canVisit.length && j < canVisit.length) {
            canVisit[i][j] = true;
            i++;
            j++;
        }
        canVisit[x][y] = false;
    }

    public static String modifyString(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '?') {
                char a;
                if (i == 0) a = 'a';
                else a = (char)((chars[i - 1] - 'a' + 1) % 26 + 'a');
                if (i != chars.length - 1 && a == chars[i + 1])
                    a = (char)((a - 'a' + 1) % 26 + 'a');
                chars[i] = a;
            }
        }
        return new String(chars);
    }

    public String getPermutation(int n, int k) {
        Map<Integer, Integer> factorial = new HashMap<>();
        factorial.put(0, 1);
        Map<Integer, Integer> num = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            factorial.put(i, factorial.get(i - 1) * i);
            num.put(i, i);
        }
        StringBuilder result = new StringBuilder();
        for (int i = n; i > 0; i--) {
            if (i == 1) {
                result.append(num.get(1));
                break;
            }
            if (k == 0) {
                for (int j = num.size(); j > 0; j--) {
                    result.append(num.get(j));
                }
                break;
            }
            int a = k / (factorial.get(i) / i);
            int b = k % (factorial.get(i) / i);
            if (b != 0) a++;
            result.append(num.get(a));
            delet(num, a);
            k = b;
        }
        return result.toString();
    }
    private void delet(Map<Integer, Integer> num, int k) {
        int i;
        for (i = k; i < num.size(); i++) {
            num.replace(i, num.get(i + 1));
        }
        num.remove(i);
    }

    // todo
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.merge(num, 1, Integer::sum);
        }

        List<Map.Entry<Integer, Integer>> list = new ArrayList<>();
        Collections.sort(list,new Comparator<Map.Entry<Integer, Integer>>() {
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });
        return nums;
    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        int j = 1;
        while (true) {
            while (list.size() < k) {
                list.add(j);
                j++;
            }
            result.add(new ArrayList<>(list));
            int m = list.size() - 1;
            while (m >= 0 && list.get(m) == n - (k - m - 1)) {
                list.remove(m);
                m--;
            }
            if (m < 0) break;
            list.set(list.size() - 1, list.get(list.size() - 1) + 1);
            j = list.get(list.size() - 1) + 1;
        }
        return result;
    }

    public static void main(String[] args) {
//        System.out.println(StoneGameIII.PredictTheWinner(new int[] {0,0,7,6,5,6,1}));
//        System.out.println(StoneGameIII.isNumber("+.8"));
        StoneGameIII s = new StoneGameIII();
//        System.out.println(s.solveNQueens(100).size());
//        System.out.println(s.getPermutation(3, 2));
//        System.out.println(s.combine(4, 3));
//        Scanner scanner = new Scanner(System.in);
//        String s1 = scanner.nextLine();
//        char c = scanner.nextLine().charAt(0);
//        char c1 = 'a';
//        System.out.println(c1 == 'a');
//        int[] a = new int[] {1,2};
//        while (scanner.hasNextInt());
//        Set<Integer> set = new HashSet<>();
//        List<List<Integer>> list = new ArrayList<>();
//        list.add(Arrays.asList(a[0], a[1]);
        int[] a = s.findOrder(2, new int[][]{{1,0}});
        for (int i : a) {
            System.out.println(i);
        }

    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> lists = new ArrayList<>();
        for (int[] prerequisite : prerequisites) {
            lists.add(Arrays.asList(prerequisite[0], prerequisite[1]));
        }
        List<Integer> result = new ArrayList<>();
        while (lists.size() != 0) {
            HashSet<Integer> higher = new HashSet<>();
            for (List<Integer> list : lists) higher.add(list.get(0));
            boolean flag = false;
            for (int i = 0; i < numCourses; i++) {
                if (!higher.contains(i) && !result.contains(i)) {
                    result.add(i);
                    flag = true;
                    for (int j = lists.size() - 1; j >= 0; j--)
                        if (lists.get(j).get(1) == i) lists.remove(j);
                }
            }
            if (!flag) return new int[]{};
        }
        for (int i = 0; i < numCourses; i++) {
            if (!result.contains(i)) result.add(i);
        }
        int[] resultArray = new int[result.size()];
        for(int i = 0; i < result.size(); i++) resultArray[i] = result.get(i);
        return resultArray;
    }
}
