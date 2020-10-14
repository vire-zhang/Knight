package com.yuanfudao;

import java.util.*;

public class LevelOrderTravel {

    public List<List<Integer>> levelOrder(Node root) {

        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<Node> nodeQueue = new LinkedList<Node>();
        nodeQueue.add(root);
        while (!nodeQueue.isEmpty()) {
            List<Integer> levelList = new ArrayList<>();
            int size = nodeQueue.size();
            for (int i = 0; i < size; i++) {
                Node node = nodeQueue.poll();
                levelList.add(node.val);
                nodeQueue.addAll(node.children);
            }
            result.add(levelList);
        }
        return result;
    }
}
