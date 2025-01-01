#include <iostream>
#include <vector>
#include <climits>
#include <algorithm>

using namespace std;

int n;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n;
	vector<vector<int>> rgb(n, vector<int> (3));
	for (int i = 0; i < n; i++) 
		for (int j = 0; j < 3; j++) cin >> rgb[i][j];
	
	int answer = INT_MAX;
	vector<int> dp(3);

	// r
	for (int i = 1; i < n; i++) {
		vector<int> new_dp(3);
		vector<int> new_rgb(3);
		for (int j = 0; j < 3; j++) new_rgb[j] = rgb[i][j];

		if (i == 1) {
			new_dp[0] = INT_MAX;
			new_dp[1] = rgb[0][0] + new_rgb[1];
			new_dp[2] = rgb[0][0] + new_rgb[2];
		} else if (i == n-1) {
			new_dp[0] = INT_MAX;
			new_dp[1] = min(dp[0], dp[2]) + new_rgb[1];
			new_dp[2] = min(dp[1], dp[0]) + new_rgb[2];
		} else {
			new_dp[0] = min(dp[1], dp[2]) + new_rgb[0];
			new_dp[1] = min(dp[0], dp[2]) + new_rgb[1];
			new_dp[2] = min(dp[1], dp[0]) + new_rgb[2];
		}
		dp = new_dp;
	}

	answer = *min_element(dp.begin(), dp.end());
	dp[0] = dp[1] = dp[2] = 0;
	// g
	for (int i = 1; i < n; i++) {
		vector<int> new_dp(3);
		vector<int> new_rgb(3);
		for (int j = 0; j < 3; j++) new_rgb[j] = rgb[i][j];

		if (i == 1) {
			new_dp[1] = INT_MAX;
			new_dp[0] = rgb[0][1] + new_rgb[0];
			new_dp[2] = rgb[0][1] + new_rgb[2];
		} else if (i == n-1) {
			new_dp[1] = INT_MAX;
			new_dp[0] = min(dp[1], dp[2]) + new_rgb[0];
			new_dp[2] = min(dp[1], dp[0]) + new_rgb[2];
		} else {
			new_dp[0] = min(dp[1], dp[2]) + new_rgb[0];
			new_dp[1] = min(dp[0], dp[2]) + new_rgb[1];
			new_dp[2] = min(dp[1], dp[0]) + new_rgb[2];
		}
		dp = new_dp;
	}

	answer = min(answer, *min_element(dp.begin(), dp.end()));
	// b
	dp[0] = dp[1] = dp[2] = 0;
	for (int i = 1; i < n; i++) {
		vector<int> new_dp(3);
		vector<int> new_rgb(3);
		for (int j = 0; j < 3; j++) new_rgb[j] = rgb[i][j];

		if (i == 1) {
			new_dp[2] = INT_MAX;
			new_dp[1] = rgb[0][2] + new_rgb[1];
			new_dp[0] = rgb[0][2] + new_rgb[0];
		} else if (i == n-1) {
			new_dp[2] = INT_MAX;
			new_dp[1] = min(dp[0], dp[2]) + new_rgb[1];
			new_dp[0] = min(dp[1], dp[2]) + new_rgb[0];
		} else {
			new_dp[0] = min(dp[1], dp[2]) + new_rgb[0];
			new_dp[1] = min(dp[0], dp[2]) + new_rgb[1];
			new_dp[2] = min(dp[1], dp[0]) + new_rgb[2];
		}
		dp = new_dp;
	}
	answer = min(answer, *min_element(dp.begin(), dp.end()));

	cout << answer;
}