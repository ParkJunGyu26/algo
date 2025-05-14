#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int n, k;
vector<int> coins, dp;
vector<bool> visited;

int main() {
	ios_base::sync_with_stdio(false);cin.tie(NULL);cout.tie(NULL);

	cin >> n >> k;
	coins.resize(n);
	visited.resize(100001);
	dp.resize(k+1, 20000);
	for (int i = 0; i < n; i++) {
		cin >> coins[i];
		if (!visited[coins[i]]) {
			dp[coins[i]] = 1;
			visited[coins[i]] = true;
		}
	}
	sort(coins.begin(), coins.end());

	dp[0] = 1;
	for (int i = 0; i < n; i++) {
		for (int j = coins[i]+1; j < k+1; j++) {
			dp[j] = min(dp[j], dp[j-coins[i]] +1);
		}
	}

	if (dp[k] == 20000) cout << -1;
	else cout << dp[k];
}