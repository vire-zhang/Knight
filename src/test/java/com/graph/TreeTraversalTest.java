package com.graph;

import com.graph.bean.TreeNode;
import com.graph.config.GraphConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = GraphConfig.class)
public class TreeTraversalTest {

    @Test
    public void test() {
        TreeTraversal travel = new TreeTraversal();

        System.out.println("先序遍历-递归：");
        travel.preOrderRecursive(aTree);
        System.out.println("先序遍历-非递归：");
        travel.preOrderNonRecursive(aTree);
        System.out.println("中序遍历-递归：");
        travel.inOrderRecursive(aTree);
        System.out.println("中序遍历-非递归：");
        travel.inOrderNonRecursive(aTree);
        System.out.println("后序遍历-递归：");
        travel.postOrderRecursive(aTree);
        System.out.println("后序遍历-非递归：");
        travel.postOrderNonRecursive(aTree);
    }

    @Autowired
    @Qualifier("preOrderR")
    private TreeTravel t1;

    @Autowired
    @Qualifier("preOrderNR")
    private TreeTravel t2;

    @Autowired
    @Qualifier("inOrderR")
    private TreeTravel t3;

    @Autowired
    @Qualifier("inOrderNR")
    private TreeTravel t4;

    @Autowired
    @Qualifier("postOrderR")
    private TreeTravel t5;

    @Autowired
    @Qualifier("postOrderNR")
    private TreeTravel t6;

    private TreeNode aTree = creatTree();

    @Test
    public void test2() {
        System.out.println("先序遍历-递归：");
        runTravelMethod(t1);
        System.out.println("先序遍历-非递归：");
        runTravelMethod(t2);
        System.out.println("中序遍历-递归：");
        runTravelMethod(t3);
        System.out.println("中序遍历-非递归：");
        runTravelMethod(t4);
        System.out.println("后序遍历-递归：");
        runTravelMethod(t5);
        System.out.println("后序遍历-非递归：");
        runTravelMethod(t6);
    }

    private void runTravelMethod(TreeTravel t) {
        long startTime2 = System.nanoTime();
        t.treeTravel(aTree);
        long endTime2 = System.nanoTime();
        System.out.println("运行时间：" + (endTime2 - startTime2) + "ns");
    }

    private TreeNode creatTree() {
        TreeNode aTree = new TreeNode(1);
        TreeNode treeLeft = new TreeNode(2, new TreeNode(3), new TreeNode(4));
        TreeNode treeRight = new TreeNode(5, new TreeNode(6), new TreeNode(7));
        aTree.setLeft(treeLeft);
        aTree.setRight(treeRight);
        return aTree;
    }
}
