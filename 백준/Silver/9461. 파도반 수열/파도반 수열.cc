#include <iostream>
#include <vector>

using namespace std;

long long t, n;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> t;
	for (int _ = 0; _ < t; _++) {
		cin >> n;
		vector<long long> dp(5);
		dp[0] = 1; dp[1] = 1; dp[2] = 1; dp[3] = 2; dp[4] = 2;

		for (int i = 5; i <= n; i++) {
			dp.push_back(dp[i-1]+dp[i-5]);
		}
		cout << dp[n-1] << "\n";
	}
}