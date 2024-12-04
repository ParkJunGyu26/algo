#include <iostream>
#include <queue>

using namespace std;

struct Point {
	int x;
	int y;
	int status; // 0 : 가로, 1 : 대각선, 2 : 세로
};

// 가로, 대각선, 세로
int dx[3] = {1, 1, 0};
int dy[3] = {0, 1, 1};
int n;
int graph[16][16] = {0};  // 0 : 가로, 1 : 대각선, 2 : 세로

int bfs(int xx, int yy) {
	queue<Point> q;
	q.push({xx, yy, 0});
	int cnt = 0;

	while (!q.empty()) {
		int x = q.front().x;
		int y = q.front().y;
		int status = q.front().status;
		q.pop();

		if (x == n-1 && y == n-1) cnt++;

		for (int i = 0; i < 3; i++) {
			if (status == 0 && i == 2) continue; // 가로 
			else if (status == 2 && i == 0) continue; // 세로
			int nx = x + dx[i];
			int ny = y + dy[i];
			int next_status = i;

			// cout << "nx : " << nx << "\n";
			// cout << "ny : " << ny << "\n";
			// cout << "next_status : " << next_status << "\n";
			// cout << "----\n";

			if (-1 < nx && nx < n && -1 < ny && ny < n && graph[ny][nx] == 0) {
				if (i == 1) {
					if (graph[ny-1][nx] == 1 || graph[ny][nx-1] == 1) continue;
				}
				q.push({nx, ny, next_status});
			}
		}
	}

	return cnt;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n;
	for (int i = 0; i < n; i++)
		for (int j = 0; j < n; j++) cin >> graph[i][j];
	
	cout << bfs(1, 0);
}