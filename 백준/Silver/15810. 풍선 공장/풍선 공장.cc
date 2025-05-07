#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

long long n, m;
vector<long long> vec;

bool check(long long target) {
	long long cnt = 0;
	for (int i = 0; i < vec.size(); i++) {
		cnt += (target / vec[i]);
		if (cnt >= m) return true;
	}

	return false;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n >> m;
	vec.resize(n);
	for (int i = 0; i < n; i++) cin >> vec[i];

	long long left = 1;
	long long right = *max_element(vec.begin(), vec.end()) * m +1;

	while (left < right) {
		long long mid = (left + right) / 2;

		if (check(mid)) {
			right = mid;
		} else {
			left = mid+1;
		}
	}

	cout << left;
}