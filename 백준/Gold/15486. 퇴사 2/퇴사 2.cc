#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cout.tie(NULL);
    cin.tie(NULL);

    int n;
    cin >> n;
    vector<pair<int, int>> vec(n);
    for (int i = 0; i < n; i++) cin >> vec[i].first >> vec[i].second;

    vector<int> dp(n+2, 0);

    for (int i = 0; i < n; i++) {
        dp[i+1] = max(dp[i+1], dp[i]);
		
        if (i + vec[i].first <= n) {
            dp[i + vec[i].first] = max(dp[i + vec[i].first], dp[i] + vec[i].second);
        }
    }

    cout << max(dp[n], dp[n+1]);
}
