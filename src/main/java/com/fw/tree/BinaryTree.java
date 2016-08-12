package com.fw.tree;

import java.util.logging.Logger;

/**
 * Created by Administrator on 2016/8/12/0012.
 */
public class BinaryTree {

    public static Logger log = Logger.getLogger(BinaryTree.class.getName());
    public Node root;

    public Node find(int key) {
        if(isEmpty()) {
            return null;
        }
        Node current = root;
        while(current.iData != key) {
            if(current.iData == key) {
                return current;
            }else if(key < current.iData) {
                current = current.leftChild;
            }else if(key >= current.iData) {
                current = current.rightChild;
            }
            return  null;
        }
        return null;
    }

    public void insert(int key) {
       Node newNode = new Node(key);
       if(isEmpty()) {
           root = newNode;
           return;
       }
        Node current = root;
        Node parent;
        while(true) {
            parent = current;
            if(key < current.iData) {
                current = current.leftChild;
                if(null == current) {
                    parent.leftChild = newNode;
                    return;
                }
            }else {
                current = current.rightChild;
                if(null == current) {
                    parent.rightChild = newNode;
                    return;
                }

            }
        }

    }

    public void delete(int key) {

    }

    // 中序遍历
    private void inOrder(Node localRoot) {
        if(null != localRoot) {
            inOrder(localRoot.leftChild);
            localRoot.displayNode();
            inOrder(localRoot.rightChild);
        }
    }

    // 前序遍历
    // 后序遍历

    public boolean isEmpty() {return(null == root);}



    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        log.info("Tree---->: ");
        for(int i = 0; i < 10; i++) {
            tree.insert((int)(Math.random()*100));
        }
        tree.inOrder(tree.root);
        log.info("RootNode ---->: "+tree.root.iData);

    }
}
