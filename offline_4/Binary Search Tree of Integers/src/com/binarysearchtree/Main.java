package com.binarysearchtree;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        while (true)
        {
            System.out.println("Enter an option:\n1. Insert Item\n2. Search Item\n3. Get In Order Successor\n" +
                    "4. Get In Order Predecessor\n5. Delete Item\n6. Get Item Depth\n7. Get Max Item\n" +
                    "8. Get Min Item\n9. Get Height\n10. Print In Order\n11. Print Pre Order\n" +
                    "12. Print Post Order\n13. Get Size\n14. End Program ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if(input.equals("1"))
            {
                System.out.println("Enter an item to insert: ");
                String s = scanner.nextLine();
                int item;
                try{
                    item = Integer.parseInt(s);
                    bst.insertItem(item);
                }catch (Exception e)
                {
                    System.out.println("Input should be an integer!");
                }
            }else if(input.equals("2"))
            {
                System.out.println("Enter an item to search: ");
                String s = scanner.nextLine();
                int item;
                try{
                    item = Integer.parseInt(s);
                    bst.searchItem(item);
                }catch (Exception e)
                {
                    System.out.println("Input should be an integer!");
                }
            }else if(input.equals("3"))
            {
                System.out.println("Enter an item: ");
                String s = scanner.nextLine();
                int item;
                try{
                    item = Integer.parseInt(s);
                    System.out.println("In Order Successor of "+item+" is: "+bst.getInOrderSuccessor(item));
                }catch (Exception e)
                {
                    System.out.println("Input should be an integer!");
                }
            }else if(input.equals("4"))
            {
                System.out.println("Enter an item: ");
                String s = scanner.nextLine();
                int item;
                try{
                    item = Integer.parseInt(s);
                    System.out.println("In Order Predecessor of "+item+" is: "+bst.getInOrderPredecessor(item));
                }catch (Exception e)
                {
                    System.out.println("Input should be an integer!");
                }
            }else if(input.equals("5"))
            {
                System.out.println("Enter an item to delete: ");
                String s = scanner.nextLine();
                int item;
                try{
                    item = Integer.parseInt(s);
                    bst.deleteItem(item);
                }catch (Exception e)
                {
                    System.out.println("Input should be an integer!");
                }
            }else if(input.equals("6"))
            {
                System.out.println("Enter an item to get its depth: ");
                String s = scanner.nextLine();
                int item;
                try{
                    item = Integer.parseInt(s);
                    if(bst.getItemDepth(item) == -1)
                        System.out.println("item: "+item+" does not exist!");
                    else
                        System.out.println("Depth of "+item+" is: "+bst.getItemDepth(item));
                }catch (Exception e)
                {
                    System.out.println("Input should be an integer!");
                }
            }else if(input.equals("7"))
            {
                if(bst.getMaxItem() == -1)
                {
                    System.out.println("Binary Search Tree is Empty!");
                }else {
                    System.out.println("Max item: " + bst.getMaxItem());
                }
            }else if(input.equals("8"))
            {
                if(bst.getMinItem() == -1)
                {
                    System.out.println("Binary Search Tree is Empty!");
                }else {
                    System.out.println("Min item: " + bst.getMinItem());
                }
            }else if(input.equals("9"))
            {
                System.out.println("Height of the Binary Search Tree: "+bst.getHeight());
            }else if(input.equals("10"))
            {
                System.out.println("In order traversal of the Binary Search Tree: ");
                bst.printInOrder();
            }else if(input.equals("11"))
            {
                System.out.println("Pre order traversal of the Binary Search Tree: ");
                bst.printPreOrder();
            }else if(input.equals("12"))
            {
                System.out.println("Post order traversal of the Binary Search Tree: ");
                bst.printPostOrder();
            }else if(input.equals("13"))
            {
                System.out.println("Number of nodes in the Binary Search Tree: "+bst.getSize());
            }else if(input.equals("14"))
            {
                break;
            }else
            {
                System.out.println("Invalid option! Try again please.");
            }
            System.out.println("\n\n\n");
        }
    }
}
