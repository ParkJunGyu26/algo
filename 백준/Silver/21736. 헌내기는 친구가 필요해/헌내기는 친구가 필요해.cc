#include <iostream>
#include <string>
#include <queue>

using namespace std;

int n, m, start_x, start_y;
string tmp;
char graph[600][600];
int dx[4] = {1, -1, 0, 0};
int dy[4] = {0, 0, 1, -1};
int visited[600][600] = {0};

int bfs(int xx, int yy) {
	queue<pair<int, int>> q;
	q.push({xx, yy});
	visited[yy][xx] = 1;
	int cnt = 0;

	while (!q.empty()) {
		int x = q.front().first;
		int y = q.front().second;
		q.pop();

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (-1 < nx && nx < m && -1 < ny && ny < n && visited[ny][nx] == 0 && graph[ny][nx] != 'X') {
				if (graph[ny][nx] == 'P') cnt++;
				q.push({nx, ny});
				visited[ny][nx] = 1;
			}
		}
	}

	return cnt;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n >> m;
	for (int i = 0; i < n; i++) {
		cin >> tmp;
		for (int j = 0; j < m; j++) {
			graph[i][j] = tmp[j];
			if (graph[i][j] == 'I') {
				start_x = j; start_y = i;
			}
		}
	}

	int answer = bfs(start_x, start_y);

	if (answer == 0) cout << "TT";
	else cout << answer;
}