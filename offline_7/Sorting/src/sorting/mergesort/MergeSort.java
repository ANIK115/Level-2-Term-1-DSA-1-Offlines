package sorting.mergesort;

public class MergeSort {

    public static void merge(int[] num, int l, int m, int r)
    {
        int len1 = m-l+1;
        int len2 = r-m;

        int[] leftSubArray = new int[len1];
        int[] rightSubArray = new int[len2];

        for(int i=0; i<len1; i++)
            leftSubArray[i] = num[l+i];
        for(int i=0; i<len2; i++)
            rightSubArray[i] = num[i+m+1];

        int i,j,k;
        for(i=0,j=0,k=l; i<len1 && j<len2; )
        {
            if(leftSubArray[i] <= rightSubArray[j])
            {
                num[k++] = leftSubArray[i++];
            }else
            {
                num[k++] = rightSubArray[j++];
            }
        }
        while (i < len1)
        {
            num[k++] = leftSubArray[i++];
        }
        while(j < len2)
        {
            num[k++] = rightSubArray[j++];
        }
    }
    public static void mergeSort(int[] num, int left, int right)
    {
        if(left < right)
        {
            int mid = (left+right)/2;
            mergeSort(num, left, mid);
            mergeSort(num, mid+1, right);
            merge(num, left, mid, right);
        }
    }
}
