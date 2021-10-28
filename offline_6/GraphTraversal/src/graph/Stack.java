package graph;

public class Stack<T> {
    private Node<T> top=null;

    public void push(T data)
    {
        if(top==null)
        {
            top=new Node<>(data);
            top.setNext(null);
            return;
        }
        Node<T> temp = new Node<>(data);
        temp.setNext(top);
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
    }
    public void pop()
    {
        if(top!=null)
        {
            top = top.getNext();
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
