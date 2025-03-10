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

	vector<int> dp;
	dp = vec;
	for (int i = 0; i < n; i++) {
		int hubo = 0;
		for (int j = 0; j < i; j++) {
			if (vec[i] > vec[j]) {
				hubo = max(hubo, dp[j]);
			}
		}
		dp[i] += hubo;
	}

	cout << *max_element(dp.begin(), dp.end());
}