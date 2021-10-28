package divideandconquer;

import divideandconquer.secondnearestpair.SecondNearestPair;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        File file = new File("input2.txt");
	    int n;
        Scanner scanner = new Scanner(file);
        n = scanner.nextInt();
        Point[] points = new Point[n];
        for(int i=0; i<n; i++)
        {
            double x = scanner.nextDouble();
            double y = scanner.nextDouble();
            points[i] = new Point(x,y,i);
        }
        SecondNearestPair secondNearestPair = new SecondNearestPair();
        secondNearestPair = secondNearestPair.secondMinimum(points);
        if(secondNearestPair.secondMinHouse1 > secondNearestPair.secondMinHouse2)
        {
            int temp = secondNearestPair.secondMinHouse1;
            secondNearestPair.secondMinHouse1 = secondNearestPair.secondMinHouse2;
            secondNearestPair.secondMinHouse2 = temp;
        }
        System.out.println(secondNearestPair.secondMinHouse1+"  "+secondNearestPair.secondMinHouse2);
        System.out.format("%.4f\n", secondNearestPair.secondMinDistance);
        System.out.println(secondNearestPair.minDistance);
    }
}
