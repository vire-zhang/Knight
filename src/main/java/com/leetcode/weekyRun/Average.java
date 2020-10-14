package com.leetcode.weekyRun;

import java.util.*;

public class Average {

    public double average(int[] salary) {

        int max = 0;
        int min = 1000001;
        int sum = 0;
        for (int i : salary) {
            sum += i;
            if (i > max) max = i;
            if (i < min) min = i;
        }
        return (double)(sum - max - min) / (salary.length - 2);
    }

    public int kthFactor(int n, int k) {

        if (n > 2 && k == n) return -1;
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                ++count;
                if (count == k) return i;
            }
        }
        return -1;
    }

    public int longestSubarray(int[] nums) {

        int max = 0;
        int count = 0;
        int pre = 0;
        boolean zeroFlag = false;
        int i = 0;
        while (i < nums.length && nums[i] == 0) ++i;
        for(;i < nums.length; ++i) {
            if (nums[i] == 1) {
                if (zeroFlag) {
                    zeroFlag = false;
                }
                ++count;
            }
            else {
                if (!zeroFlag) {
                    if ((count + pre) > max) max = count + pre;
                    pre = count;
                    count = 0;
                    zeroFlag = true;
                }
                else pre = 0;
            }
        }

        if ((count + pre) > max) max = count + pre;
        if (max == nums.length) --max;
        return max;
    }

    class Node {
        int val;
        int level = 0;
        List<Node> children = new ArrayList<>();
        List<Node> parents = new ArrayList<>();

        public Node(int val) {
            this.val = val;
        }
    }

    public int minNumberOfSemesters(int n, int[][] dependencies, int k) {

        if (dependencies.length == 0) return (n / k + (n % k == 0 ? 0 : 1));

        Map<Integer, Node> nodes = new HashMap<>();
        for (int[] d : dependencies) {
            Node node0;
            if (!nodes.containsKey(d[0])) {
                node0 = new Node(d[0]);
                nodes.put(d[0], node0);
            } else node0 = nodes.get(d[0]);
            Node node1;
            if (!nodes.containsKey(d[1])) {
                node1 = new Node(d[1]);
                nodes.put(d[1], node1);
            } else node1 = nodes.get(d[1]);
            node1.children.add(node0);
            node0.parents.add(node1);
            if (node0.level != node1.level + 1) {
                Queue<Node> queue = new LinkedList<>();
                Queue<Node> queue2 = new LinkedList<>();
                queue.add(node0);
                int level = node1.level + 1;
                while (!queue.isEmpty()) {
                    Node node = queue.poll();
                    if (node.level < level) node.level = level;
                    for (Node child : node.children) {
                        queue2.add(child);
                    }
                    if (queue.isEmpty()) {
                        queue = queue2;
                        queue2 = new LinkedList<>();
                        ++level;
                    }
                }
            }
        }

        List<Node> canBeLearnNode = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (!nodes.containsKey(i)) nodes.put(i, new Node(i));
        }

        int count = 0;
        do {
            for (Node node : nodes.values()) {
                if (node.children.size() == 0 &&
                        !canBeLearnNode.contains(node)) {
                    canBeLearnNode.add(node);
                }
            }
            Collections.sort(canBeLearnNode, new Comparator<Node>() {
                @Override
                public int compare(Node o1, Node o2) {
                    return o1.level - o2.level;
                }
            });
            int kk = k;
            for (int i = canBeLearnNode.size() - 1; i >= 0 ; i--) {
                if (kk > 0) {
                    Node node = canBeLearnNode.get(i);
                    canBeLearnNode.remove(i);
                    nodes.remove(node.val);
                    for (Node parent : node.parents) {
                        parent.children.remove(node);
                    }
                    --kk;
                } else break;
            }
            ++count;
        } while (nodes.size() != 0);

        return count;
    }

    public static void main(String[] args) {
        Average a = new Average();
        System.out.println(a.longestSubarray(new int[]{1,1,1}));

        a.minNumberOfSemesters(8, new int[][]{{5,1},{3,4},{2,6},{3,6},{5,6},{6,8},{3,8},{5,8},{7,6},{7,4},{1,4},{8,4},{2,8}},2);
    }
}
