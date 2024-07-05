#include <string>
#include <vector>

using namespace std;

int solution(int n) {
    int answer = 0;
    vector<int> dp(n+1, 0);
    
    dp[1] = 1;
    for (int i = 2; i < n+1; i++) {
        dp[i] = dp[i-1] + dp[i-2];
        dp[i] = dp[i] % 1234567;
    }
    answer = dp[n];
    
    return answer;
}