package graph;

public class Queue<T> {
    private Node<T> front;
    private Node<T> rear;

    public void enqueue(T data)
    {
        Node<T> temp = new Node<>(data);
        if(front==null && rear==null)
        {
            front=rear=temp;
            return;
        }
        rear.setNext(temp);
        rear=temp;
    }
    public void dequeue()
    {
        if(front==null)
        {
            return;
        }
        if(front==rear)
        {
            front=rear=null;
        }else
        {
            front=front.getNext();
        }
    }
    public T front()
    {
        return front.getData();
    }
    public boolean isEmpty()
    {
        if(front==null)
            return true;
        else
            return false;
    }
}
