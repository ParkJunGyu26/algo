#include <iostream>
#include <vector>

using namespace std;

int answer[2];

int max(int a, int b) {
	if (a > b) return a;
	return b;
}

int min(int a, int b) {
	if (a > b) return b;
	return a;
}

int gcd(int b1, int b2) {
	int MAX = max(b1, b2);
	int MIN = min(b1, b2);

	while (MAX % MIN > 0) {
		int tmp = MAX % MIN;
		MAX = MIN;
		MIN = tmp;
	}
	return MIN;
}

int lcm(int b1, int b2) {
	return b1 * b2 / gcd(b1, b2);
}

void ans(int a1, int b1, int a2, int b2) {
	int bottom = lcm(b1, b2); // 분모 -> 최소공배수
	int upper = a1 * (bottom / b1) + a2 * (bottom / b2); // 분자

	answer[0] = upper;
	answer[1] = bottom;

	// cout << "upper : " << upper << endl;
	// cout << "bottom : " << bottom << endl;

	int target = 2;
	while (target <= min(upper, bottom)) {
		if (answer[0] % target == 0 && answer[1] % target == 0) {
			answer[0] /= target;
			answer[1] /= target;
		} else target++;
	}
}

// 두 분모의 최소공배수를 구하기
int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	// a1 / b1
	// a2 / b2
	int a1, b1, a2, b2;
	cin >> a1 >> b1;
	cin >> a2 >> b2;
	ans(a1, b1, a2, b2);
	cout << answer[0] << " " << answer[1];
}