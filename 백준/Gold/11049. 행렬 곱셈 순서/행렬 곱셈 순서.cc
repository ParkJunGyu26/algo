#include <iostream>
#include <vector>
#include <climits>
#include <algorithm>

using namespace std;

int n;
vector<pair<int, int>> vec;
vector<vector<int>> dp;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    
    cin >> n;
    vec.resize(n);
	dp.resize(n+1, vector<int>(n+1));
    for (int i = 0; i < n; i++) cin >> vec[i].first >> vec[i].second;
    
    for (int i = 1; i < n; i++) {
		dp[i][i+1] = vec[i-1].first * vec[i].first * vec[i].second;
		// cout << dp[i][i+1] << "\n";
	}
    
    for (int gap = 2; gap < n; gap++) {
		// cout << "gap : " << gap << "\n";
        for (int start = 1; start <= n - gap; start++) {
			dp[start][start+gap] = INT_MAX;
			// cout << "start : " << start << "\n";
            for (int end = start; end < start+gap; end++) {
				// cout << "end : " << end << ", start + gap : " << start + gap << "\n";
                int left = dp[start][end];
                int right = dp[end+1][start + gap];

				// cout << "left : " << left << ", right : " << right << "\n";
				// cout << "vec[start-1].first * vec[end].first * vec[start + gap-1].second : " << vec[start-1].first * vec[end].first * vec[start + gap-1].second << "\n";

                dp[start][start+gap] = min(left + right + (vec[start-1].first * vec[end].first * vec[start + gap-1].second), dp[start][start+gap]);
				// cout << "dp[start][start+gap] : " << dp[start][start+gap] << "\n--------------\n";
            }
			// cout << "start end!!!!!!!!!!!!\n";
        }
		// cout << "gap end!!!!!!!!!!!!!\n";
    }
    
    cout << dp[1][n];
}