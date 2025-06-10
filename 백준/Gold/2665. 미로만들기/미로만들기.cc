#include <iostream>
#include <string>
#include <vector>
#include <queue>

using namespace std;

int n;
int dx[4] = {0, 1, 0, -1};
int dy[4] = {-1, 0, 1, 0};
vector<vector<char>> graph;
vector<vector<int>> res;

bool inRange(int x, int y) {
	return (-1 < x && x < n && -1 < y && y < n);
}

int bfs() {
	res[0][0] = 0;
	queue<pair<int, int>> q;
	q.push({0, 0});

	while(!q.empty()) {
		auto cur = q.front();
		q.pop();

		for (int i = 0; i < 4; i++) {
			int nx = cur.first + dx[i];
			int ny = cur.second + dy[i];

			if (!inRange(nx, ny)) continue;

			if (graph[ny][nx] == '1' && res[ny][nx] > res[cur.second][cur.first]) {
				if (res[ny][nx] > res[cur.second][cur.first]) {
					res[ny][nx] = res[cur.second][cur.first];
					q.push({nx, ny});
				}
			} else {
				if (res[ny][nx] > res[cur.second][cur.first] + 1) {
					res[ny][nx] = res[cur.second][cur.first] + 1;
					q.push({nx, ny});
				}
			}
		}
	}

	return res[n-1][n-1];
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n;
	graph.resize(n, vector<char>(n));
	res.resize(n, vector<int>(n, n*n+1));
	
	for (int i = 0; i < n; i++) {
		string tmp = "";
		cin >> tmp;
		for (int j = 0; j < n; j++) graph[i][j] = tmp[j];
	}

	cout << bfs();
}