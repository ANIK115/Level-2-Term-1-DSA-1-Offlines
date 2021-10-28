package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
       while(true)
       {
           Scanner scanner = new Scanner(System.in);
           String stream = scanner.nextLine();
           if(stream.equals(""))
               break;
           ApplicationOfQueue.stringManipulation(stream);
       }
    }
}
