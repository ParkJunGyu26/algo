#include <iostream>
#include <vector>
#include <queue>

#define MAX 50*50+1

using namespace std;

int answer = 0;
int n, m, g, r;
int dx[4] = {0, 1, 0, -1};
int dy[4] = {-1, 0, 1, 0};
int graph[51][51]; // 0 : 호수, 1 : 배양액 x, 2 : 배양액 o
vector<pair<int, int>> hubo; // 배양액이 위치할 수 있는 (x, y) 리스트

void init() {
	cin >> n >> m >> g >> r;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			cin >> graph[i][j];

			if (graph[i][j] == 2) {
				hubo.push_back({j, i});
			}
		}
	}
}

void bfs(vector<int>& green_dot, vector<int>& red_dot) {
	int cnt = 0;
	queue<pair<int, int>> red, green, next_red, next_green;
	int res[51][51][2]; // {시간, 방문 색 (1 : 그린, 2 : 레드, 3 : 꽃)} 
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			res[i][j][0] = MAX;
			res[i][j][1] = 0;
		}
	}
	
	// 그린
	for (int i = 0; i < g; i++) {
		pair<int, int> dot = hubo[green_dot[i]];
		res[dot.second][dot.first][0] = 0;
		res[dot.second][dot.first][1] = 1;
		next_green.push(dot);
	}

	// 레드
	for (int i = 0; i < r; i++) {
		pair<int, int> dot = hubo[red_dot[i]];
		res[dot.second][dot.first][0] = 0;
		res[dot.second][dot.first][1] = 2;
		next_red.push(dot);
	}

	while(!next_green.empty() && !next_red.empty()) {
		while(!next_green.empty()) {
			green.push(next_green.front());
			next_green.pop();
		}

		while(!next_red.empty()) {
			red.push(next_red.front());
			next_red.pop();
		}

		int x, y;

		while(!green.empty()) {
			x = green.front().first;
			y = green.front().second;
			green.pop();

			if (res[y][x][1] == 3) continue;

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (!(-1 < nx && nx < m && -1 < ny && ny < n)) continue;

				if (graph[ny][nx] == 0) continue;

				if (res[ny][nx][1] == 0) {
					res[ny][nx][0] = res[y][x][0] + 1;
					res[ny][nx][1] = 1;
					next_green.push({nx, ny});
				}
			}
		}

		while(!red.empty()) {
			x = red.front().first;
			y = red.front().second;
			red.pop();

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (!(-1 < nx && nx < m && -1 < ny && ny < n)) continue;
				
				if (graph[ny][nx] == 0) continue;

				if (res[ny][nx][0] == (res[y][x][0] + 1) && res[ny][nx][1] == 1) {
					cnt++;
					res[ny][nx][1] = 3;
				}

				if (res[ny][nx][1] == 0) {
					res[ny][nx][0] = res[y][x][0] + 1;
					res[ny][nx][1] = 2;
					next_red.push({nx, ny});
				}
			}
		}
	}
	
	answer = max(answer, cnt);
}

void set_red(int index, vector<bool>& visited, vector<int>& green_dot, vector<int>& red_dot) {
	if (red_dot.size() == r) {
		bfs(green_dot, red_dot);
		return;
	}

	for (int i = index; i < visited.size(); i++) {
		if (visited[i]) continue;

		visited[i] = true;
		red_dot.push_back(i);
		set_red(i+1, visited, green_dot, red_dot);
		red_dot.pop_back();
		visited[i] = false;
	}
}

void set_green(int index, vector<bool>& visited, vector<int>& green_dot) {
	if (green_dot.size() == g) {
		vector<int> red_dot;
		set_red(0, visited, green_dot, red_dot);

		return;
	}

	for (int i = index; i < visited.size(); i++) {
		if (visited[i]) continue;

		visited[i] = true;
		green_dot.push_back(i);
		set_green(i+1, visited, green_dot);
		green_dot.pop_back();
		visited[i] = false;
	}
}

void solve() {
	vector<bool> visited(hubo.size());
	vector<int> green_dot;

	// 1. 초록 배양액 위치 잡기
	// 2. 빨간 배양액 위치 잡기
	// 3. bfs 돌리기
	set_green(0, visited, green_dot);
}

int main() {
	ios_base::sync_with_stdio(false);cout.tie(NULL);cin.tie(NULL);

	init();
	solve();

	cout << answer;
}