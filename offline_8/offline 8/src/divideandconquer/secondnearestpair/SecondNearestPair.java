package divideandconquer.secondnearestpair;

import divideandconquer.Point;
import divideandconquer.mergesort.MergeSort;


public class SecondNearestPair {

    public int minHouse1;   //house index of nearest pair
    public int minHouse2;   //house index of nearest pair
    public int secondMinHouse1; //house index of 2nd nearest pair
    public int secondMinHouse2; //house index of 2nd nearest pair
    public double minDistance;  //minimum distance
    public double secondMinDistance;    //2nd minimum distance

    public SecondNearestPair() {
    }

    public SecondNearestPair(int minHouse1, int minHouse2, int secondMinHouse1, int secondMinHouse2, double minDistance, double secondMinDistance) {
        this.minHouse1 = minHouse1;
        this.minHouse2 = minHouse2;
        this.secondMinHouse1 = secondMinHouse1;
        this.secondMinHouse2 = secondMinHouse2;
        this.minDistance = minDistance;
        this.secondMinDistance = secondMinDistance;
    }


    //This method is called from the main method once. it first sorts the points in ascending order with respect to x coordinates using merge sort [O(nlogn)] and then calls
    // findSecondMinimum which does the rest of the work!
    public SecondNearestPair secondMinimum(Point[] points)
    {
        int n = points.length;
        Point[] Px = points.clone();
        MergeSort.mergeSort(Px, 0, n-1, "x");
        return findSecondMinimum(Px, 0, n);
    }

    //This is a recursive function whose Recurrence Relation is T(n)= 2T(n/2)+O(n)
    public SecondNearestPair findSecondMinimum(Point[] Px, int start, int end)
    {
        int n= end-start;
        //Base case
        if(n <= 3)
        {
            return baseCaseHandler(Px, start, end);
        }
        int mid = n/2;
        Point midPoint = Px[mid];
        Point[] leftIntervalPoints = new Point[mid]; //array of Point for left sub problem
        Point[] rightIntervalPoints = new Point[n-mid]; // array of Point for right sub problem
        int left = 0, right = 0;

        //if the x coordinate of a point is less than or equal to mid point's x and till the left array has enough space, then it goes to left sub problem
        //else it goes to right sub problem
        for(int i=start; i<end; i++)
        {
            if((Double.compare(Px[i].x, midPoint.x) <=0) && left < mid)
            {
                leftIntervalPoints[left++] = Px[i];
            }else
            {
                rightIntervalPoints[right++] = Px[i];
            }
        }
        SecondNearestPair leftInterval = findSecondMinimum(leftIntervalPoints, 0, left);    //recursively calling for left sub problem
        SecondNearestPair rightInterval = findSecondMinimum(rightIntervalPoints,0, right);  //recursively calling for right sub problem

        //Combining the result from left sub problem and right sub problem
        //There are total 4 distances from 2 sub problems, left minimum, left 2nd minimum, right minimum and right 2nd minimum
        //from those 4 distances, we are taking the overall minimum and 2nd minimum distance
        SecondNearestPair ans = minFinder(leftInterval, rightInterval);

        //Now we are merging the Px (which is already sorted with respect to x coordinate) with respect to y coordinate so that we can create the strip
        //Strip is the pair of points where one point is from the left interval and the other from the right interval and there may have a minimum and 2nd minimum distance among the pairs.
        MergeSort.merge(Px, start, mid, end-1, "y");

        //creating the strip where each point has a distance less than second minimum distance from the mid point
        Point[] strip = new Point[n];
        int k=0;
        for(int i=start; i<end; i++)
        {
            if(Double.compare(Math.abs(Px[i].x - midPoint.x) , ans.secondMinDistance) <=0)
                strip[k++] = Px[i];
        }
        //secondNearestStrip method solves the strip points
        return secondNearestStrip(strip, k, ans);
    }


