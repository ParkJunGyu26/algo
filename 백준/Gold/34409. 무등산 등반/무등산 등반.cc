#include <iostream>
#include <vector>
#include <queue>
#include <climits>

using namespace std;

int n, m, start_x, start_y, end_x, end_y, a, b, c;
int dx[4] = {0, 1, 0, -1};
int dy[4] = {-1, 0, 1, 0};
vector<vector<int>> matrix;
vector<vector<pair<int, int>>> graph;
vector<vector<bool>> visited;

bool in_range(int x, int y) {
	return (-1 < x && x < m && -1 < y && y < n);
}

void dijkstra() {
	priority_queue<pair<int, int>> pq;
	int start = start_y * m + start_x;

	pq.push({0, start});
	vector<long long> res(n*m, INT_MAX);
	res[start] = 0;

	while (!pq.empty()) {
		int dist = -pq.top().first;
		int node = pq.top().second;
		pq.pop();

		if (res[node] < dist) continue;

		for (int i = 0; i < graph[node].size(); i++) {
			int next_node = graph[node][i].first;
			int next_dist = dist + graph[node][i].second;

			if (res[next_node] > next_dist) {
				res[next_node] = next_dist;
				pq.push({-next_dist, next_node});
			}
		}
	}

	if (res[end_x + end_y * m] == INT_MAX) cout << -1;
	else cout << res[end_x + end_y * m];
}

// 다익스트라? -> 가중치 있음... 단순히 1이 아님
// bfs? -> 가중치 없고, 최단 경로
// dfs? -> 
// N, M <= 500 -> 500^2
int main() {
	ios_base::sync_with_stdio(false);
	cout.tie(NULL);
	cin.tie(NULL);

	cin >> n >> m >> start_y >> start_x >> a >> b >> c;
	matrix.resize(n, vector<int>(m));
	visited.resize(n, vector<bool>(m));
	graph.resize(n*m);

	start_x--;
	start_y--;
	int maxH = 0;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			cin >> matrix[i][j];
			if (maxH < matrix[i][j]) {
				maxH = matrix[i][j];
				end_y = i;
				end_x = j;
			}
		}
	}

	// make 다익스트라
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			int index = (i * m) + j;

			for (int k = 0; k < 4; k++) {
				int nx = j + dx[k];
				int ny = i + dy[k];

				if (!in_range(nx, ny)) continue;

				int diff = matrix[ny][nx] - matrix[i][j];

				if (abs(diff) > c) continue;

				int dist = 1;
				if (diff > 0) dist = diff * a;
				else if (diff < 0) dist = diff * -b;

				int next_index = (ny * m) + nx;

				graph[index].push_back({next_index, dist}); // (인덱스, 가중치)
			}
		}
	}

	dijkstra();
}