package com.fw.tree;

import java.util.logging.Logger;

/**
 * @Author fengwei
 * Created on 2016/8/12/0012.
 */
public class Node {
    public static Logger log = Logger.getLogger(Node.class.getName());
    public int iData;
    public double dData;
    public Node leftChild;
    public Node rightChild;

    public Node(int iData) {
        this.iData = iData;
    }

    public void displayNode() {
        String left = null==leftChild ? "NULL" : leftChild.iData+"";
        String right = null==rightChild ? "NULL" : rightChild.iData+"";
        log.info("Node:{ "+ iData +" }, my left child is:{ "+left+" } , my right child is:{ "+right+" }");
    }
}
