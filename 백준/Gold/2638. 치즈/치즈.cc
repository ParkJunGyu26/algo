#include <iostream>
#include <queue>
#include <vector>
#include <algorithm>

using namespace std;

int n, m, tmp;
int dx[4] = {1, -1, 0, 0};
int dy[4] = {0, 0, 1, -1};
vector<vector<int>> graph(100, vector<int>(100));
vector<vector<int>> visited(100, vector<int>(100));
vector<vector<int>> air(100, vector<int>(100)); // 1이면 외부, 0이면 내부

// now : 1(치즈 녹이기)
// now : 0(공기 초기화)
int bfs(int xx, int yy, int now, vector<vector<int>> &temp) {
	queue<pair<int, int>> q;
	q.push({xx, yy});
	visited[yy][xx] = 1;

	while(!q.empty()) {
		int x = q.front().first;
		int y = q.front().second;
		q.pop();

		int count = 0;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (-1 < nx && nx < m && -1 < ny && ny < n && visited[ny][nx] == 0) {
				// 공기 초기화
				if (now == 0 && graph[ny][nx] == 0) {
					air[ny][nx] = 1;
					visited[ny][nx] = 1;
					q.push({nx, ny});
				}

				// 치즈 초기화
				if (now == 1) {
					if (graph[ny][nx] == 0 && air[ny][nx] == 1) {
						count++;
					}
					if (graph[ny][nx] == 1) {
						visited[ny][nx] = 1;
						q.push({nx, ny});
					}
				}
			}
		}

		if (now == 1 && count >= 2) {
			temp[y][x] = 0;
		}
	}

	return 1;
}

// 0인 곳들을 bfs로 돌려서 나갈 수 있는지 확인
int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n >> m;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			cin >> tmp;
			graph[i][j] = tmp;
		}
	}

	int answer = 0;
	while (true) {
		// 데이터 일관성을 위한 임의 2차원 벡터
		vector<vector<int>> temp;
		temp = graph;

		// 공기 초기화해주기
		air[0][0] = 1;
		int trash = bfs(0, 0, 0, temp);

		fill(visited.begin(), visited.end(), vector<int>(m, 0));

		// 녹인 치즈 개수
		int cnt = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (graph[i][j] == 1 && visited[i][j] == 0) {
					cnt += bfs(j, i, 1, temp);
				}
			}
		}

		if (cnt == 0) break;

		graph = temp;
		fill(visited.begin(), visited.end(), vector<int>(m, 0));
		fill(air.begin(), air.end(), vector<int>(m, 0));

		fill(visited.begin(), visited.end(), vector<int>(m, 0));

		answer++;
	}

	cout << answer;
}