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
	vector<int> vec(n);
	for (int i = 0; i < n; i++) cin >> vec[i];

	vector<int> left_dp(n, 1);
	vector<int> right_dp(n, 1);

	// 왼쪽에서 오른쪽 : 점점 증가
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < i; j++) {
			if (vec[i] > vec[j]) left_dp[i] = max(left_dp[i], left_dp[j]+1);
		}
	}

	// 오른쪽에서 왼쪽 : 점점 증가
	for (int i = n-1; i >= 0; i--) {
		for (int j = n-1; j >= i; j--) {
			if (vec[i] > vec[j]) right_dp[i] = max(right_dp[i], right_dp[j]+1);
		}
	}

	int answer = 0;
	for (int i = 0; i < n; i++) answer = max(answer, left_dp[i] + right_dp[i]);

	cout << answer-1;
}