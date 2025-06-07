#include <iostream>

using namespace std;

int n, k;
int dp[201][201];

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n >> k;

	for (int i = 0; i <= k; i++) dp[0][i] = 1;
	for (int i = 1; i <= n; i++) dp[i][1] = 1;

	for (int i = 1; i <= n; i++) {
		for (int j = 2; j <= k; j++) {
			dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % 1000000000;
		}
	}

	cout << dp[n][k];
}