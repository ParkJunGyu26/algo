#include <iostream>
#include <algorithm>

using namespace std;

int n, k;
vector<pair<int, int>> vec; // (w, v)
vector<vector<int>> dp;

// 중복 없이 각각 하나씩 뽑아서 배낭에 넣을 수 있는 가치합의 최대합
int main() {
	ios_base::sync_with_stdio(false);cin.tie(NULL);cout.tie(NULL);

	cin >> n >> k;
	dp.resize(n+1, vector<int>(k+1));
	vec.resize(n);

	for (int i = 0; i < n; i++) cin >> vec[i].first >> vec[i].second;

	for (int i = 1; i <= n; i++) {

		for (int j = 1; j <= k; j++) {

			if (j - vec[i-1].first >= 0) {
				dp[i][j] = max(dp[i-1][j], dp[i-1][j - vec[i-1].first] + vec[i-1].second);
			} else {
				dp[i][j] = dp[i-1][j];
			}
		}
	}

	cout << dp[n][k];
}