#include <iostream>
#include <string>
#include <queue>
#include <vector>
#include <climits>

using namespace std;

int t, w, h;
int dx[4] = {1, -1, 0, 0};
int dy[4] = {0, 0, 1, -1};
string str;
char graph[1000][1000];

int min(int a, int b) {
	if (a > b ) return b;
	return a;
}

void bfs(queue<pair<int, int>>& q, vector<vector<int>>& v) {

	while(!q.empty()) {
		int x = q.front().first;
		int y = q.front().second;
		q.pop();

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (-1 < nx && nx < w && -1 < ny && ny < h && v[ny][nx] == 0 && graph[ny][nx] != '#') {
				v[ny][nx] = v[y][x] + 1;
				q.push({nx, ny});
			}
		}
	}

	return ;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> t;
	for (int _ = 0; _ < t; _++) {
		cin >> w >> h;
		queue<pair<int, int>> fire;
		queue<pair<int, int>> person;
		vector<vector<int>> f_v (h, vector<int>(w, 0));
		vector<vector<int>> p_v (h, vector<int>(w, 0));

		for (int i = 0; i < h; i++) {
			cin >> str;
			for (int j = 0; j < w; j++) {
				graph[i][j] = str[j];
				if (str[j] == '@') {
					person.push({j, i});
					p_v[i][j] = 1;
				}
				if (str[j] == '*') {
					fire.push({j, i});
					f_v[i][j] = 1;
				}
			}
		}

		bfs(fire, f_v);
		bfs(person, p_v);

		int answer = INT_MAX;
		bool check = false;
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if (i == 0 || i == h-1 || j == 0 || j == w-1) {
					if (graph[i][j] != '#') {
						if (p_v[i][j] == 0) continue;

						// cout << "p_v[i][j] : " << p_v[i][j] << "\n";
						// cout << "f_v[i][j] : " << f_v[i][j] << "\n";
						// cout << "---\n";

						if (p_v[i][j] < f_v[i][j] || f_v[i][j] == 0) {
							check = true;
							answer = min(answer, p_v[i][j]);
						}
					}
				}
			}
		}

		if (check) cout << answer;
		else cout << "IMPOSSIBLE";
		cout << "\n";
	}
}