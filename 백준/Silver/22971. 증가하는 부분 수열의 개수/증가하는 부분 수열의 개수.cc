#include <iostream>
#include <vector>

using namespace std;

int n;
vector<int> vec;
vector<long long> dp;

int main() {
	ios_base::sync_with_stdio(false);cout.tie(NULL);cin.tie(NULL);
	cin >> n;
	vec.resize(n);
	dp.resize(n+1, 1);
	dp[0] = 0;
	for (int i = 0; i < n; i++) cin >> vec[i];
	for (int i = 1; i < n; i++) {
		for (int j = i+1; j <= n; j++) {
			if (vec[j-1] > vec[i-1]) {
				dp[j] += dp[i];
				dp[j] %= 998244353;
			}
		}
	}
	for (int i = 1; i <= n; i++) cout << dp[i] << " ";
}