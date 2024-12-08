#include <iostream>
#include <queue>

using namespace std;

int n, m;
int dx[4] = {1, -1, 0, 0};
int dy[4] = {0, 0, 1, -1};
int answer = 0;

void bfs(queue<pair<int, int>> &q, vector<vector<int>> &v, vector<vector<int>> &graph) {

	while (!q.empty()) {
		int x = q.front().first;
		int y = q.front().second;
		q.pop();

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (-1 < nx && nx < m && -1 < ny && ny < n && v[ny][nx] == 0 && graph[ny][nx] == 0) {
				v[ny][nx] = 1;
				graph[ny][nx] = 2;
				q.push({nx, ny});
			}
		}
	}
}

void dfs(int num, int cnt, queue<pair<int, int>> &virus, vector<vector<int>> &graph, vector<vector<int>> &visited) {
	if (cnt == 3) {
		vector<vector<int>> v = visited;
		vector<vector<int>> g = graph;
		queue<pair<int, int>> q = virus;
		bfs(q, v, g);
		int count = 0;
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++) if (g[i][j] == 0) count++;

		answer = max(answer, count);
		return;
	}

	for (int i = num+1; i < n*m; i++) {
		if (graph[i/m][i%m] == 0) {
			graph[i/m][i%m] = 1;
			dfs(i, cnt+1, virus, graph, visited);
			graph[i/m][i%m] = 0;
		}
	}
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n >> m;
	vector<vector<int>> graph(n, vector<int>(m));
	vector<vector<int>> visited(n, vector<int>(m));
	queue<pair<int, int>> virus;

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			cin >> graph[i][j];
			if (graph[i][j] == 2) {
				visited[i][j] = 1;
				virus.push({j, i});
			}
		}
	}

	for (int i = 0; i < m*n; i++) {
		if (graph[i/m][i%m] == 0) {
			graph[i/m][i%m] = 1;
			dfs(i, 1, virus, graph, visited);
			graph[i/m][i%m] = 0;
		}
	}

	cout << answer;
}