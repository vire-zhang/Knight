package com.leetcode.weekyRun;

import java.util.*;

public class ChaseGame {
    public static void main(String[] args) {
        ChaseGame chaseGame = new ChaseGame();
        System.out.println(chaseGame.chaseGame(new int[][]{{1,2},{2,3},{3,1},{3,6},{2,4},{4,5},{5,8},{4,7}}, 6, 7));
    }

    public int chaseGame(int[][] edges, int startA, int startB) {
        Map<Integer, Node> graph = creatGraph(edges);
        // 找到图里的圈
        List<Integer> circle = findCircle(graph);
        if (circle.contains(startB)) {
            if (graph.get(startB).next.contains(graph.get(startA))) return 1;
            else return -1;
        }
        return distanceAB(startA, startB, circle, graph);
    }
    private int distanceAB(int startA, int startB, List<Integer> circle, Map<Integer, Node> graph) {
        List<Integer> roadB = roadToCircleEntry(startB, circle, graph);
        int distanceB = roadB.size() - 1;
        int distanceA;
        if (!circle.contains(startA)) {
            // A不在圈上
            List<Integer> roadA = roadToCircleEntry(startA, circle, graph);
            distanceA = roadA.size() - 1;
            if (roadA.get(roadA.size() - 1) != roadB.get(roadB.size() - 1)) {
                // 不在一个分支
                int tmp = Math.abs(circle.indexOf(roadA.get(roadA.size() - 1)) - circle.indexOf(roadB.get(roadB.size() - 1)));
                tmp = circle.size() - tmp > tmp ? tmp : circle.size() - tmp;
                distanceA += tmp;
                if (distanceA - distanceB > 1) return -1;
                else {
                    int distanceBEnd = distanceToEnd(graph, circle, startB);
                    return distanceA + distanceB + distanceBEnd;
                }
            } else {
                // 在一个分支
                if (distanceA - distanceB > 1) return -1;
                if (Math.abs(distanceA - distanceB) == 1) return 1;
                return distanceToEnd(graph, circle, startA);
            }
        }
        else {
            // A在圈上
            int tmp = Math.abs(circle.indexOf(startA - circle.indexOf(roadB.get(roadB.size() - 1))));
            tmp = circle.size() - tmp > tmp ? tmp : circle.size() - tmp;
            distanceA = tmp;
            if (distanceA - distanceB > 1) return -1;
            return distanceA + distanceB + distanceToEnd(graph, circle, startB);
        }
    }
    private int distanceToEnd(Map<Integer, Node> graph, List<Integer> circle, int start) {
        if (graph.get(start).next.size() == 1) return 0;
        List<Integer> visited = new ArrayList<>();
        List<Integer> road = new ArrayList<>();
        int max = 0;
        visited.add(start);
        road.add(start);
        while (true) {
            boolean breakFlag = false;
            boolean calcuFlag = true;
            List<Node> nodes = graph.get(road.get(road.size() - 1)).next;
            for (int i = 0; i < nodes.size(); i++) {
                if (!visited.contains(nodes.get(i).val)) {
                    visited.add(nodes.get(i).val);
                    road.add(nodes.get(i).val);
                    calcuFlag = false;
                    if (circle.contains(nodes.get(i).val)) {
                        road = new ArrayList<>();
                        road.add(start);
                        break;
                    }
                }
                if (i == nodes.size() - 1 && calcuFlag) {
                    if (road.size() - 1 > max) max = road.size() - 1;
                    if (road.size() == 1) {
                        breakFlag = true;
                        break;
                    } else {
                        road = new ArrayList<>();
                        road.add(start);
                    }
                }
            }
            if (breakFlag) break;
        }
        return max;
    }
    private List<Integer> roadToCircleEntry(int startB, List<Integer> circle, Map<Integer, Node> graph){
        List<Integer> visited = new ArrayList<>();
        List<Integer> road = new ArrayList<>();
        visited.add(startB);
        road.add(startB);
        while (true) {
            boolean removeFlag = false;
            boolean circleFlag = false;
            for (Node node : graph.get(road.get(road.size() - 1)).next) {
                if (!visited.contains(node.val)) {
                    visited.add(node.val);
                    road.add(node.val);
                    removeFlag = true;
                    if (circle.contains(node.val)) {
                        circleFlag = true;
                    }
                    break;
                }
            }
            if (circleFlag) break;
            if (!removeFlag && road.size() != 1) road.remove(road.size() - 1);
        }
        return road;
    }
    private List<Integer> findCircle(Map<Integer, Node> graph) {
        List<Integer> circle = new ArrayList<>();
        List<Integer> visited = new ArrayList<>();
        List<Integer> road = new ArrayList<>();
        visited.add(1);
        road.add(1);
        while (true) {
            boolean removeFlag = false;
            boolean circleFlag = false;
            for (Node node : graph.get(road.get(road.size() - 1)).next) {
                if (!visited.contains(node.val)) {
                    visited.add(node.val);
                    road.add(node.val);
                    removeFlag = true;
                    break;
                }
                if (road.contains(node.val) && node.val != road.get(road.size() - 2)) {
                    int i = road.indexOf(node.val);
                    circle = road.subList(i, road.size());
                    circleFlag = true;
                    break;
                }
            }
            if (circleFlag) break;
            if (!removeFlag) road.remove(road.size() - 1);
        }
        return circle;
    }
    private Map<Integer, Node> creatGraph(int[][] edges) {
        Map<Integer, Node> map = new HashMap<>();
        for (int[] edge : edges) {
            Node node;
            List<Node> next;
            if (map.containsKey(edge[0])) {
                node = map.get(edge[0]);
                next = node.next;
            } else {
                next = new ArrayList<>();
                node = new Node(edge[0], next);
                map.put(edge[0], node);
            }
            Node node1;
            List<Node> next1;
            if (map.containsKey(edge[1])) {
                node1 = map.get(edge[1]);
                next1 = node1.next;
            } else {
                next1 = new ArrayList<>();
                node1 = new Node(edge[1], next1);
                map.put(edge[1], node1);
            }
            next.add(node1);
            next1.add(node);
        }
        return map;
    }

    private class Node {
        int val;
        List<Node> next;

        public Node(int val, List<Node> next) {
            this.val = val;
            this.next = next;
        }
    }
}
