package com.fw.tree;

import java.util.Objects;
import java.util.logging.Logger;

/**
 * @Author fengwei
 * Created on 2016/8/15/0015.
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
            if(current.key.compareTo(node.key) <= 0) {
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
                if(null != uncle && RED == uncle.color) {
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
            if(current.key.compareTo(key) > 0) {
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
        log.info("DELETE---------------------------------------->");
        Node<T> child, parent;
        boolean color;

        // 被删除节点的"左右孩子都不为空"的情况。
        if ( (node.left!=null) && (node.right!=null) ) {
            // 被删节点的后继节点。(称为"取代节点")
            // 用它来取代"被删节点"的位置，然后再将"被删节点"去掉。
            Node<T> replace = node;

            // 获取后继节点
            replace = replace.right;
            while (replace.left != null)
                replace = replace.left;

            // "node节点"不是根节点(只有根节点不存在父节点)
            if ((node.parent)!=null) {
                if ((node.parent).left == node)
                    (node.parent).left = replace;
                else
                    (node.parent).right = replace;
            } else {
                // "node节点"是根节点，更新根节点。
                this.root = replace;
            }

            // child是"取代节点"的右孩子，也是需要"调整的节点"。
            // "取代节点"肯定不存在左孩子！因为它是一个后继节点。
            child = replace.right;
            parent = (replace.parent);
            // 保存"取代节点"的颜色
            color = (replace.color);

            // "被删除节点"是"它的后继节点的父节点"
            if (parent == node) {
                parent = replace;
            } else {
                // child不为空
                if (child!=null)
                    child.parent = parent;
                parent.left = child;

                replace.right = node.right;
                node.right.parent = replace;
            }

            replace.parent = node.parent;
            replace.color = node.color;
            replace.left = node.left;
            node.left.parent = replace;

            if (color == BLACK)
                removeFixUp(child, parent);

            node = null;
            return ;
        }

        if (node.left !=null) {
            child = node.left;
        } else {
            child = node.right;
        }

        parent = node.parent;
        // 保存"取代节点"的颜色
        color = node.color;

        if (child!=null)
            child.parent = parent;

        // "node节点"不是根节点
        if (parent!=null) {
            if (parent.left == node)
                parent.left = child;
            else
                parent.right = child;
        } else {
            this.root = child;
        }

        if (color == BLACK)
            removeFixUp(child, parent);
        node = null;

    }

    public void remove(T key) {
        Node<T> del = findNode(key);
        remove(del);
    }

    public boolean isRed(Node n) {
        return n.color == RED;
    }

    public boolean isBlack(Node n) {
        return n.color == BLACK;
    }

    // 删除节点以后，如果替代节点为黑，那么子节点分支少了一个黑，将子节点兄弟节点分支也减少一个黑以后，
    // 由子节点的位置开始由上直至根节点查找任意一个红节点，将其置为黑色恢复rb树的红黑性质。
    private void removeFixUp(Node node, Node parent) {
        Node<T> other;
        while ((node==null || node.color==BLACK) && (node != this.root)) {
            if (parent.left == node) {
                other = parent.right;
                if (other.color==RED) {
                    // Case 1: x的兄弟w是红色的
                    other.changeColor(BLACK);
                    parent.changeColor(RED);
                    leftRotate(parent);
                    other = parent.right;
                }

                if ((other.left==null || isBlack(other.left)) &&
                        (other.right==null || isBlack(other.right))) {
                    // Case 2: x的兄弟w是黑色，且w的俩个孩子也都是黑色的
                    other.changeColor(RED);
                    node = parent;
                    parent = node.parent;
                } else {

                    if (other.right==null || isBlack(other.right)) {
                        // Case 3: x的兄弟w是黑色的，并且w的左孩子是红色，右孩子为黑色。
                        other.left.changeColor(BLACK);
                        other.changeColor(RED);
                        rightRotate(other);
                        other = parent.right;
                    }
                    // Case 4: x的兄弟w是黑色的；并且w的右孩子是红色的，左孩子任意颜色。
                    other.changeColor(parent.color);
                    parent.changeColor(BLACK);
                    other.right.changeColor(BLACK);
                    leftRotate(parent);
                    node = this.root;
                    break;
                }
            } else {

                other = parent.left;
                if (isRed(other)) {
                    // Case 1: x的兄弟w是红色的
                    other.changeColor(BLACK);
                    parent.changeColor(RED);
                    rightRotate(parent);
                    other = parent.left;
                }

                if ((other.left==null || isBlack(other.left)) &&
                        (other.right==null || isBlack(other.right))) {
                    // Case 2: x的兄弟w是黑色，且w的俩个孩子也都是黑色的
                    other.changeColor(RED);
                    node = parent;
                    parent = node.parent;
                } else {

                    if (other.left==null || isBlack(other.left)) {
                        // Case 3: x的兄弟w是黑色的，并且w的左孩子是红色，右孩子为黑色。
                        other.right.changeColor(BLACK);
                        other.changeColor(RED);
                        leftRotate(other);
                        other = parent.left;
                    }

                    // Case 4: x的兄弟w是黑色的；并且w的右孩子是红色的，左孩子任意颜色。
                    other.changeColor(parent.color);
                    parent.changeColor(BLACK);
                    other.left.changeColor(BLACK);
                    rightRotate(parent);
                    node = this.root;
                    break;
                }
            }
        }

        if (node!=null)
            node.changeColor(BLACK);
    }


    public static void main(String[] args) {
        RBTree<Integer> tree = new RBTree<Integer>();
        tree.insert(4);
        tree.insert(1);
        tree.insert(5);
        tree.insert(2);
        tree.insert(9);
        tree.insert(3);
        tree.insert(7);
        tree.insert(11);
        tree.insert(18);
        tree.insert(16);
        tree.insert(12);
        tree.insert(18);
        tree.insert(17);
        tree.insert(10);
        tree.insert(20);
        tree.insert(19);

        tree.displayRoot();
        tree.frontOrder(tree.root);
        tree.remove(16);
        tree.frontOrder(tree.root);

    }

}
