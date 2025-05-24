#include <iostream>
#include <vector>
#include <queue>
#include <climits>

using namespace std;

struct Node {
	int x;
	int y;
	int life;
	int result;
};

int n, m, start_x, start_y, end_x, end_y;
int answer = 0;
int dx[4] = {0, 1, 0, -1};
int dy[4] = {-1, 0, 1, 0};
vector<vector<int>> graph;
vector<vector<vector<int>>> res;

bool inRange(int x, int y) {
	return (-1 < x && x < m && -1 < y && y < n);
}

void bfs() {
	queue<Node> q;
	Node st = {start_x, start_y, 1, 1};
	q.push({st});
	res[start_y][start_x][0] = res[start_y][start_x][1] = 0;

	while (!q.empty()) {
		int x = q.front().x;
		int y = q.front().y;
		int life = q.front().life;
		int result = q.front().result;
		q.pop();
		
		if (x == end_x && y == end_y) {
			cout << result;
			return;
		}


		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (inRange(nx, ny)) {
				if (graph[ny][nx] == 0) {
					if (life == 0) {
						if (res[ny][nx][1] > res[y][x][1] + 1) {
							res[ny][nx][1] = res[y][x][1] + 1;
							q.push({nx, ny, life, result+1});
						}
					} else {
						if (res[ny][nx][0] > res[y][x][0] + 1) {
							res[ny][nx][0] = res[y][x][0] + 1;
							q.push({nx, ny, life, result+1});
						}
					}
				} else {
					if (life == 0) continue;

					if (res[ny][nx][1] == INT_MAX) {
						res[ny][nx][1] = res[y][x][0];
						q.push({nx, ny, life-1, result});
					}
				}
			}
		}
	}

	cout << -1;
	return;
}

int main() {
	ios_base::sync_with_stdio(false);cin.tie(NULL);cout.tie(NULL);
	cin >> n >> m;
	cin >> start_y >> start_x >> end_y >> end_x;
	start_x--;
	start_y--;
	end_x--;
	end_y--;

	graph.resize(n, vector<int>(m));
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) cin >> graph[i][j];
	}
	res.resize(n, vector<vector<int>>(m, vector<int>(2, INT_MAX)));

	bfs();
}