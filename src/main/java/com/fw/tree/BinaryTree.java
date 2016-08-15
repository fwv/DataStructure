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
            Node successor = getSuccessor(current);
            if(current == root) {
                root = successor;
            }else if(isLeft) {
                parent.leftChild = successor;
            }else {
                parent.rightChild = successor;
            }
            successor.leftChild = current.leftChild;
        }
        return true;
    }

    // 查找指定删除节点的中序后继节点
    private Node getSuccessor(Node delNode) {
        Node successorparent = delNode;
        Node successor = delNode;
        Node current = delNode.rightChild;
        while(null != current) {
            successorparent = successor;
            successor = current;
            current = current.leftChild;
        }
        if(successor != delNode.rightChild) {
            successorparent.leftChild = successor.rightChild;
            successor.rightChild = delNode.rightChild;
        }
        return successor;
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
    private void frontOrder(Node localRoot) {
        if(null != localRoot) {
            localRoot.displayNode();
            inOrder(localRoot.leftChild);
            inOrder(localRoot.rightChild);
        }
    }
    // 后序遍历
    private void afterOrder(Node localRoot) {
        if(null != localRoot) {
            inOrder(localRoot.leftChild);
            inOrder(localRoot.rightChild);
            localRoot.displayNode();
        }
    }

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
        tree.insert(3);
        for(int i = 0; i < 5; i++) {
           // tree.insert((int)(Math.random()*100));
            tree.insert(i+1);
        }
        tree.insert(8);
        tree.insert(7);
        tree.insert(9);
        tree.inOrder(tree.root);
        tree.delete(3);
        tree.inOrder(tree.root);

    }
}
