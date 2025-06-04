#include <iostream>
#include <map>
#include <algorithm>
#include <vector>

using namespace std;

int n;
vector<int> A;

int main() {
	ios_base::sync_with_stdio(false);
	cout.tie(NULL);
	cin.tie(NULL);

	cin >> n;
	A.resize(n);
	for (int i = 0; i < n; i++) cin >> A[i];

	int answer = 0;
	for (int i = -99; i <= 99; i++) {
		map<int, int> m;

		for (int j = 0; j < n; j++) {
			if (m.find(A[j] - i) == m.end()) m[A[j]] = 1;
			else m[A[j]] = max((m[A[j] - i] + 1), m[A[j]]);
		}

		for (auto mm : m) answer = max(answer, mm.second);
	}

	cout << answer;
}

/*

90 91 92 80 81 82 83 93 94 95


*/