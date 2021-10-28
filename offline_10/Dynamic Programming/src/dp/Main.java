package dp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static long mod = 1000000007;

    public static long waysToGetSum(int n, int[] faces, int sum)
    {
        //Table to store the results of the sub problems
        long[][] subProbTable = new long[n+1][sum+1];
        for(int i=1; i<=faces[0] && i<=sum; i++)
            subProbTable[1][i] = 1;

        for(int i=2; i<=n; i++)
        {
            for(int j=1; j<=sum; j++)
            {
                for(int k=1; k<j && k<=faces[i-1]; k++)
                {
                    subProbTable[i][j] += subProbTable[i-1][j-k];
                    subProbTable[i][j] %= mod;
                }
            }
        }
        return subProbTable[n][sum];
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("Test 6.txt");
        int n,sum;
        Scanner scanner = new Scanner(file);
        n = scanner.nextInt();
        sum = scanner.nextInt();
        int[] faces = new int[n];
        for(int i=0; i<n; i++)
            faces[i] = scanner.nextInt();

        System.out.println("Number of ways: "+waysToGetSum(n, faces, sum));
    }
}
