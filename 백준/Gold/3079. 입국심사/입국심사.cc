#include <iostream>
#include <algorithm>
#include <vector>

#define MAX_SIZE 10000000000

using namespace std;

long long n, m;

bool check_count(long long target, vector<long long> &vec) {
	long long cnt = 0;
	for (auto num : vec)
		cnt += (target / num);
	
	return cnt >= m;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n >> m;
	vector<long long> vec(n);
	for (int i = 0; i < n; i++)
		cin >> vec[i];
	
	sort(vec.begin(), vec.end());
	
	long long left = 0;
	long long right = vec[0] * m;

	while (left < right) {
		long long mid = (left + right) / 2;

		if (check_count(mid, vec)) {
			right = mid;
		} else {
			left = mid+1;
		}
	}

	cout << left;
}