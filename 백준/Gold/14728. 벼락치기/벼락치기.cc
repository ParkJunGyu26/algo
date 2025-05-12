#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int n, t;
    cin >> n >> t;
    
    vector<pair<int, int>> units(n);
    for (int i = 0; i < n; i++) {
        cin >> units[i].first >> units[i].second;
    }
    
    vector<int> dp(t + 1, 0);
    
    for (int i = 0; i < n; i++) {
        int time = units[i].first;
        int score = units[i].second;
        
        for (int j = t; j >= time; j--) {
            dp[j] = max(dp[j], dp[j - time] + score);
        }
    }
    
    cout << dp[t];
    return 0;
}
