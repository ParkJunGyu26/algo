#include <iostream>
#include <vector>

using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int t;
    cin >> t;
    
    while (t--) {
        int n, m;
        cin >> n;
        
        vector<int> coins(n);
        for (int i = 0; i < n; i++) cin >> coins[i];
        
        cin >> m;
        
        vector<long long> dp(m + 1, 0);
        dp[0] = 1;
        
        for (int i = 0; i < n; i++) {
            for (int j = coins[i]; j <= m; j++) dp[j] += dp[j - coins[i]];
        }
        
        cout << dp[m] << "\n";
    }
    
    return 0;
}
