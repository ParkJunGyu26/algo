#include <iostream>
#include <vector>

using namespace std;

int n;
vector<vector<int>> graph;
vector<vector<vector<long long>>> dp;

int dx[3] = {1, 1, 0};
int dy[3] = {0, 1, 1};

bool inRange(int x, int y) {
	return (0 <= x && x < n && 0 <= y && y < n);
}

long long dfs(int x, int y, int dir) {
	if (x == n - 1 && y == n - 1) return 1;

	if (dp[y][x][dir] != -1) return dp[y][x][dir];

	long long cnt = 0;
	for (int i = 0; i < 3; i++) {
		if ((dir == 0 && i == 2) || (dir == 2 && i == 0)) continue;

		int nx = x + dx[i];
		int ny = y + dy[i];

		if (!inRange(nx, ny) || graph[ny][nx] == 1) continue;

		if (i == 1) {
			if (graph[y][x + 1] == 1 || graph[y + 1][x] == 1) continue;
		}

		cnt += dfs(nx, ny, i);
	}
	return dp[y][x][dir] = cnt;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n;
	graph.resize(n, vector<int>(n));
	dp.resize(n, vector<vector<long long>>(n, vector<long long>(3, -1)));

	for (int i = 0; i < n; i++)
		for (int j = 0; j < n; j++) cin >> graph[i][j];

	cout << dfs(1, 0, 0) << "\n";
}