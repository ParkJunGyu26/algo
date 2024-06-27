#include <iostream>
#include <vector>

using namespace std;

int main()
{
	ios_base :: sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	int n, k, p;
	cin >> n >> k >> p;

	vector<int> bread;
	for (int i = 0; i < n*k; i++) {
		int tmp;
		cin >> tmp;
		bread.push_back(tmp);
	}

	int ans = 0;
	for (int i = 0; i < n; i++) {
		int fail = 0;
		for (int j = i*k; j < i*k+k; j++) {
			if (bread[j] == 0) fail++;
		}
		if (fail < p) ans++;
	}

	cout << ans;

	return 0;
}