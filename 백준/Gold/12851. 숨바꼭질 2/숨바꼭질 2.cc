#include <iostream>
#include <queue>
#include <vector>
#include <algorithm>

using namespace std;

int n, k;
int dx[2] = {1, -1};
vector<int> res;
int answer = 0;

bool range(int pos) {
	return (pos < 0 || pos > 100000);
}

void bfs() {
	queue<pair<int, int>> q;
	q.push({n, 0});
	res.resize(100001, 1e9);

	while(!q.empty()) {
		int pos = q.front().first;
		int time = q.front().second;
		q.pop();

		if (range(pos)) continue;

		res[pos] = time;
		if (pos == k) answer++;

		int nPos, nTime;
		nTime = time+1;
		for (int i = 0; i < 2; i++) {
			nPos = pos + dx[i];

			if (range(nPos) || res[nPos] < nTime) continue;

			res[nPos] = nTime;
			q.push({nPos, nTime});
		}
		nPos = pos*2;
		if (range(nPos) || res[nPos] < nTime) continue;

		res[nPos] = nTime;
		q.push({nPos, nTime});
	}
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n >> k;
	if (n >= k) {
		cout << abs(n-k) << "\n" << 1;
	} else {
		bfs();
		cout << res[k] << "\n" << answer;
	}
}