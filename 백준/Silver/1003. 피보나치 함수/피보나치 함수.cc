#include <iostream>
#include <vector>

using namespace std;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	int t, n;
	cin >> t;
	for (int _ = 0; _ < t; _++) {
		cin >> n;
		vector<pair<long long, long long>> dp;
		dp.push_back({1, 0});
		dp.push_back({0, 1});
		for (int i = 2; i <= n; i++)
			dp.push_back({dp[i-1].first+dp[i-2].first, dp[i-1].second+dp[i-2].second});

		cout << dp[n].first << " " << dp[n].second << "\n";
	}
}

/*
	0	1
0 : 1	0
1 : 0	1
2 : 1	1
3 : 1	2
4 : 2	3
5 : 3	5
6 : 5	8

*/