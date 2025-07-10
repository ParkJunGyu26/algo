#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>

#define MAX 20*20+1

using namespace std;

// 좌표(x, y), 시간, 레벨
struct Info {
	int res, level, eat;
	pair<int, int> dot;
};

int answer = 0;
int n;
int dx[4] = {0, 1, 0, -1};
int dy[4] = {-1, 0, 1, 0};
pair<int, int> start; // 시작점
pair<int, int> EXIT = {-1, -1};
vector<vector<int>> grid; // 그래프

bool cmp(Info a, Info b) {
	if (a.res != b.res) return a.res < b.res;
	if (a.dot.second != b.dot.second) return a.dot.second < b.dot.second;
	return a.dot.first < b.dot.first;
}

bool inRange(int x, int y) {
	return (-1 < x && x < n && -1 < y && y < n);
}

void FAST() {
	ios_base::sync_with_stdio(false);cin.tie(NULL);cout.tie(NULL);
}

void INPUT() {
	cin >> n;
	grid.resize(n, vector<int>(n));
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			cin >> grid[i][j];
			if (grid[i][j] == 9) {
				start = {j, i};
				grid[i][j] = 0;
			}
		}
	}
}

Info SEARCH(Info now) {
	Info target = {0, 0, 0, EXIT};

	int min_distance = MAX;
	queue<Info> q;
	vector<vector<int>> visited(n, vector<int>(n, MAX));
	vector<Info> hubo;

	q.push(now);
	visited[now.dot.second][now.dot.first] = true;

	while (!q.empty()) {
		Info cur = q.front();
		q.pop();

		int eat = cur.eat;
		int level = cur.level;
		int y = cur.dot.second;
		int x = cur.dot.first;
		int res = cur.res;

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			int nRes = res+1;

			if (!inRange(nx, ny) || grid[ny][nx] > level || visited[ny][nx] <= visited[y][x] + 1) continue;

			// 이동만 가능 -> 먹지는 못함
			if (grid[ny][nx] == 0 || grid[ny][nx] == level) {
				q.push({nRes, level, eat, {nx, ny}});
			} else { // 이동하지 않고, 먹음
				if (min_distance < visited[y][x] + 1) continue;
				min_distance = visited[y][x] + 1;

				hubo.push_back({nRes, level, eat+1, {nx, ny}});
			}
			visited[ny][nx] = visited[y][x] + 1;
		}
	}

	if (hubo.empty()) return target;

	sort(hubo.begin(), hubo.end(), cmp);

	target = hubo[0];
	
	if (target.eat == target.level) target = {target.res, target.level+1, 0, target.dot};
	grid[target.dot.second][target.dot.first] = 0;

	answer = target.res;
	return target;
}

void SOLVE() {
	Info now = {0, 2, 0, start};

	while (true) {
		Info target = SEARCH(now);
		if (target.dot == EXIT) {
			cout << answer;
			return;
		}

		now = target;
	}
}

int main() {
	FAST();
	INPUT();
	SOLVE();
}