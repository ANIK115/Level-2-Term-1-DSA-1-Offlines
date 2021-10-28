package com.company;

public class Stack<T> {
    private Node<T> top=null;

    public void push(T data)
    {
        if(top==null)
        {
            top = new Node<>(data);
            top.setPrev(null);
            return;
        }
        Node<T> temp = new Node<>(data);
        temp.setPrev(top);
        top = temp;

    }

    public T top()
    {
        try {
            return top.getData();
        }catch (NullPointerException e)
        {
            System.out.println("Stack Underflow!");
        }
        return null;
    } // 1,2,3
    public void pop()
    {
        if(top!=null)
        {
            top = top.getPrev();
        }else
        {
            throw new NullPointerException();
        }
    }
    public boolean isEmpty()
    {
        if(top == null)
            return true;
        else
            return false;
    }
}
