package sorting;

import sorting.mergesort.MergeSort;
import sorting.quicksort.QuickSort;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args)  {
        while(true)
        {
            Scanner scanner = new Scanner(System.in);
            Random random = new Random();
            System.out.println("How many numbers?: ");
            int n = scanner.nextInt();
            scanner = new Scanner(System.in);
            System.out.println("Choose an order: 1) Ascending 2) Descending 3) Random: ");
            String order = scanner.nextLine();

            int bound = 40;
            int[] numbers = new int[n];

            if (order.equalsIgnoreCase("1")) {
                numbers[0] = (int) (random.nextFloat() * bound);
                for (int i = 1; i < n; i++) {
                    numbers[i] = numbers[i - 1] + (int) (random.nextFloat() * bound);
                }
            } else if (order.equalsIgnoreCase("2")) {
                numbers[0] = (int) (random.nextFloat() * (n));
                for (int i = 1; i < n; i++) {
                    numbers[i] = numbers[i - 1] - (int) (random.nextFloat() * bound);
                }
            } else {
                for (int i = 0; i < n; i++) {
                    numbers[i] = random.nextInt();
                }
            }
            int[] numbers2 = numbers.clone();
            System.out.println("Numbers generated are :------------------------------------- ");
            for(int x: numbers)
            {
                System.out.print(x+"    ");
            }
            System.out.println("\n\n\n");


            long start1 = System.nanoTime();
            MergeSort.mergeSort(numbers, 0, n - 1);
            long end1 = System.nanoTime();
            long mergeSortTime = end1 - start1;
            System.out.println("Merge sort time " + mergeSortTime / 1000000.0 + " milli seconds");

            long start2 = System.nanoTime();
            QuickSort.quickSort(numbers2, 0, n - 1);
            long end2 = System.nanoTime();
            long quickSortTime = end2 - start2;
            System.out.println("Quick sort time at " + quickSortTime / 1000000.0 + " milli seconds\n\n\n");

            System.out.println("Sorted elements by Merge Sort\t\tSorted elements by Quick Sort\n");
            for(int i=0; i<n; i++)
            {
                System.out.format("%20d %35d", numbers[i], numbers2[i]);
                System.out.println();
            }
        }
    }
}
