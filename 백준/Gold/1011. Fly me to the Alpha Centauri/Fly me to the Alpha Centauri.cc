#include <iostream>
#include <climits>

using namespace std;

long long t, x, y;

int binary_check(long long mid, long long target) {
	
	if (mid * (mid-1) + 1 == target && mid >= 2 || target == 1 && mid == 1) {
		return 1;
	}

	if (mid * (mid-1) + 1 < target) return 2;

	return 3;
}

int solve(long long target) {
	if (target == 1) return 1;
	if (target == 2) return 2;

	long long left = 1;
	long long right = INT_MAX;

	long long index = -1;

	while (left <= right) {
		long long mid = (left + right) / 2;

		if (binary_check(mid, target) == 1) {
			index = mid;
			break;
		} else if (binary_check(mid, target) == 2) {
			left = mid + 1;
		} else {
			right = mid - 1;
		}
	}

	if (index == -1) { // 군수열 범위에 없는 경우
		if (left > right) {
			int tmp = left;
			left = right;
			right = tmp;
		}
		int MIN = (left*2-1);

		if (left*(left-1)+1 + left <= target) return MIN+1;
		return MIN;
	}
	// 군수열 범위에 있는 경우
	return (index*2-1);
}

// 방문처리도 하면 안되네.. 2^31 이라.. -> 21억..

// 계차수열 An = 1 + n(n-1) (n >= 2)
// n == 1 -> 1
int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> t;
	for (int _ = 0; _ < t; _++) {
		cin >> x >> y;

		cout << solve(y-x) << "\n";
	}
}

/*

숫자차이	  값
1			1
2			2
3			3
4			3
5			4
6			4
7			5
8			5
9			5
10			6	
11			6
12			6
13			7

*/