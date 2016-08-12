package com.fw.tree;

import java.util.logging.Logger;

/**
 * Created by Administrator on 2016/8/12/0012.
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
        log.info("Node:{ "+ iData +" }");
    }
}
