#include <iostream>
#include <string>
#include <vector>
#include <queue>

using namespace std;

int n;
char g[100][100];
int v[100][100] = {0};
int dx[4] = {1, -1, 0, 0};
int dy[4] = {0, 0, 1, -1};

int bfs(int xx, int yy, bool status) { // status -> true : 정상
	queue<pair<int, int>> q;
	q.push({xx, yy});

	while(!q.empty()) {
		int x = q.front().first;
		int y = q.front().second;
		q.pop();

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (-1 < nx && nx < n && -1 < ny && ny < n && v[ny][nx] == 0) {
				if (g[ny][nx] == g[y][x]) {
					q.push({nx, ny});
					v[ny][nx] = 1;
				} else {
					if (!status) {
						if (g[y][x] == 'R' && g[ny][nx] == 'G' || g[y][x] == 'G' && g[ny][nx] == 'R') {
							q.push({nx, ny});
							v[ny][nx] = 1;
						}
					}
				}
			}
		}
	}

	return 1;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n;
	string tmp;
	for (int i = 0; i < n; i++) {
		cin >> tmp;
		for (int j = 0; j < n; j++)
			g[i][j] = tmp[j];
	}

	int answer1, answer2;
	answer1 = answer2 = 0;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (v[i][j] == 0) {
				v[i][j] = 1;
				answer1 += bfs(j, i, true);
			}
		}
	}

	for (int i = 0; i < n; i++) 
		for (int j = 0; j < n; j++) v[i][j] = 0;
	
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (v[i][j] == 0) {
				v[i][j] = 1;
				answer2 += bfs(j, i, false);
			}
		}
	}

	cout << answer1 << " " << answer2;
}