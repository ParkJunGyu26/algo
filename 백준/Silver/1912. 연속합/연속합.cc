#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int n;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n;
	vector<int> v(n);
	vector<int> dp(n+1);
	for (int i = 0; i < n; i++) cin >> v[i];

	for (int i = 1; i <= n; i++) {
		if (v[i-1] + dp[i-1] <= 0) dp[i] = 0;
		else dp[i] = v[i-1] + dp[i-1];

		// cout << "v[i-1] : " << v[i-1] << "\n";
		// cout << "dp[i-1] : " << dp[i-1] << "\n";
		// cout << "i : " << i << ", dp[i] : " << dp[i] << "\n";
		// cout << "---\n";
	}

	// for (int i = 0; i <= n; i++) cout << dp[i] << " ";
	// cout << "\n";

	int answer = *max_element(v.begin(), v.end());
	for (int i = 0; i <= n; i++) {
		if (dp[i] == 0) continue;
		if (answer < dp[i]) answer = dp[i];
	}

	cout << answer;
}