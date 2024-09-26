#include <iostream>

using namespace std;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	int t;
	cin >> t;

	for (int i = 0; i < t; i++) {
		unsigned long long n, m;
		cin >> n >> m;

		if (m / 2 < n) n = m-n;

		unsigned long long ans = 1;
		unsigned long long bottom = 1;
		
		while (bottom <= n) {
			ans *= m;
			ans /= bottom;
			m--;
			bottom++;
		}

		cout << ans << "\n";
	}
}