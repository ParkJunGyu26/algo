#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int n, m, a, b;
int dx[4] = {1, -1, 0, 0};
int dy[4] = {0, 0, 1, -1};
int res[1000][1000];

void bfs(int xx, int yy, vector<vector<int>> &graph) {
	queue<pair<int, int>> q;
	q.push({xx, yy});
	res[yy][xx] = 0;

	while (!q.empty()) {
		int x = q.front().first;
		int y = q.front().second;
		q.pop();

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (-1 < nx && nx < m && -1 < ny && ny < n && res[ny][nx] == -1) {
				if (graph[ny][nx] == 1) {
					res[ny][nx] = res[y][x] + 1;
					q.push({nx, ny});
				}
			}
		}
	}
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n >> m;
	vector<vector<int>> graph(n, vector<int>(m));
	fill(&res[0][0], &res[n][m], -1);
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			cin >> graph[i][j];
			if (graph[i][j] == 2) {
				a = i;
				b = j;
			} else if (graph[i][j] == 0) res[i][j] = 0;
		}
	}


	bfs(b, a, graph);
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) cout << res[i][j] << " ";
		cout << "\n";
	}
}