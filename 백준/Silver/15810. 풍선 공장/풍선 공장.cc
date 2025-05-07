#include <iostream>
#include <queue>

using namespace std;

int cnt = 0;
long long n, m, answer;
priority_queue<pair<long long, long long>> pq;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n >> m;
	for (int i = 0; i < n; i++) {
		long long tmp;
		cin >> tmp;
		pq.push({-tmp, -tmp});
	}

	while (!pq.empty() && cnt < m) {
		long long now = -pq.top().first;
		long long plus = -pq.top().second;
		pq.pop();

		cnt++;
		pq.push({-(now+plus), -plus});
		answer = now;
	}

	cout << answer;
}