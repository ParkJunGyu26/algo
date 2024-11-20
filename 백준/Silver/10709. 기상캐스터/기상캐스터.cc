#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int h, w;
char g[100][100];
queue<pair<int, int>> q;

void bfs(vector<vector<int>> &res) {

	while (!q.empty()) {
		int x = q.front().first;
		int y = q.front().second;
		q.pop();

		int nx = (x+1);

		if (nx < w && res[y][nx] == -1) {
			res[y][nx] = res[y][x] + 1;
			q.push({nx, y});
		}
	}

	return;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	
	cin >> h >> w;
	vector<vector<int>> res(h, vector<int>(w, -1));

	for (int i = 0; i < h; i++) {
		for (int j = 0; j < w; j++) {
			cin >> g[i][j];
			if (g[i][j] == 'c') {
				res[i][j] = 0;
				q.push({j, i});
			}
		}
	}
	
	bfs(res);
	for (int i = 0; i < h; i++) {
		for (int j = 0; j < w; j++) cout << res[i][j] << " ";
		cout << "\n";
	}
}