#include <iostream>

using namespace std;

unsigned long long n, k;

unsigned long long answer_check(unsigned index) {
	return ((n+2-index)*(index));
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n >> k;

	unsigned long long left, right;
	left = 0;
	right = n/2+1;

	while (left <= right) {
		unsigned long long mid = (left + right) / 2;

		if (answer_check(mid) == k) {
			cout << "YES";
			return 0;
		} else if (answer_check(mid) > k) {
			right = mid-1;
		} else {
			left = mid+1;
		}
	}

	cout << "NO";
	return 0;
}