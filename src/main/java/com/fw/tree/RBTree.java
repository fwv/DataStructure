package com.fw.tree;

import java.util.Objects;
import java.util.logging.Logger;

/**
 * Created by Administrator on 2016/8/15/0015.
 */
public class RBTree<T extends Comparable<T>> {
    public static Logger log = Logger.getLogger(RBTree.class.getName());

    public static final boolean RED = true;
    public static final boolean BLACK = false;
    public Node<T> root;

    public static class Node<T extends Comparable<T>> {
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

        public static String displayNode(Node node) {
            if(null == node) {
                return "NULL";
            }
            String color = node.color ? "RED":"BLACK";

            return "{ "+node.key+", "+ color + " }";
        }

        public static void display(Node node) {
            log.info("I am the node : " + displayNode(node) + "| my father is " + displayNode(node.parent)
                    + "| my left child is " +  displayNode(node.left)
                    + "| my right child is " + displayNode(node.right));
        }

        public void changeColor(boolean newColor) {
            this.color = newColor;
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
        if (current == null) {
            this.root = node;
            node.color = BLACK;
            return;
        }
        while(null != current) {
            parent = current;
            if(current.key.compareTo(node.key) < 0) {
                current = current.right;
            }else {
                current = current.left;
            }
        }
        node.parent = parent;
        if(node.key.compareTo(parent.key) < 0) {
            parent.left = node;
        }else if(node.key.compareTo(parent.key) >= 0) {
            parent.right = node;
        }


        node.changeColor(RED);
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
                    parent.changeColor(BLACK);
                    uncle.changeColor(BLACK);
                    gparent.changeColor(RED);
                    node = gparent;
                    continue;
                }

                // case 2 : uncle is black, node is right
                if(null != uncle && node == parent.right) {
                    node = parent;
                    leftRotate(parent);
                    continue;
                }

                // case 3: uncle is black, node is left
                parent.changeColor(BLACK);
                gparent.changeColor(RED);
                rightRotate(gparent);
            }else {
                // Case 1条件：叔叔节点是红色
                Node<T> uncle = gparent.left;
                if ((uncle!=null) && RED == uncle.color) {
                    uncle.changeColor(BLACK);
                    parent.changeColor(BLACK);
                    gparent.changeColor(RED);
                    node = gparent;
                    continue;
                }

                // Case 2条件：叔叔是黑色，且当前节点是左孩子
                if (parent.left == node) {
                    Node<T> tmp;
                    node = parent;
                    rightRotate(parent);
                    continue;
                }

                // Case 3条件：叔叔是黑色，且当前节点是右孩子。
                parent.changeColor(BLACK);
                gparent.changeColor(RED);
                leftRotate(gparent);
            }
        }
        this.root.changeColor(BLACK);
    }

    public void inOrder(Node node) {
        Node current = node;
        if(current != null) {
            inOrder(current.left);
            Node.display(node);
            inOrder(current.right);
        }
    }

    public void frontOrder(Node node) {
        Node current = node;
        if(null != current) {
            Node.display(current);
            frontOrder(current.left);
            frontOrder(current.right);
        }
    }

    public void displayRoot() {
        if(null != root)
            log.info("ROOT : " + this.root.displayNode(this.root));
    }

    public Node findNode(T key) {
        if(null == this.root)return null;

        Node current = this.root;
        while(key != current.key) {
            if(current.key.compareTo(key) < 0) {
                current = current.left;
                if(null == current)return null;
            }else {
                current = current.right;
                if(null == current) return null;
            }
        }
        return current;
    }


    public void remove(Node<T> node) {
        Node child,parent;
        boolean color;
        if(null != node.left && null != node.right) {
            Node replace = node;
            replace = replace.right;
            if(replace != null) {
                replace = replace.left;
            }
            if (this.root == node) {
                this.root = replace;
            }else {
                if (node == node.parent.left) {
                    node.parent.left = replace;
                }else {
                    node.parent.right = replace;
                }
            }
            child = replace.right;
            parent = replace.parent;
            color = replace.color;

            if(parent == node) {
                parent = replace;
            }else {
                if(null != child) {
                    child.parent = parent;
                    parent.left = child;
                }
                replace.right = node.right;
                node.right.parent = replace;
            }
            replace.parent = node.parent;
            replace.color = node.color;
            replace.left = node.left;
            node.left.parent = replace;

            if(color == BLACK) {
                removeFixUp(child, parent);
            }
            node = null;
            return;
        }

        if(node.left != null) {
            child = node.left;
        }else {
            child = node.right;
        }
        parent = node.parent;
        color = node.color;

        if(null != child)
            child.parent = parent;

        if (null != parent) {
            if (parent.left == node) {
                parent.left = child;
            }else if (parent.right == node) {
                parent.right = child;
            }
        } else {
            this.root = child;
        }

        if (BLACK == color)
            removeFixUp(child, parent);
        node = null;
    }

    public void remove(T key) {
        Node<T> del = findNode(key);
        remove(del);
    }

    private void removeFixUp(Node node, Node parent) {
        Node other;
        while(((null == node) || node.color==BLACK) && node != this.root) {
            if (parent.left == node) {
                other = parent.right;

                //case 1:
                if (other.color == RED) {
                    other.changeColor(BLACK);
                    parent.changeColor(RED);
                    leftRotate(parent);
                    other = parent.right;
                }

            }else {
                other = parent.left;
            }
        }
    }


    public static void main(String[] args) {
        RBTree<Integer> tree = new RBTree<Integer>();
        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        tree.insert(4);
        tree.insert(5);
        tree.insert(6);
        tree.insert(7);
        tree.insert(8);
        tree.insert(9);
        tree.displayRoot();
        tree.frontOrder(tree.root);

    }

}
