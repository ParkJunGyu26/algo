#include <iostream>

using namespace std;

int n, k;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n >> k;
	int prefix_sum = n - ((k+1)*k/2);

	if (prefix_sum < 0) {
		cout << -1;
	} else {
		if (prefix_sum % k == 0) cout << k-1;
		else cout << k;
	}
}