#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

int n;
vector<int> vec;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n;
	vec.resize(n);
	for (int i = 0; i < n; i++) cin >> vec[i];

	int odd = 0;
	int even = 0;
	long long answer = 0;
	for (int i = 0; i < n; i++) if (vec[i] % 2 == 0) even++; else odd++;

	// 짝수 모두 왼쪽
	queue<int> q;
	for (int i = 0; i < n; i++) {
		if (i < even) {
			if (vec[i] % 2 != 0) q.push(i);
		} else {
			if (vec[i] % 2 == 0) {
				answer += (i - q.front());
				q.pop();
			}
		}
	}

	// 짝수 모두 오른쪽
	long long cnt = 0;
	queue<int> q2;
	for (int i = 0; i < n; i++) {
		if (i < odd) {
			if (vec[i] % 2 == 0) q.push(i);
		} else {
			if (vec[i] % 2 != 0) {
				cnt += (i - q.front());
				q.pop();
			}
		}
	}

	cout << min(answer, cnt);
}