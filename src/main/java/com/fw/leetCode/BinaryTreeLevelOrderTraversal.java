package com.fw.leetCode;

import com.fw.tree.BinaryTree;
import com.fw.tree.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.logging.Logger;


/**
 * Leetcode 102.binary tree level order traversal
 * Given a binary tree, return the level order traversal of its nodes' values.
 * Given binary tree [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *   /  \
 *  15   7
 *  RETURN:
 *  [
 *   [3],
 *   [9],[20]
 *   [15],[7]
 *  ]
 *
 *  @author fw
 *  runtime: 7ms
 */
public class BinaryTreeLevelOrderTraversal {

    private static Logger log = Logger.getLogger(BinaryTreeLevelOrderTraversal.class.getName());

    private BinaryTree tree;

    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        ArrayList<Node> gparent = new ArrayList<Node>();
        ArrayList<Node> parent = new ArrayList<Node>();
        Queue<Node> queue = new LinkedList<Node>();
        if (null == root) return result;
        gparent.add(root);
        queue.add(root);
        result.add(nodeToVal(gparent));
        while(!queue.isEmpty()) {
            Node node = queue.poll();
            if (node != root) {
                if (isSon(node, gparent)) {
                    parent.add(node);
                }else {
                    result.add(nodeToVal(parent));
                    gparent = (ArrayList<Node>) parent.clone();
                    parent.clear();
                    parent.add(node);
                }
            }
            if (null != node.leftChild)queue.add(node.leftChild);
            if (null != node.rightChild)queue.add(node.rightChild);
        }
        if (0 != parent.size())
        result.add(nodeToVal(parent));

        return result;
    }

    public List<Integer> nodeToVal(List<Node> nodes) {
        List<Integer> result = new ArrayList<Integer>();
        for (Node n : nodes) {
            result.add(n.iData);
        }
        return result;
    }

    public boolean isSon(Node node, List<Node> parents) {
        if (null == parents || 0 == parents.size())return false;
        for (Node p : parents) {
            if (node == p.leftChild || node == p.rightChild)return true;
        }
        return false;
    }

    public static void main(String[] args) {
        BinaryTreeLevelOrderTraversal bt = new BinaryTreeLevelOrderTraversal();
        bt.tree = new BinaryTree();
        bt.tree.insert(66);
        bt.tree.insert(20);
        bt.tree.insert(200);
        bt.tree.insert(5);
        bt.tree.insert(21);
        List<List<Integer>> result = bt.levelOrder(bt.tree.root);
        log.info(result.size() +"");
        StringBuffer s = new StringBuffer();
        StringBuilder bb = new StringBuilder();

    }

}
