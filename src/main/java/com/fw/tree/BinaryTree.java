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
            }else if(current == null) {
                return null;
            }
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

    public boolean delete(int key) {
        if(isEmpty())return false;
        Node current = root;
        // find delete node
        Node parent = root;
        boolean isLeft = false;
        while(current.iData != key) {
            parent = current;
            if (key < current.iData) {
                isLeft = true;
                current = current.leftChild;
            }else if(key >= current.iData){
                isLeft = false;
                current = current.rightChild;
            }else if(current == null) {
                return false;
            }
        }

       // the delete node doesn't have any child
        if(null == current.leftChild && null == current.rightChild) {
            // the delete node is root
            if (current == root) {
                root = null;
            }
            if (isLeft) {
                parent.leftChild = null;
            } else {
                parent.rightChild = null;
            }
       // the delete node has one child
        }else if(null == current.leftChild) {
            if(current == root) {
                root = current.rightChild;
            }
            if(isLeft) {
                parent.leftChild = current.rightChild;
            }else {
                parent.rightChild = current.rightChild;
            }
        }else if (null == current.rightChild) {
            if(current == root) {
                root = current.leftChild;
            }
            if(isLeft) {
                parent.leftChild = current.leftChild;
            }else {
                parent.rightChild = current.leftChild;
            }
        }
        // the delete node has two nodes
        else if(null != current.leftChild && null != current.rightChild) {

        }
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

    public Node findMinNode() {
        if (isEmpty())return null;
        Node current = root;
        while(current != null) {
           if(current.leftChild == null)return current;
            current = current.leftChild;
        }
        return null;
    }



    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        log.info("Tree---->: ");
        for(int i = 0; i < 10; i++) {
            tree.insert((int)(Math.random()*100));
        }
        tree.inOrder(tree.root);
        log.info("RootNode ---->: "+tree.root.iData);
        tree.findMinNode().displayNode();

    }
}
