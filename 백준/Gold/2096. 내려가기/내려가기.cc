#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

int n;
int graph[100000][3] = {0}; 

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n;

	for (int i = 0; i < n; i++)
		for (int j = 0; j < 3; j++) cin >> graph[i][j];
	
	vector<int> dp(3);
	dp[0] = graph[0][0];
	dp[1] = graph[0][1];
	dp[2] = graph[0][2];

	for (int i = 1; i < n; i++) {
		vector<int> new_dp = dp;

		new_dp[0] = max(dp[0], dp[1]) + graph[i][0];
		new_dp[1] = max(dp[0], max(dp[1], dp[2])) + graph[i][1];
		new_dp[2] = max(dp[1], dp[2]) + graph[i][2];

		dp = new_dp;
	}
	int max_answer = *max_element(dp.begin(), dp.end());
	dp[0] = graph[0][0];
	dp[1] = graph[0][1];
	dp[2] = graph[0][2];

	for (int i = 1; i < n; i++) {
		vector<int> new_dp = dp;

		new_dp[0] = min(dp[0], dp[1]) + graph[i][0];
		new_dp[1] = min(dp[0], min(dp[1], dp[2])) + graph[i][1];
		new_dp[2] = min(dp[1], dp[2]) + graph[i][2];

		dp = new_dp;
	}

	int min_answer = *min_element(dp.begin(), dp.end());

	cout << max_answer << " " << min_answer;
}