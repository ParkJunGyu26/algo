#include <iostream>
#include <algorithm>

using namespace std;

long long n, k;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n >> k;

	long long left = 1;
	long long right = k;

	long long answer = -1;
	while (left <= right) {
		long long mid = (left + right) / 2;
		int cnt = 0;

		for (int i = 1; i <= n; i++) cnt += (min(i*n, mid-1) / i);

		if (cnt < k){
			left = mid+1;
			answer = mid;
		} else {
			right = mid-1;
		}
	}

	cout << answer;
}