#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

int k, w, h;
int dx_k[8] = {1, 2, 2, 1, -1, -2, -2, -1};
int dy_k[8] = {-2, -1, 1, 2, 2, 1, -1, -2};
int dx[4] = {0, 1, 0, -1};
int dy[4] = {-1, 0, 1, 0};
vector<vector<int>> graph;

struct Point{
int x;
int y;
int k;
};

bool inRange(int x, int y) {
	return (-1 < x && x < w && -1 < y && y < h);
}

void bfs() {
	vector<vector<vector<int>>> visited(h, vector<vector<int>>(w, vector<int>(k+1, 1e9)));
	queue<Point> q;
	q.push({0, 0, k});
	for (int i = 0; i <= k; i++) visited[0][0][i] = 0;

	while (!q.empty()) {
		int x = q.front().x;
		int y = q.front().y;
		int k_cnt = q.front().k;
		q.pop();

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (inRange(nx, ny) && graph[ny][nx] == 0 && visited[ny][nx][k_cnt] == 1e9) {
				q.push({nx, ny, k_cnt});
				visited[ny][nx][k_cnt] = visited[y][x][k_cnt] + 1;
			}

		}

		if (k_cnt > 0) {
			for (int i = 0; i < 8; i++) {
				int nx = x + dx_k[i];
				int ny = y + dy_k[i];
				int nK_cnt = k_cnt - 1;

				if (inRange(nx, ny) && graph[ny][nx] == 0 && visited[ny][nx][nK_cnt] == 1e9) {
					q.push({nx, ny, nK_cnt});
					visited[ny][nx][nK_cnt] = visited[y][x][k_cnt]+1;
				}
			}
		}
	}

	int ans = *min_element(visited[h-1][w-1].begin(), visited[h-1][w-1].end());

	if (ans == 1e9) cout << -1;
	else cout << ans;
}

// 3차원 -> 인접해서 오거나 말처럼 오거나
int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> k >> w >> h;
	graph.resize(h, vector<int>(w));
	for (int i = 0; i < h; i++)
		for (int j = 0; j < w; j++) cin >> graph[i][j];

	bfs();
}