package com.fw.leetCode;

import com.fw.tree.BinaryTree;
import com.fw.tree.Node;

import java.util.*;
import java.util.logging.Logger;

/**
 * Created by Administrator on 2016/8/19/0019.
 */
public class BinaryTreeRightSide {
    public static Logger log = Logger.getLogger(BinaryTreeRightSide.class.getName());
    public BinaryTree tree;
    private List<Node> parents = new ArrayList<Node>();

    public BinaryTreeRightSide() {
        tree = new BinaryTree();
    }

    // 获取二叉树右视角能够看到的元素
    //              1            <---
    //            /   \
    //           2     3         <---
    //            \     \
    //             5     4       <---
    //            return [1, 3, 4]
    public List<Integer> rightSideView(Node root) {
        LinkedList<Integer> result = new LinkedList<Integer>();
        Queue<Node> queue = new LinkedList<Node>();
        LinkedList<Node> temp = new LinkedList<Node>();
        if(null != root) {
            queue.add(root);
            result.add(root.iData);
            parents.add(root);
            while(!queue.isEmpty()) {
                Node cur = queue.poll();
                if(cur.leftChild != null) {
                    if(isFirst(cur.leftChild)) {
                        parents.clear();
                        parents = temp;
                        parents.add(cur);
                        if(!temp.isEmpty()) {
                            Integer n = temp.getLast().iData;
                            result.add(n);
                        }
                        temp.clear();
                    }
                    queue.add(cur.leftChild);
                    temp.add(cur.leftChild);
                }
                if(cur.rightChild != null) {
                    if(isFirst(cur.leftChild)) {
                        parents.clear();
                        parents = temp;
                        parents.add(cur);
                        if(!temp.isEmpty()) {
                            Integer n = temp.getLast().iData;
                            result.add(n);
                        }
                        temp.clear();
                    }
                    queue.add(cur.rightChild);
                    temp.add(cur.rightChild);
                }
            }
        }
        return result;
    }

    public boolean isFirst(Node node) {
        if (parents.isEmpty())return true;
        for(Node parent : parents) {
            if (parent.leftChild == node || parent.rightChild == node) {
                return false;
            }
        }
        return true;
    }

    public int depth(Node node) {
        return tree.getMaxDepth(node);
    }

    public void deleteFlag(List<Integer> list) {
        Iterator<Integer> it = list.iterator();
        while(it.hasNext()) {
            if(-1 == it.next())
                it.remove();
        }
    }

    public static void main(String[] args) {
        BinaryTreeRightSide ts = new BinaryTreeRightSide();
        ts.tree.insert(6);
        ts.tree.insert(1);
        ts.tree.insert(3);
        ts.tree.insert(2);
        ts.tree.insert(5);
        ts.tree.insert(4);
        ts.tree.frontOrder(ts.tree.root);
        List<Integer> list = ts.rightSideView(ts.tree.root);
        log.info("============== RIGHT SIDE ==============");
        for (Integer i : list) {
            log.info("right side: " + i);
        }
    }

}
