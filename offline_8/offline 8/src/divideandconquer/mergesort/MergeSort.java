package divideandconquer.mergesort;

import divideandconquer.Point;

public class MergeSort {

    public static void merge(Point[] points, int l, int m, int r, String compare)
    {
        int len1 = m-l+1;
        int len2 = r-m;

        Point[] leftSubArray = new Point[len1];
        Point[] rightSubArray = new Point[len2];

        for(int i=0; i<len1; i++)
            leftSubArray[i] = points[l+i];
        for(int i=0; i<len2; i++)
            rightSubArray[i] = points[i+m+1];

        int i,j,k;
        for(i=0,j=0,k=l; i<len1 && j<len2; )
        {
            if(compare.equalsIgnoreCase("x"))
            {
                if(leftSubArray[i].x <= rightSubArray[j].x)
                {
                    points[k++] = leftSubArray[i++];
                }else
                {
                    points[k++] = rightSubArray[j++];
                }
            }else
            {
                if(leftSubArray[i].y <= rightSubArray[j].y)
                {
                    points[k++] = leftSubArray[i++];
                }else
                {
                    points[k++] = rightSubArray[j++];
                }
            }

        }
        while (i < len1)
        {
            points[k++] = leftSubArray[i++];
        }
        while(j < len2)
        {
            points[k++] = rightSubArray[j++];
        }
    }
    public static void mergeSort(Point[] points, int left, int right, String compare)
    {
        if(left < right)
        {
            int mid = (left+right)/2;
            mergeSort(points, left, mid, compare);
            mergeSort(points, mid+1, right, compare);
            merge(points, left, mid, right, compare);
        }
    }
}