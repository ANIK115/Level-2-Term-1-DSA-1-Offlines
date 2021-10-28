package com.company;

public class Main {
    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.insertItem(15);
        bst.insertItem(10);
        bst.insertItem(20);
        bst.insertItem(5);
        bst.insertItem(25);
        bst.searchItem(20);
        bst.searchItem(25);
        bst.searchItem(1);
        System.out.println("Max: "+bst.getMaxItem());
        bst.printInOrder(bst.getRoot());
//        System.out.println("Pre Order: ");
//        bst.printPreOrder(bst.getRoot());
//        System.out.println("Post Order: ");
//        bst.printPostOrder(bst.getRoot());
        System.out.println("depth of 25: "+bst.getItemDepth(25));
        System.out.println("depth of 15: "+bst.getItemDepth(15));
        System.out.println("depth of 1: "+bst.getItemDepth(1));
        System.out.println("Current Size: "+bst.getSize(bst.getRoot()));


    }
}
