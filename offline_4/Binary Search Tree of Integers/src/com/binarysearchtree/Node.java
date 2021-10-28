package com.binarysearchtree;

public class Node {
    private int item;
    private Node leftChild;
    private Node rightChild;

    public Node(int item) {
        this.item = item;
        leftChild = rightChild = null;
    }

    public int getItem() {
        return item;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }
}
