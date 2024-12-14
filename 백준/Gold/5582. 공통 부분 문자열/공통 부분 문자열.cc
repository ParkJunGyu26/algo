#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
	string a, b;
	cin >> a >> b;

	vector<vector<int>> dp(a.size()+1, vector<int>(b.size()+1, 0));
	for (int i = 1; i <= a.size(); i++) {
		for (int j = 1; j <= b.size(); j++) {
			if (a[i-1] == b[j-1]) {
				dp[i][j] = dp[i-1][j-1] +1;
			}
		}
	}

	int answer = 0;
	for (int i = 1; i <= a.size(); i++) {
		for (int j = 1; j <= b.size(); j++) answer = max(answer, dp[i][j]);
	}

	cout << answer;
}