    private SecondNearestPair secondNearestStrip(Point[] strip, int size, SecondNearestPair ans) {
        for(int i=0; i<size; i++)
        {
            for(int j=i+1; j < size && j+i <8; j++)
            {
                double dis = Point.distance(strip[i], strip[j]);
                //if the distance is less than the minimum distance recorded so far, then the previous minimum distance will be the 2nd minimum distance
                if(Double.compare(dis, ans.minDistance) <0)
                {
                    ans.secondMinDistance = ans.minDistance;
                    ans.minDistance = dis;
                    ans.secondMinHouse1 = ans.minHouse1;
                    ans.secondMinHouse2 = ans.minHouse2;
                    ans.minHouse1 = strip[i].index;
                    ans.minHouse2 = strip[j].index;
                }
                // else if the distance is greater than minimum distance and less than 2nd minimum distance, then we update the 2nd minimum distance
                else if(Double.compare(dis , ans.secondMinDistance) <0 && Double.compare(dis, ans.minDistance) !=0)
                {
                    ans.secondMinDistance = dis;
                    ans.secondMinHouse1 = strip[i].index;
                    ans.secondMinHouse2 = strip[j].index;
                }
            }
        }
        return ans;
    }

    //this method finds the overall minimum and 2nd minimum from the results of left and right sub problem
    //p is from left sub problem and q is from right sub problem

    public SecondNearestPair minFinder(SecondNearestPair p, SecondNearestPair q)
    {
        //Assuming that the combined result is the left sub problem's result.
        SecondNearestPair pair = p;
        //comparing the assumption with the result of right sub problem and updating it accordingly.
        if(pair.minDistance > q.minDistance)
        {
            if(pair.minDistance > q.secondMinDistance)
            {
                pair.secondMinDistance = q.secondMinDistance;
                pair.secondMinHouse1 = q.secondMinHouse1;
                pair.secondMinHouse2 = q.secondMinHouse2;
            }else
            {
                pair.secondMinDistance = pair.minDistance;
                pair.secondMinHouse1 = pair.minHouse1;
                pair.secondMinHouse2 = pair.minHouse2;
            }
            pair.minDistance = q.minDistance;
            pair.minHouse1 = q.minHouse1;
            pair.minHouse2 = q.minHouse2;
        }else if(pair.secondMinDistance > q.minDistance && Double.compare(pair.minDistance, q.minDistance) !=0)
        {
            pair.secondMinDistance = q.minDistance;
            pair.secondMinHouse1 = q.minHouse1;
            pair.secondMinHouse2 = q.minHouse2;
        }
        return pair;
    }

    //in the sub problem, if n is 3 then it finds the minimum and 2nd minimum distance among the 3 points
    // if n is 2, then it keeps the distance of 2 points as minimum distance and the 2nd minimum distance is infinity in this case
    //if n is 1, then this method will do nothing
    public SecondNearestPair baseCaseHandler(Point[] Px, int start, int end)
    {
        double min = Double.MAX_VALUE;
        double secondMin = Double.MAX_VALUE;
        int minh1=0;
        int minh2=0;
        int secMinh1=0;
        int secMinh2=0;
        for(int i=start; i<end; i++)
        {
            for(int j=i+1; j<end; j++)
            {
                double dis = Point.distance(Px[i], Px[j]);
                if(Double.compare(dis , min) <0)
                {
                     secondMin = min;
                     min = dis;
                     secMinh1 = minh1;
                     secMinh2 = minh2;
                     minh1 = Px[i].index;
                     minh2 = Px[j].index;
                }else if(Double.compare(dis , secondMin) <0 && Double.compare(dis, min) !=0)
                {
                    secondMin = dis;
                    secMinh1 = Px[i].index;
                    secMinh2 = Px[j].index;
                }
            }
        }
        return new SecondNearestPair(minh1, minh2, secMinh1, secMinh2, min, secondMin);
    }
}
