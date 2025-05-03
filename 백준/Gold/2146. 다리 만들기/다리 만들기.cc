#include <iostream>
#include <vector>
#include <queue>

#define MAX_NUM 10000

using namespace std;

int n;
int dx[4] = {0, 1, 0, -1};
int dy[4] = {-1, 0, 1, 0};
vector<vector<int>> vec;
vector<vector<int>> location;
vector<queue<pair<int, int>>> res(MAX_NUM);
vector<vector<bool>> visited;

bool inRange(int x, int y) {
	return (-1 < x && x < n && -1 < y && y < n);
}

void bfs(int xx, int yy, int num) {
	queue<pair<int, int>> q;
	visited[yy][xx] = true;
	location[yy][xx] = num;
	q.push({xx, yy});

	while (!q.empty()) {
		int x = q.front().first;
		int y = q.front().second;
		q.pop();

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (inRange(nx, ny) && !visited[ny][nx]) {
				visited[ny][nx] = true;
				if (vec[ny][nx] == 1) {
					q.push({nx, ny});
					location[ny][nx] = num;
				} else res[num].push({nx, ny});
			}
		}
	}
}

void solution(int num) {
	int result = MAX_NUM;

	for (int i = 1; i < num; i++) {
		queue<pair<int, int>> q;
		vector<vector<int>> answer(n, vector<int>(n, MAX_NUM));
		
		while (!res[i].empty()) {
			int x = res[i].front().first;
			int y = res[i].front().second;
			res[i].pop();

			answer[y][x] = 1;
			q.push({x, y});
		}

		while (!q.empty()) {
			int x = q.front().first;
			int y = q.front().second;
			q.pop();

			for (int j = 0; j < 4; j++) {
				int nx = x + dx[j];
				int ny = y + dy[j];

				if (inRange(nx, ny)) {
					if (vec[ny][nx] == 1) {
						if (i != location[ny][nx]) answer[ny][nx] = min(answer[ny][nx], answer[y][x]);
					} else {
						if (answer[y][x] + 1 < answer[ny][nx]) {
							answer[ny][nx] = answer[y][x] + 1;
							q.push({nx, ny});
						}
					}
				}
			}
		}

		for (int j = 0; j < n; j++) {
			for (int k = 0; k < n; k++) {
				if (vec[j][k] == 1) result = min(result, answer[j][k]);
			}
		}
	}

	cout << result;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n;
	vec.resize(n, vector<int>(n));
	res.resize(n*n);
	location.resize(n, vector<int>(n));
	visited.resize(n, vector<bool>(n));

	for (int i = 0; i < n; i++)
		for (int j = 0; j < n; j++) cin >> vec[i][j];

	int num = 1;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (!visited[i][j] && vec[i][j] == 1) {
				bfs(j, i, num++);
			}
		}
	}

	solution(num);
}