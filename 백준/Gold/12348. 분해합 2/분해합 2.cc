#include <iostream>

using namespace std;

long long n, tmp;

long long solution(long long number) {
	long long total = number;

	while (true) {
		if (number / 10 == 0) {
			total += (number % 10);
			return total;
		}

		total += (number % 10);
		number /= 10;
	}
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n;
	int cnt = 1;
	tmp = n;
	while (true) {
		if (tmp / 10 == 0) break;

		tmp /= 10;
		cnt++;
	}

	long long answer = 0;
	for (long long i = n - 9*cnt; i < n; i++) {
		long long target = solution(i);
		if (target == n) {
			answer = i;
			break;
		}
	}

	cout << answer;
}