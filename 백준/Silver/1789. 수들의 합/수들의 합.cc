#include <iostream>
using namespace std;
int main(){
    cin.tie(0);
    cout.tie(0);
    ios::sync_with_stdio(0);
    long long S, sum = 0;
    int N = 1;
    cin >> S;
    while ( 1 ){
        sum += N;
        if (sum > S) break;
        N++;
    }
    cout << N - 1;

    return 0;
}
