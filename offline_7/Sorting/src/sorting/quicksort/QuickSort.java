package sorting.quicksort;

public class QuickSort {

    public static void swap(int[] num, int i, int j)
    {
        int temp = num[i];
        num[i] = num[j];
        num[j] = temp;
    }

    private static int partition(int[] num, int left, int right)
    {
        int pivot = num[right];

        int i = left-1;
        for(int j = left; j < right; j++)
        {
            if (num[j] < pivot)
            {
                i++;
                swap(num, i, j);
            }
        }
        i++;
        swap(num, i, right); //Now ith element is in its correct place
        return i;
    }

    public static void quickSort(int[] num, int left, int right)
    {
        if (left < right)
        {
            int i = partition(num, left, right);
            quickSort(num, left, i - 1);
            quickSort(num, i + 1, right);
        }
    }
}
