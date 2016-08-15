package com.fw.tree;

import java.util.logging.Logger;

/**
 * Created by Administrator on 2016/8/15/0015.
 */
public class RBTree<T extends Comparable<T>> {
    public static Logger log = Logger.getLogger(RBTree.class.getName());

    public static final boolean RED = true;
    public static final boolean BLACK = false;
    public Node<T> root;

    public class Node<T extends Comparable<T>> {
        public boolean color;
        public T key;
        public Node<T> left;
        public Node<T> right;
        public Node<T> parent;

        public Node(T key, boolean color, Node left, Node right, Node parent) {
            this.key = key;
            this.color = color;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }

    public void leftRotate(Node<T> current) {
        Node pivot = current.right;
        current.right = pivot.left;
        if(null != pivot.left) {
            pivot.left.parent = current;
        }

        if (null == current.parent) {
            this.root = pivot;
        }else if (current == current.parent.left){
            current.parent.left = pivot;
        }else {
            current.parent.right = pivot;
        }
        pivot.parent = current.parent;

        current.parent = pivot;
        pivot.left = current;
    }

    public void rightRotate(Node<T> current) {
        Node pivot = current.left;

        pivot.parent = current.parent;
        if(null == current.parent) {
            this.root = pivot;
        }else if(current == current.parent.left) {
            current.parent.left = pivot;
        }else {
            current.parent.right = pivot;
        }

        current.left = pivot.right;
        if(null != pivot.right) {
            pivot.right.parent = current;
        }

        current.parent = pivot;
        pivot.right = current;
    }

    private void insert(Node node) {
        Node parent = null;
        Node current = this.root;
        while(null != current) {
            parent = current;
            if(current.key.compareTo(node.key) < 0) {
                current = current.right;
            }else {
                current = current.left;
            }
        }
        if(node.parent != null) {
            node.parent = parent;
            if(node.key.compareTo(parent.key) < 0) {
                parent.left = node;
            }else if(node.key.compareTo(parent.key) >= 0) {
                parent.right = node;
            }
        }else {
            this.root = node;
        }

        node.color = RED;
        insertFixUp(node);
    }

    private void insert(T key) {
        Node n = new Node(key, BLACK, null, null, null);
        insert(n);
    }

    private void insertFixUp(Node node) {
        Node parent,gparent;
        while(null !=(parent = node.parent) && parent.color == RED) {
            gparent = parent.parent;

            if(parent == gparent.left) {
                // case 1: uncle is red
                Node uncle = gparent.right;
                if(RED == uncle.color && null != uncle) {
                    parent.color = BLACK;
                    uncle.color = BLACK;
                    gparent.color = RED;
                    node = gparent;
                    continue;
                }

                // case 2 : uncle is black, node is right
                if(null != uncle && node == parent.right) {
                    Node temp;
                    leftRotate(parent);
                    temp = node;
                    node = parent;
                    parent = temp;
                    continue;
                }

                // case 3: uncle is black, node is left
                parent.color = BLACK;
                gparent.color = RED;
                rightRotate(gparent);
            }else {
                // Case 1条件：叔叔节点是红色
                Node<T> uncle = gparent.left;
                if ((uncle!=null) && RED == uncle.color) {
                    uncle.color = BLACK;
                    parent.color = BLACK;
                    gparent.color = RED;
                    node = gparent;
                    continue;
                }

                // Case 2条件：叔叔是黑色，且当前节点是左孩子
                if (parent.left == node) {
                    Node<T> tmp;
                    rightRotate(parent);
                    tmp = parent;
                    parent = node;
                    node = tmp;
                    continue;
                }

                // Case 3条件：叔叔是黑色，且当前节点是右孩子。
                parent.color = BLACK;
                gparent.color = RED;
                leftRotate(gparent);
            }
        }
        this.root.color = BLACK;
    }

}
