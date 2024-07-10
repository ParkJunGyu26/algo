#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int abs(int first, int second)
{
	if (first >= second) return (first - second);
	return (second - first);
}

int main()
{
	int T, n;
	cin >> T;

	int sosu[10001] = {0};
	sosu[0] = 1;
	sosu[1] = 1;

	// 에라토스테네스의 체
	// 값이 0인 경우가 소수임
	for (int i = 2; i < 10001; i++) {
		for (int j = i*2; j < 10001; j += i) {
			if (sosu[j] == 0) sosu[j] = 1;
		}
	}

	for (int i = 0; i < T; i++) {
		cin >> n;
		vector<pair<int, int>> ans;

		// 쌍 저장
		for (int j = 0; j < n; j++) {
			if (sosu[j] == 0 && sosu[n-j] == 0) {
				ans.push_back(make_pair(j, n-j));
			}
		}

		// 정답 탐색
		int diff = 10000;
		pair<int, int> answer;
		for (auto p : ans) {
			if (diff > abs(p.first, p.second)) {
				answer = p;
				diff = abs(p.first, p.second);
			}
		}

		cout << answer.first << " " << answer.second << endl;
	}

	return 0;
}