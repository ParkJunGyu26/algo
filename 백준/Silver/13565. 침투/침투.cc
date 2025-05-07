#include <iostream>
#include <vector>
#include <queue>
#include <string>

using namespace std;

bool check = false;
int m, n;
int dx[4] = {0, 1, 0, -1};
int dy[4] = {-1, 0, 1, 0};
vector<vector<int>> vec;

bool inRange(int x, int y) {
	return (-1 < x && x < n && -1 < y && y < m);
}

void bfs(int xx, int yy) {
	vector<vector<bool>> visited(m, vector<bool>(n));
	queue<pair<int, int>> q;
	q.push({xx, yy});
	visited[yy][xx] = true;

	while (!q.empty()) {
		int x = q.front().first;
		int y = q.front().second;
		q.pop();

		if (y == m-1) {
			check = true;
			return;
		}

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (inRange(nx, ny) && !visited[ny][nx] && vec[ny][nx] == 0) {
				visited[ny][nx] = true;
				q.push({nx, ny});
			}
		}
	}
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> m >> n;
	vec.resize(m, vector<int>(n));
	for (int i = 0; i < m; i++) {
		string tmp;
		cin >> tmp;
		for (int j = 0; j < n; j++) vec[i][j] = tmp[j]-'0';
	}

	for (int i = 0; i < n; i++) {
		if (vec[0][i] == 0) {
			bfs(i, 0);
			if (check) {
				cout << "YES";
				return 0;
			}
		}
	}

	cout << "NO";
}