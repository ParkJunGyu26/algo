#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int n;
vector<int> A, answer, dp;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n;
	A.resize(n);
	dp.resize(n, 1);
	for (int i = 0; i < n; i++) cin >> A[i];

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < i; j++) {
			if (A[i] > A[j]) {
				dp[i] = max(dp[i], dp[j]+1);
			}
		}
	}

	int L = *max_element(dp.begin(), dp.end());
	cout << L << "\n";

	for (int i = n-1; i >= 0; i--) {
		if (dp[i] == L) {
			answer.push_back(A[i]);
			L--;
		}
	}

	sort(answer.begin(), answer.end());

	for (auto a : answer) cout << a << " ";
}