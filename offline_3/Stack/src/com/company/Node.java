package com.company;

public class Node<T> {
    private T data;
    Node<T> prev;

    public Node(T data) {
        this.data = data;
        prev = null;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> getPrev() {
        return prev;
    }

    public void setPrev(Node<T> prev) {
        this.prev = prev;
    }
}
