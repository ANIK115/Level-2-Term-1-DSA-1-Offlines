package com.company;

public class BinarySearchTree<T extends Number & Comparable<T>> {
    private Node<T> root;

    public BinarySearchTree() {
        root = null;
    }

    public Node<T> getRoot() {
        return root;
    }

    public void insertItem(T data)
    {
        root = insertItem(root, data);
    }

    private Node<T> insertItem(Node<T> root, T data)
    {
        if(root == null) {
            root = new Node<>(data);
            return root;
        }
        if(data.compareTo(root.getData()) < 0) {
            root.setLeftChild(insertItem(root.getLeftChild(), data));
        }else {
            root.setRightChild(insertItem(root.getRightChild(), data));
        }
        return root;
    }
    public void searchItem(T data) {
        searchItem(root,data);
    }

    public void searchItem(Node<T> root, T data)
    {
        if(root == null)
        {
            System.out.println(data +" is not found!");
            return ;
        }else if(root.getData().compareTo(data) == 0)
        {
            System.out.println(data +" is found!");
            return ;
        }else if(data.compareTo(root.getData()) < 0)
        {
            searchItem(root.getLeftChild(), data);
        }else {
            searchItem(root.getRightChild(), data);
        }
    }
    public int getItemDepth(T item)
    {
        Node<T> current = root;
        int depth = 0;
        while(current !=null)
        {
            if(item.compareTo(current.getData()) == 0 )
            {
                break;
            }else if(item.compareTo(current.getData()) < 0)
            {
                current = current.getLeftChild();
            }else
            {
                current = current.getRightChild();
            }
            depth++;
        }
        if(current == null) return -1;
        else return depth;
    }
    public T getMaxItem()
    {
        Node<T> current = root;
        while(current.getRightChild() != null)
        {
            current = current.getRightChild();
        }
        return current.getData();
    }
    public T getMinItem()
    {
        Node<T> current = root;
        while(current.getLeftChild() != null)
        {
            current = current.getLeftChild();
        }
        return current.getData();
    }
    public void printInOrder(Node<T> root)
    {
        if(root == null) return;
        printInOrder(root.getLeftChild());
        System.out.println(root.getData());
        printInOrder(root.getRightChild());
    }
    public void printPreOrder(Node<T> root)
    {
        if(root == null) return;
        System.out.println(root.getData());
        printPreOrder(root.getLeftChild());
        printPreOrder(root.getRightChild());
    }
    public void printPostOrder(Node<T> root)
    {
        if(root == null) return;
        printPostOrder(root.getLeftChild());
        printPostOrder(root.getRightChild());
        System.out.println(root.getData());
    }
    public int getSize(Node<T> root)
    {
        if(root == null) return 0;
        return getSize(root.getLeftChild()) + getSize(root.getRightChild()) +1 ; //1 is for root node
    }
}
