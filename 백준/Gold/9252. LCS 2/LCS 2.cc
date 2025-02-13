#include <iostream>
#include <string>
#include <algorithm>
#include <vector>

using namespace std;

string a, b;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> b >> a; // a는 세로, b는 가로
	int n = a.size();
	int m = b.size();
	vector<vector<int>> dp(n+1, vector<int>(m+1));
	string answer = "";

	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= m; j++) {
			if (b[j-1] == a[i-1]) dp[i][j] = dp[i-1][j-1]+1;
			else dp[i][j] = max(dp[i-1][j], dp[i][j-1]);
		}
	}

	int i = n;
	int j = m;
	while (i > 0 && j > 0) {
		if (dp[i][j] == dp[i-1][j]) i--;
		else if (dp[i][j] == dp[i][j-1]) j--;
		else {
			answer += a[i-1];
			i--;
			j--;
		}
	}

	reverse(answer.begin(), answer.end());
	cout << answer.size() << "\n" << answer;
}