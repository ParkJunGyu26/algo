#include <iostream>
#include <vector>
#include <string>
#include <unordered_map>
#include <algorithm>
#include <queue>
#include <climits>

using namespace std;

int t, n, n_x, n_y, t_x, t_y;
int dx[8] = {1, 2, 2, 1, -1, -2, -2, -1};
int dy[8] = {-2, -1, 1, 2, 2, 1, -1, -2};

void bfs(vector<vector<int>> &visited) {
	queue<pair<int, int>> q;
	q.push({n_x, n_y});
	visited[n_y][n_x] = 0;

	while (!q.empty()) {
		int x = q.front().first;
		int y = q.front().second;
		q.pop();

		if (x == t_x && y == t_y) {
			cout << visited[y][x] << "\n";
			return;
		}

		for (int i = 0; i < 8; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (-1 < nx && nx < n && -1 < ny && ny < n && visited[ny][nx] == 0) {
				visited[ny][nx] = visited[y][x] + 1;
				q.push({nx, ny});
			}
		}
	}
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> t;

	for (int _ = 0; _ < t; _++) {
		cin >> n >> n_x >> n_y >> t_x >> t_y;
		vector<vector<int>> visited(n, vector<int>(n, 0));
		bfs(visited);
	}
}