#include <iostream>
#include <algorithm>
#include <vector>
#include <cstring>
using namespace std;

int n;
vector<int> A;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> n;
    A.resize(n);
    for (int i = 0; i < n; i++) cin >> A[i];

    int answer = 1;
    // 등차 d를 -99 ~ 99까지 모두 시도
    for (int d = -99; d <= 99; d++) {
        // dp[x]: 마지막 원소가 x인 등차수열의 최대 길이
        int dp[101];
        memset(dp, 0, sizeof(dp));
        for (int i = 0; i < n; i++) {
            int prev = A[i] - d;
            if (1 <= prev && prev <= 100)
                dp[A[i]] = max(dp[A[i]], dp[prev] + 1);
            else
                dp[A[i]] = max(dp[A[i]], 1);
            answer = max(answer, dp[A[i]]);
        }
    }
    cout << answer << '\n';
}
