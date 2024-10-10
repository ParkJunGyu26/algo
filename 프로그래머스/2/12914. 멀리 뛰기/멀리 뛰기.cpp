#include <string>
#include <vector>

using namespace std;

// long long perSum(int twoCnt, int oneCnt) {
//     int len = twoCnt + oneCnt;
    
//     int two_per = 1;
//     // 2에 대한 순열 개수
//     for (int i = len; i > len-twoCnt; i--)
//         two_per *= i;
        
//     int one_per = 1;
//     // 1에 대한 순열 개수
//     for (int i = len; i > len-oneCnt; i--)
//         one_per *= i;
        
//     return (two_per + one_per);
// }

// long long countNum(int twoCnt, int oneCnt) {
//     long long result = 0;
//     while (twoCnt) {
//         result += perSum(twoCnt, oneCnt);
        
//         twoCnt--;
//         oneCnt += 2;
//     }
//     return result;
// }

// dfs는 시간 초과
// 순열x -> dp
long long solution(int n) {
    vector<long long> dp;
    dp.push_back(0); dp.push_back(1); dp.push_back(2);
    for (int i = 3; i <= n; i++) {
        int tmp = dp[i-2] + dp[i-1];
        tmp %= 1234567;
        dp.push_back(tmp);
    }
    
    return dp[n];
}

// 2의 개수에 따라
// 2 = 0 + 2 = 1 + 2 = 2 + ... 2 = n/2

// 4 = (1+1+1+1) / (1+1+2) / (2+2) = 1 + (3C1) + 1 = 5
// 5 = (1+1+1+1+1) / (1+1+1+2) / (1+2+2) = 1 + (4C1) + (3C1) = 8