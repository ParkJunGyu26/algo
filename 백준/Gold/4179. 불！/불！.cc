#include <iostream>
#include <string>
#include <queue>
#include <vector>
#include <climits>

#define Y second
#define X first

using namespace std;

int r, c;
char g[1000][1000];
int dx[4] = {1, -1, 0, 0};
int dy[4] = {0, 0, 1, -1};

int min(int a, int b) {
	if (a > b) return b;
	return a;
}

void bfs(queue<pair<int, int>>& q, vector<vector<int>>& v) {

	while (!q.empty()) {
		int x = q.front().X;
		int y = q.front().Y;
		q.pop();

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (-1 < nx && nx < c && -1 < ny && ny < r && v[ny][nx] == 0 && g[ny][nx] != '#') {
				q.push({nx, ny});
				v[ny][nx] = v[y][x] + 1;
			}
		}
	}

	return ;
}

// bfs를 두 번 돌리고, 지훈이 res가 벽에 더 낮은 숫자가 있으면 possible
// 불은 두 개 이상 있을 수 있다.
int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> r >> c;

	string tmp;
	queue<pair<int, int>> jihoon;
	queue<pair<int, int>> fire;
	vector<vector<int>> Jihoon(r, vector<int>(c, 0)); // res 및 방문처리
	vector<vector<int>> Fire(r, vector<int>(c, 0)); // res 및 방문처리
	vector<pair<int, int>> fir;
	
	for (int i = 0; i < r; i++) {
		cin >> tmp;
		for (int j = 0; j < c; j++) {
			g[i][j] = tmp[j];
			if (g[i][j] == 'J') {
				jihoon.push({j, i});
				Jihoon[i][j] = 1;
			}
			if (g[i][j] == 'F') {
				fire.push({j, i});
				Fire[i][j] = 1;
			}
		}
	}
	
	bfs(jihoon, Jihoon); // 지훈
	bfs(fire, Fire); // 불

	int answer = INT_MAX;
	bool check = false;
	for (int i = 0; i < r; i++) {
		for (int j = 0; j < c; j++) {
			if (i == 0 || i == r-1 || j == 0 || j == c-1) {
				if (Jihoon[i][j] == 0) continue;

				if (Fire[i][j] == 0 || Jihoon[i][j] < Fire[i][j]) {
					check = true;
					answer = min(answer, Jihoon[i][j]);
				}
			}
		}
	}

	if (check) cout << answer;
	else cout << "IMPOSSIBLE";
}