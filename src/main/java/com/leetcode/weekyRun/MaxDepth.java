package com.leetcode.weekyRun;

import java.util.*;
import java.util.stream.Collectors;

public class MaxDepth {

    public int maxDepth(String s) {
        char[] chars = s.toCharArray();
        int count = 0;
        int max = 0;
        for (char c : chars) {
            if (c == '(') {
                count++;
                if (count > max) max = count;
            }
            if (c == ')') {
                count--;
            }
        }
        return max;
    }

    public int maximalNetworkRank(int n, int[][] roads) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(i, new ArrayList<>());
        }
        for (int[] road : roads) {
            map.get(road[0]).add(road[1]);
            map.get(road[1]).add(road[0]);
        }
        List<Map.Entry<Integer, List<Integer>>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, List<Integer>>>() {
            @Override
            public int compare(Map.Entry<Integer, List<Integer>> o1, Map.Entry<Integer, List<Integer>> o2) {
                return o2.getValue().size() - o1.getValue().size();
            }
        });
        int max = 0;
        for (int i = 0; i < n - 1; i++) {
            boolean breakFlag = false;
            for (int j = i + 1; j < n; j++) {
                int node1 = list.get(i).getKey();
                int node2 = list.get(j).getKey();
                int count = map.get(node1).size() + map.get(node2).size();
                if (count < max) {
                    breakFlag = true;
                    break;
                }
                if (map.get(node1).contains(node2)) {
                    count--;
                }
                if (max < count) {
                    max = count;
                }
            }
            if (breakFlag) {
                break;
            }
        }
        return max;
    }

    public boolean checkPalindromeFormation(String a, String b) {
        if (isPalindrome(a) || isPalindrome(b)) {
            return true;
        }
        if (a.charAt(0) == b.charAt(b.length() - 1) ||
            b.charAt(0) == a.charAt(a.length() - 1)) {

            return true;
        }
        return false;
    }
    private boolean isPalindrome(String s) {
        if (s.length() == 1) return true;
        int i = 0;
        int j = s.length() -1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public int[] countSubgraphsForEachDiameter(int n, int[][] edges) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(i, new ArrayList<>());
        }
        for (int[] road : edges) {
            map.get(road[0]).add(road[1]);
            map.get(road[1]).add(road[0]);
        }
        for (int count = 2; count <= n; count++) {

        }

        return new int[]{};
    }

    public static void main(String[] args) {
        MaxDepth maxDepth = new MaxDepth();
        System.out.println(maxDepth.checkPalindromeFormation("kifxqstikwuvjswfcumcplmygcvazemuzhzvgyzwkvtmbuqjzfvwmoweviiwkwwpudwajytkthypwsnkgrarjaauixuebatlcbyztjknbpkofryskoucypgryicbuvtimutwzkntozczdmazszlwljjckesbwqmetcgiextuynxfgarnojkxdbxgobxmiimbbwtifdgvzyedevrsajxnslkantuucyodzhmqwkflhxxkywoeiwuujaqbyubisyrhvlxhbyewemzehvihsyhpaakxxzbksheraqiidlsgniomskpudqlyqgifhmcrktzcrwomiyncupzuwgzakwxujluvxfawzblrpgdtghnbydhullwzbumansotntuoudwkk",
                "rxahzvzlpzrpslvccifmugezbzbhamavatrmqnnvyhygpxhfmrnnsailumhjamswjdcnjysuebzhzqkgulzoqayhloqygazwchfifsbxvyhxkvsfcxtydrtdocyqlmutljvthkfuowymlhuvtlzredqybymdnzvptpgnvajyysibqgtvzglnhvdkagoijslcqclsjiogakdvhnlgzvtgqbisyyjavngptpvzndmybyqderzltvuhlmywoufkhtvjltumlqycodtrdytxcfsvkxhyvxbsfifhcwzagyqolhyaqozlugkqzhzbeusyjncdjwsmajhmuliasnnrmfhxpgyhyvnnqmrtavamahbzbzegumficcvlsprzplzvqxfik"));
    }
}
