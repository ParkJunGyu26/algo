#include <iostream>
#include <vector>

using namespace std;

int n;
int answer = 0;
// y가 점점 커지도록 하기 때문에 애초에 자신의 y보다 작은 값은 안 봐도 됨
int dx[5] = {1, 1, 0, -1, -1};
int dy[5] = {0, 1, 1, 1, 0};

bool inRange(int x, int y) {
	return (-1 < x && x < n && -1 < y && y < n);
}

// 퀸 자리 못 두도록
void Queen(int x, int y, int index, vector<vector<int>> &visited, int Ty) {
	int nx = x + dx[index];
	int ny = y + dy[index];

	if (inRange(nx, ny)) {
		visited[ny][nx] += Ty;
		Queen(nx, ny, index, visited, Ty);
	}

}

void bt(int y, vector<vector<int>> &visited) {
	if (y == n) {
		answer++;
		return;
	}

	for (int i = 0; i < n; i++) {
		if (visited[y][i] == 0) {
			// 못 가는 퀸 위치 지정(가지치기)
			visited[y][i]--;
			for (int j = 0; j < 5; j++) Queen(i, y, j, visited, -1);

			bt(y+1, visited);

			// 퀸 위치 리셋
			for (int j = 0; j < 5; j++) Queen(i, y, j, visited, 1);
			visited[y][i]++;
		}
	}
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n;
	vector<vector<int>> visited(n, vector<int>(n));
	
	bt(0, visited);
	cout << answer;
}