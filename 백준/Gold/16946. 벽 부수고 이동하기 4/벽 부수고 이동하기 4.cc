#include <iostream>
#include <vector>
#include <string>
#include <queue>

#define MAX_SIZE 1000001

using namespace std;

int n, m;
int dx[4] = {0, 1, 0, -1};
int dy[4] = {-1, 0, 1, 0};
int location[MAX_SIZE];
vector<vector<int>> vec;
vector<vector<int>> res;
vector<vector<bool>> visited;

bool inRange(int x, int y) {
	return (-1 < x && x < m && -1 < y && y < n);
}

void bfs(int xx, int yy, int num) {
	queue<pair<int, int>> q;
	q.push({xx, yy});
	visited[yy][xx] = true;
	res[yy][xx] = num;
	int cnt = 1;

	while (!q.empty()) {
		int x = q.front().first;
		int y = q.front().second;
		q.pop();

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (inRange(nx, ny) && !visited[ny][nx] && vec[ny][nx] == 0) {
				visited[ny][nx] = true;
				res[ny][nx] = num;
				q.push({nx, ny});
				cnt++;
			}
		}
	}

	location[num] = cnt;
}	

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n >> m;
	vec.resize(n, vector<int>(m));
	res.resize(n, vector<int>(m));
	visited.resize(n, vector<bool>(m));

	// O(10^6)
	for (int i = 0; i < n; i++) {
		string tmp;
		cin >> tmp;
		for (int j = 0; j < m; j++) {
			vec[i][j] = tmp[j] - '0';
		}
	}

	// O(10^6)
	int num = 0;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			if (!visited[i][j] && vec[i][j] == 0) bfs(j, i, num++);
		}
	}

	// O(10^6)
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			if (vec[i][j] == 0) cout << 0;
			else {
				int result = 1;
				vector<bool> v(num+1);

				for (int k = 0; k < 4; k++) {
					int nx = j + dx[k];
					int ny = i + dy[k];

					if (inRange(nx, ny) && !v[res[ny][nx]] && vec[ny][nx] == 0) {
						result += location[res[ny][nx]];
						v[res[ny][nx]] = true;
					}
				}

				cout << result % 10;
			}
		}
		cout << "\n";
	}
}