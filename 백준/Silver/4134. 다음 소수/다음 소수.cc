#include <iostream>
#include <cmath>

#define MAX 4LL * 1000000000

using namespace std;

bool ansCheck(long long target) {
	for (long long number = 2; number <= (long long)sqrt(target)+1; number++) {
		if (target % number == 0) {
			return false;
		}
	}
	return true;
}

// n <= 4*10^9 -> 완탐(에라토스테네스의 체)안됨
// 제곱근 이용해서 풀자.
// 아니다 매개변수 탐색에서 제일 왼쪽 값 차기로 하자
int main() {
	int t;
	cin >> t;

	for (int i = 0; i < t; i++) {
		long long n;
		cin >> n;

		if (n == 1 || n == 0) cout << 2 << '\n';
		else if (n == 2) cout << 2 << '\n';
		else{

		for (long long num = n; num < n+MAX; num++) {
			if (ansCheck(num)) {
				cout << num << "\n";
				break;
			}
		}}
	}
}