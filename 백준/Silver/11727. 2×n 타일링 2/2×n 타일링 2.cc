#include <iostream>
#include <vector>

using namespace std;

int n;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n;

	vector<int> dp;
	dp.push_back(0);
	dp.push_back(1);
	dp.push_back(3);
	dp.push_back(5);

	for (int i = 4; i <= n; i++)
		dp.push_back((dp[i-2]*2+dp[i-1])%10007);
	
	cout << dp[n];
}