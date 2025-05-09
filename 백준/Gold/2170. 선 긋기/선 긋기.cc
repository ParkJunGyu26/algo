#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
#include <climits>

using namespace std;

long answer = 0;
int n, x, y;
priority_queue<pair<long, long>> pq;

int main() {
	ios_base::sync_with_stdio(false);
	cout.tie(NULL);
	cin.tie(NULL);

	cin >> n;
	for (int i = 0; i < n; i++) {
		cin >> x >> y;
		pq.push({-x, -y});
	}

	long before_end = LONG_MIN;

	while (!pq.empty()) {
		long start = -pq.top().first;
		long end = -pq.top().second;
		pq.pop();

		if (before_end > end) continue;

		if (before_end < start) answer += abs(end - start);
		else if (before_end == start) answer += abs(end - start);
		else answer += abs(end - before_end);

		before_end = end;
	}

	cout << answer;
}