package com.binarysearchtree;

//Assuming that this binary search tree is for positive integers and there is no duplicate data in the tree

public class BinarySearchTree {
    private Node root;

    public BinarySearchTree() {
        this.root = null;
    }
    public void insertItem(int item)
    {
        root = insertItem(root,item);
    }
    private Node insertItem(Node root, int item)
    {
        if(root == null)
        {
            root = new Node(item);
        }else if(item < root.getItem())
            root.setLeftChild(insertItem(root.getLeftChild(), item));
        else
            root.setRightChild(insertItem(root.getRightChild(),item));
        return root;
    }
    public Node searchItem(int item) //this function returns the current node in which the item is found. if the item is not found then the current node is null
    {
        Node current = root;
        while(current != null) //iteratively searching the given item
        {
            if(current.getItem() == item)
                break;
            else if(item < current.getItem())
                current = current.getLeftChild();
            else
                current = current.getRightChild();
        }
        if(current != null)
            System.out.println(item + " has been found");
        else
            System.out.println(item + " has not been found");
        return current;
    }
    public int getInOrderSuccessor(int item)
    {
        Node current = searchItem(item); //Getting the node of the given item
        if(current == null)
            return -1;
        if(current.getRightChild() != null) //if the given item has a right sub tree, then in order successor will be the minimum value of that right sub tree
        {
            current = current.getRightChild();
            while (current.getLeftChild() != null) //getting the min value of the right sub tree
                current = current.getLeftChild();
            return current.getItem();
        }else //if the given item does not have a right sub tree, then the in order successor will be the nearest node from the given item, for which the given item is in that node's left sub tree
        {
            Node successor = null;
            Node ancestor = root; //starting from root as there is no "parent" node available to back track from the given node!
            while(ancestor != current)
            {
                if(current.getItem() < ancestor.getItem())
                {
                    successor = ancestor; //if we are going left ,then we are storing that node's reference which may be the successor
                    ancestor = ancestor.getLeftChild();
                }else
                {
                    ancestor = ancestor.getRightChild();
                }
            }
            return successor.getItem();
        }
    }
    public int getInOrderPredecessor(int item)
    {
        Node current = searchItem(item);
        if(current == null)
            return -1;
        if(current.getLeftChild() != null) //if the given item has a left sub tree, then in order predecessor will be the maximum value of that left sub tree
        {
            current = current.getLeftChild();
            while(current.getRightChild() != null)
            {
                current = current.getRightChild();
            }
            return current.getItem();
        }else
        { //if the given item does not have a left sub tree, then the in order predecessor will be the nearest node from that item, for which the item is in the right sub tree
            Node predecessor = null;
            Node ancestor = root; //starting from root node
            while(ancestor != current)
            {
                if(current.getItem() > ancestor.getItem())
                {
                    predecessor = ancestor;
                    ancestor = ancestor.getRightChild();
                }else
                {
                    ancestor = ancestor.getLeftChild();
                }
            }
            return predecessor.getItem();
        }
    }
    public void deleteItem(int item)
    {
        root = deleteItem(root, item);
    }
    private Node deleteItem(Node root, int item)
    {
        if(root == null)
            return root;
        else if(item < root.getItem())
            root.setLeftChild(deleteItem(root.getLeftChild(),item)); //recursively going to the given item to delete it
        else if(item > root.getItem())
            root.setRightChild(deleteItem(root.getRightChild(),item));
        else
        { //This will execute when the item is found!
            if(root.getLeftChild() == null && root.getRightChild() == null)
            {
                root = null; //if the item is a leaf node, then we can simply make it null
            }
            else if(root.getRightChild() == null)
            {
                root = root.getLeftChild(); //if the item does not have a right child, then we can replace the item with its left child
            }else if(root.getLeftChild() == null)
            {
                root = root.getRightChild(); //if the item does not have a left child, then we can replace the item with its right child
            }else
            { //this is the case where the item has 2 children!
                //if we consider the right sub tree, then we can take the minimum value of the right sub tree and replace it with the given item.
                //then if we delete the duplicate item in the right sub tree, then our work will be done
                //this will happen recursively and we'll end up with one of the 3 cases handled above
                Node current = root.getRightChild();
                while(current.getLeftChild() != null)
                    current = current.getLeftChild();
                root.setItem(current.getItem());
                root.setRightChild(deleteItem(root.getRightChild(), current.getItem()));
            }
        }
        return root;
    }
    public int getItemDepth(int item)
    {
        int depth = 0;
        Node current = root;
        while(current != null)
        {
            if(item == current.getItem())
                break;
            else if(item < current.getItem())
                current = current.getLeftChild();
            else
                current = current.getRightChild();
            depth++;
        }
        return current == null ? -1 : depth;
    }
    public int getMinItem()
    {
        if(root == null)
            return -1;

        Node current = root;
        while(current.getLeftChild() != null)
        {
            current = current.getLeftChild();
        }
        return current.getItem();
    }
    public int getMaxItem()
    {
        if(root == null)
            return -1;

        Node current = root;
        while(current.getRightChild() != null)
        {
            current = current.getRightChild();
        }
        return current.getItem();
    }
    public int getHeight()
    {
        return getHeight(root);
    }
    private int getHeight(Node root)
    {
        if(root == null)
            return -1; // height of an empty bst is -1
        return Math.max(getHeight(root.getLeftChild()), getHeight(root.getRightChild())) + 1;
    }
    public void printInOrder()
    {
        printInOrder(root);
    }
    private void printInOrder(Node root)
    {
        if(root == null) return;
        printInOrder(root.getLeftChild());
        System.out.print(root.getItem() + "  ");
        printInOrder(root.getRightChild());
    }
    public void printPreOrder()
    {
        printPreOrder(root);
    }
    private void printPreOrder(Node root)
    {
        if(root == null)
            return;
        System.out.print(root.getItem() + "  ");
        printPreOrder(root.getLeftChild());
        printPreOrder(root.getRightChild());
    }
    public void printPostOrder()
    {
        printPostOrder(root);
    }
    private void printPostOrder(Node root)
    {
        if(root == null)
            return;
        printPostOrder(root.getLeftChild());
        printPostOrder(root.getRightChild());
        System.out.print(root.getItem() + "  ");
    }
    public int getSize()
    {
        return getSize(root);
    }
    private int getSize(Node root)
    {
        if(root == null)
            return 0;
        return getSize(root.getLeftChild()) + getSize(root.getRightChild()) + 1; //1 is for root node
    }
}
