package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ArithmeticExpression a=new ArithmeticExpression();
        String stream = "";
        while(true)
        {
            Scanner scanner = new Scanner(System.in);
            stream = scanner.nextLine();
            if(stream.equals("end"))
                break;
            a.calculation(stream);
        }
    }
}
