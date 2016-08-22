package com.fw.leetCode;

import com.fw.tree.BinaryTree;
import com.fw.tree.Node;

import java.util.*;
import java.util.logging.Logger;

/**
 * Leetcode 199. Binary Tree Right Side View
 * Given a binary tree, imagine yourself standing on the right side of it,
 * return the values of the nodes you can see ordered from top to bottom.
 *            1            <---
 *          /   \
 *         2     3         <---
 *          \     \
 *           5     x       <---
 *          return [1, 3, 5]
 * @author fw
 */
public class BinaryTreeRightSide {
    public static Logger log = Logger.getLogger(BinaryTreeRightSide.class.getName());
    public BinaryTree tree;
    private LinkedList<Node> p = new LinkedList<Node>();
    private LinkedList<Node> gp = new LinkedList<Node>();

    public BinaryTreeRightSide() {
        tree = new BinaryTree();
    }

    public List<Integer> rightSideView(Node root) {
        LinkedList<Integer> result = new LinkedList<Integer>();
        Queue<Node> queue = new LinkedList<Node>();
        Node lastNode = root;
        if(null != root) {
            queue.add(root);
            result.add(root.iData);
            gp.add(root);
            if (null == root.leftChild && null == root.rightChild)return result;
            while(!queue.isEmpty()) {
                Node cur = queue.poll();
                lastNode = cur;
                if(cur.leftChild != null) {
                    if (!isBelong(cur.leftChild, gp)) {
                        Integer n = p.getLast().iData;
                        result.add(n);
                        gp = (LinkedList<Node>) p.clone();
                        p.clear();
                    }
                    p.add(cur.leftChild);
                    queue.add(cur.leftChild);
                }
                if(cur.rightChild != null) {
                    if (!isBelong(cur.rightChild, gp)) {
                        Integer n = p.getLast().iData;
                        result.add(n);
                        gp = (LinkedList<Node>) p.clone();
                        p.clear();
                    }
                    p.add(cur.rightChild);
                    queue.add(cur.rightChild);
                }
            }
            result.add(lastNode.iData);
        }
        return result;
    }

    public boolean isBelong(Node node, List<Node> list) {
        if (list.isEmpty())return false;
        for(Node parent : list) {
            if (parent.leftChild == node || parent.rightChild == node) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        BinaryTreeRightSide ts = new BinaryTreeRightSide();
        ts.tree.insert(5);
        ts.tree.insert(2);
        ts.tree.insert(7);
        ts.tree.insert(3);
        ts.tree.insert(6);

        ts.tree.frontOrder(ts.tree.root);
        List<Integer> list = ts.rightSideView(ts.tree.root);
        log.info("============== RIGHT SIDE ==============");
        for (Integer i : list) {
            log.info("right side: " + i);
        }
    }

}
