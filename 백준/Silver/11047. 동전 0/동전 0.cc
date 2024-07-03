#include <iostream>
#include <vector>

using namespace std;

int main()
{
	int N, K, ans;
	vector<int> coin;

	cin >> N >> K;

	for (int i = 0; i < N; i++) {
		int t;
		cin >> t;
		coin.push_back(t);
	}

	ans = 0;
	for (int i = N-1; i >= 0; i--) {
		if (K / coin[i]) {
			ans += K / coin[i];
			K %= coin[i];
		}
	}

	cout << ans;

	return 0;
}