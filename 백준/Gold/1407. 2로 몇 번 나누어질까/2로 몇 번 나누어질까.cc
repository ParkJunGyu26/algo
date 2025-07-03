#include <iostream>

using namespace std;

long long A, B;

long long two_power(long long target) {
	long long answer = 0;
	long long mul = 1;

	while (target > 0) {
		answer += ((target + 1) / 2) * mul;
		mul *= 2;
		target /= 2;
	}

	return answer;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> A >> B;
	cout << two_power(B) - two_power(A-1);
}