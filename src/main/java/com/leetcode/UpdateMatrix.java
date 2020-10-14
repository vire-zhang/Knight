package com.leetcode;

import com.bytedance.LinkedList;
import com.bytedance.XorNumber;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Character.isDigit;

public class UpdateMatrix {
    public int[][] updateMatrix(int[][] matrix) {

        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[0].length; ++j) {
                if (matrix[i][j] == 0) {
                    continue;
                }
                int minDis = Integer.MIN_VALUE;
                if (j > 0) minDis = matrix[i][j - 1] + 1;
                if (i > 0 && matrix[i - 1][j] < minDis - 1)
                    minDis = matrix[i - 1][j] + 1;
            }
        }

        return matrix;
    }

    private class Pair{
        int i;
        int j;
        Pair (int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public int numWays(int n, int[][] relation, int k) {

        int turn = 0, result = 0;
        int[] turnCount = new int[k];
        for (int i = 0; i < k; i++) {
            turnCount[i] = 0;
        }
        Stack<Pair> pairStack = new Stack<Pair>();
        for (int i = 0; i < relation.length; ++i) {
            if (relation[i][0] == 0) {
                pairStack.push(new Pair(relation[i][0], relation[i][1]));
                ++turnCount[turn];
            }
        }
        while (!pairStack.empty()) {
            if (turn + 1 == k) {
                for (int i = 0; i < turnCount[turn]; i++) {
                    Pair pair = pairStack.pop();
                    if (pair.j == n - 1) ++result;
                }
                turnCount[turn] = 0;
                while (turn >= 0 && turnCount[turn] == 0) --turn;
                continue;
            }
            Pair pair = pairStack.pop();
            --turnCount[turn];
            ++turn;
            for (int i = 0; i < relation.length; ++i) {
                if (relation[i][0] == pair.j) {
                    pairStack.push(new Pair(relation[i][0], relation[i][1]));
                    ++turnCount[turn];
                }
            }
            while (turn >= 0 && turnCount[turn] == 0) --turn;
        }
        return result;
    }

//    public int[] getTriggerTime(int[][] increase, int[][] requirements) {
//
//        int[] time = new int[requirements.length];
//        ArrayList<Integer> requireNo = new ArrayList<Integer>();
//        for (int i = 0; i < time.length; i++) {
//            time[i] = -1;
//            requireNo.add(i);
//        }
//        int a = 0, b = 0, c = 0;
//        for (int i = 0; i < increase.length; i++) {
//            for (int j = requireNo.size() - 1; j >= 0; j--) {
//                int loc = requireNo.get(j);
//                if (a >= requirements[loc][0] && b >= requirements[loc][1] && c >= requirements[loc][2]) {
//                    time[loc] = i;
//                    requireNo.remove(j);
//                }
//            }
//            a += increase[i][0];
//            b += increase[i][1];
//            c += increase[i][2];
//        }
//        for (int j = requireNo.size() - 1; j >= 0; j--) {
//            int loc = requireNo.get(j);
//            if (a >= requirements[loc][0] && b >= requirements[loc][1] && c >= requirements[loc][2]) {
//                time[loc] = increase.length;
//                requireNo.remove(j);
//            }
//        }
//        return time;
//    }

//    public int[] getTriggerTime(int[][] increase, int[][] requirements) {
//
//        int[] time = new int[requirements.length];
//        ArrayList<Integer> requireNo = new ArrayList<Integer>();
//        for (int i = 0; i < time.length; i++) {
//            time[i] = -1;
//            requireNo.add(i);
//        }
//        ArrayList<ArrayList<Integer>> req = new ArrayList<ArrayList<Integer>>();
//        for (int i = 0; i < requirements.length; i++) {
//            ArrayList<Integer> temp = new ArrayList<Integer>();
//            temp.add(requirements[i][0]);
//            temp.add(requirements[i][1]);
//            temp.add(requirements[i][2]);
//            temp.add(i);
//            req.add(temp);
//        }
//        int a = 0, b = 0, c = 0;
//        for (int i = 0; i < increase.length + 1; i++) {
//            int l = a, m = b, n = c;
//            List<ArrayList<Integer>> ss = req.stream().filter(r -> r.get(0) <= l && r.get(1) <= m && r.get(2) <= n).parallel().collect(Collectors.toList());
//            for (ArrayList<Integer> temp : ss) {
//                time[temp.get(3)] = i;
//            }
//            req = (ArrayList<ArrayList<Integer>>) req.stream().filter(r -> r.get(0) > l || r.get(1) > m || r.get(2) > n).parallel().collect(Collectors.toList());
//            if (i < increase.length) {
//                a += increase[i][0];
//                b += increase[i][1];
//                c += increase[i][2];
//            }
//        }
//        return time;
//    }

    private class NumTrie{

        NumTrie[] next = new NumTrie[2];
        int count = 1;

        public void addNum(int num) {
            NumTrie trie = this;
            for (int i = 31; i >= 0; i--) {
                int digit = (num >> i) & 1;
                if (trie.next[digit] == null) trie.next[digit] = new NumTrie();
                else ++trie.next[digit].count;
                trie = trie.next[digit];
            }
        }

        public int smallerCnt(int num) {
            NumTrie trie = this;
            int cnt = 0;
            for (int i = 31; i >= 0; i--) {
                int digit = (num >> i) & 1;
                if (digit == 1 && trie.next[0] != null) cnt += trie.next[0].count;
                if (trie.next[digit] != null) {
                    trie = trie.next[digit];
                } else break;
            }
            return cnt;
        }
    }

    public int[] getTriggerTime(int[][] increase, int[][] requirements) {

        int[] time = new int[requirements.length];
        for (int i = 0; i < time.length; i++) time[i] = -1;

        NumTrie trieA = new NumTrie(), trieB = new NumTrie(), trieC = new NumTrie();
        int a = 0, b = 0, c = 0;
        for (int i = 0; i < increase.length; i++) {
            a += increase[i][0];
            b += increase[i][1];
            c += increase[i][2];
            trieA.addNum(a);
            trieB.addNum(b);
            trieC.addNum(c);
        }

        for (int i = 0; i < requirements.length; i++) {
            if (requirements[i][0] == 0 && requirements[i][1] == 0 && requirements[i][2] == 0) {
                time[i] = 0;
                continue;
            }
            int cntA = trieA.smallerCnt(requirements[i][0]);
            int cntB = trieB.smallerCnt(requirements[i][1]);
            int cntC = trieC.smallerCnt(requirements[i][2]);
            int cnt = Math.max(Math.max(cntA, cntB), cntC);
            if (cnt < increase.length) time[i] = cnt + 1;
        }

        return time;
    }

    /**
     * 27 / 41 个通过测试用例
     * LCP 09. 最小跳跃次数
     * @param jump
     * @return
     */
    public int minJump(int[] jump) {
        if (jump[0] > jump.length - 1) return 1;
        int count = 2;
        int pre = 0, loc = jump[0];
        while(true) {
            if ((jump[loc] + loc) > jump.length - 1) return count;
            int max = 0;
            for (int i = pre + 1; i < loc; ++i) {
                if (i + jump[i] > jump.length - 1) {
                    if (loc != pre + jump[pre] && i < pre + jump[pre])
                        return count;
                    return (++count);
                }
                if (i + jump[i] > max) max = i + jump[i];
            }
            for (int i = pre + 1; i < loc; ++i) {
                if (i + jump[i] + jump[i + jump[i]] > jump.length - 1) max = i + jump[i];
            }
            pre = loc;
            int temp = loc + jump[loc] + jump[loc + jump[loc]];
            if (max > temp) {
                loc = max;
                count += 2;
            } else {
                loc += jump[loc];
                ++count;
            }
        }
    }

    public String getHappyString(int n, int k) {
        int[] res = new int[n];
        for (int i = 0; i < n; i += 2) res[i] = 0;
        for (int i = 1; i < n; i += 2) res[i] = 1;
        int m;
        for (m = 1; m < k; ++m) {
            // 三进制数自增1
            boolean flag = thirdCarryPlus(res);
            if (!flag) return "";
            // 判断是否为快乐数
            while(!isHappy(res)){
                flag = thirdCarryPlus(res);
                if (!flag) return "";
            }
        }
        StringBuilder s = new StringBuilder("");
        for (int i = n - 1; i >= 0; --i) {
            switch(res[i]) {
                case 0:
                    s.insert(0, 'a');
                    break;
                case 1:
                    s.insert(0, 'b');
                    break;
                case 2:
                    s.insert(0, 'c');
                    break;
                default:
                    break;
            }
        }
        return s.toString();
    }

    private boolean thirdCarryPlus(int[] res) {
        int j = res.length - 1;
        while (j >= 0) {
            ++res[j];
            if (res[j] > 2) {
                res[j] = 0;
                --j;
                continue;
            } else break;
        }
        if (j < 0) return false;
        return true;
    }

    private boolean isHappy(int[] res){
        for (int i = 1; i < res.length; i++) {
            if (res[i] == res[i - 1]) return false;
        }
        return true;
    }

    public String reformat(String s) {
        if (s.length() == 1) return s;
        char[] chars = new char[s.length()];
        int i = 0, j = 1;
        char res = 'A';
        for (char c : s.toCharArray()) {
            if (isDigit(c)) {
                if (i >= s.length()) return "";
                chars[i] = c;
                i += 2;
            } else {
                if (j >= s.length()) {
                    if (res != 'A') return "";
                    res = c;
                } else {
                    chars[j] = c;
                    j += 2;
                }
            }
        }
        String sb = new String(chars);
        if (res == 'A') return sb;
        if (j - i > 2) return "";
        return res+sb.substring(0,sb.length()-1);
    }

    public List<List<String>> displayTable(List<List<String>> orders) {
        List<String> foodNames = new ArrayList<String>();
        List<Integer> tables = new ArrayList<Integer>();
        Map<String, Map<String, Integer>> tableOrder = new HashMap<>();
        for (List<String> order : orders) {
            String table = order.get(1);
            String food = order.get(2);
            if(!tables.contains(Integer.parseInt(table))) tables.add(Integer.parseInt(table));
            if(!foodNames.contains(food)) foodNames.add(food);
            if (tableOrder.containsKey(table)) {
                Map<String, Integer> foodMap = tableOrder.get(table);
                if (foodMap.containsKey(food)) {
                    int count = foodMap.get(food);
                    ++count;
                    foodMap.replace(food, count);
                } else {
                    foodMap.put(food, 1);
                }
            } else {
                Map<String, Integer> foodMap = new HashMap<>();
                foodMap.put(food, 1);
                tableOrder.put(table, foodMap);
            }
        }
        Collections.sort(foodNames);
        foodNames.add(0, "Table");
        List<List<String>> result = new ArrayList<List<String>>();
        result.add(foodNames);
        Collections.sort(tables);
        for (Integer tableNo : tables) {
            String table = tableNo.toString();
            List<String> tableFood = new ArrayList<>();
            tableFood.add(table);
            Map<String, Integer> foodCountMap = tableOrder.get(table);
            for (int i = 1; i < foodNames.size(); i++) {
                if (!foodCountMap.containsKey(foodNames.get(i)))
                    tableFood.add("0");
                else
                    tableFood.add(foodCountMap.get(foodNames.get(i)).toString());
            }
            result.add(tableFood);
        }
        return result;
    }

    public int minNumberOfFrogs(String croakOfFrogs) {
        int c = 0, r = 0, o = 0, a = 0, k = 0, ans = 0;
        for (char x : croakOfFrogs.toCharArray()) {
            if (x == 'c') ++c;
            else if (x == 'r') ++r;
            else if (x == 'o') ++o;
            else if (x == 'a') ++a;
            else if (x == 'k') ++k;
            if (r > c || o > r || a > o || k > a) return -1;
            ans = Math.max(ans, c);
            if (k == 1) {
                --c;
                --r;
                --o;
                --a;
                --k;
            }
        }
        if (c != 0) return -1;
        return ans;
    }

    public static void main(String[] args) {
        UpdateMatrix test = new UpdateMatrix();
//        int[][] relation = {{0,2},{2,1},{3,4},{2,3},{1,4},{2,0},{0,4}};
//        int[][] relation = {{0,2},{2,1}};
//        System.out.println(test.numWays(5, relation, 5));

//        int[][] inc = {{2,8,4},{2,5,0},{10,9,8}};
//        int[][] req = {{2,11,3},{15,10,7},{9,17,12},{8,1,14}};
//        for (int i : test.getTriggerTime(inc, req)) {
//            System.out.println(i);
//        }

//        int[] jump = {3,7,6,1,4,3,7,8,1,2,8,5,9,8,3,2,7,5,1,1};
//        System.out.println(test.minJump(jump));

//        System.out.println(test.getHappyString(10, 100));

        System.out.println(test.reformat("aa"));
    }
}
