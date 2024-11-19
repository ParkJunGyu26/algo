#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

int m, n, k;
int graph[100][100] = {0};
int visited[100][100] = {0};
int dx[4] = {1, -1, 0, 0};
int dy[4] = {0, 0, 1, -1};

int bfs(int xx, int yy) {
	int cnt = 1;
	queue<pair<int, int>> q;
	q.push({xx, yy});
	visited[yy][xx] = 1;

	while(!q.empty()) {
		int x = q.front().first;
		int y = q.front().second;
		q.pop();

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (-1 < nx && nx < n && -1 < ny && ny < m && graph[ny][nx] == 0 && visited[ny][nx] == 0) {
				visited[ny][nx] = 1;
				q.push({nx, ny});
				cnt++;
			}
		}
	}

	return cnt;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	vector<int> answer;

	cin >> m >> n >> k; // 높이, 너비, 직사각형 개수

	for (int _ = 0; _ < k; _++) {
		int x1, y1, x2, y2;
		cin >> x1 >> y1 >> x2 >> y2;
		for (int i = m-y2; i < m-y1; i++) {
			for (int j = x1; j < x2; j++) graph[i][j] = 1;
		}
	}

	// for (int i = 0; i < m; i++) {
	// 	for (int j = 0; j < n; j++) cout << graph[i][j] << " ";
	// 	cout << "\n";
	// }

	for (int i = 0; i < m; i++) {
		for (int j = 0; j < n; j++) {
			if (graph[i][j] == 0 && visited[i][j] == 0)
				answer.push_back(bfs(j, i));
		}
	}

	sort(answer.begin(), answer.end());

	cout << answer.size() << "\n";
	for (auto a : answer)
		cout << a << " ";
}