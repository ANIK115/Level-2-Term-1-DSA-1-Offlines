#include<iostream>
#include<algorithm>
using namespace std;

int main()
{
    int n,k;
    cin>>n>>k;
    long long prices[n];
    for(int i=0; i<n; i++)
        cin>> prices[i];
    sort(prices, prices+n, greater<long long>());
    long long minimum_cost=0;
    int count = 0;
    for(int i=0; i<n; i++)
    {
        minimum_cost += prices[i]*(count+1);
        if((i+1)%k==0)
            count++;
    }
    cout << minimum_cost << endl;
    return 0;
}
