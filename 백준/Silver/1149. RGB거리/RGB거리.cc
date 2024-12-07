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
	vector<vector<int>> graph(n, vector<int>(3));
	for (int i = 0; i < n; i++)
		for (int j = 0; j < 3; j++) cin >> graph[i][j];
	
	vector<vector<int>> dp(n, vector<int>(3));
	for (int i = 0; i < 3; i++)
		dp[0][i] = graph[0][i];
	
	for (int i = 1; i < n; i++) {
		dp[i][0] = graph[i][0] + min(dp[i-1][1], dp[i-1][2]);
		dp[i][1] = graph[i][1] + min(dp[i-1][0], dp[i-1][2]);
		dp[i][2] = graph[i][2] + min(dp[i-1][0], dp[i-1][1]);
	}

	cout << min(dp[n-1][0], min(dp[n-1][1], dp[n-1][2]));
}