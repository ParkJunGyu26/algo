#include <iostream>
#include <vector>

using namespace std;

int n, k;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n >> k;
	vector<int> coin(n+1);
	vector<int> dp(k+1);
	for (int i = 0; i < n; i++) cin >> coin[i];

	dp[0] = 1;

	for (int i = 0; i < n; i++) {
		// cout << "coin : " << coin[i] << "\n";
		for (int j = coin[i]; j < k+1; j++) {

			dp[j] += dp[j-coin[i]];
			// cout << "j : " << j << "\n";
		}
	}

	// for (int i = 0; i <= k; i++) cout << dp[i] << " ";
	cout << dp[k];
}