#include <iostream>
#include <vector>
#include <string>
#include <queue>
#include <tuple>

using namespace std;

string tmp;
int l, r, c, t_l, t_r, t_c, s_l, s_r, s_c;
// 동 서 남 북 상 하
int dx[6] = {1, -1, 0, 0, 0, 0};
int dy[6] = {0, 0, 1, -1, 0, 0};
int dz[6] = {0, 0, 0, 0, -1, 1};

void bfs(int xx, int yy, int zz, vector<vector<vector<char>>> &graph, vector<vector<vector<int>>> &visited, vector<vector<vector<int>>> &res) {
	queue<tuple<int, int, int>> q;
	q.push({xx, yy, zz});
	
	while(!q.empty()) {
		int x = get<0>(q.front());
		int y = get<1>(q.front());
		int z = get<2>(q.front());

		q.pop();

		if (x == t_c && y == t_r && z == t_l) {
			return;
		}

		for (int i = 0; i < 6; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			int nz = z + dz[i];

			if (-1 < nx && nx < c && -1 < ny && ny < r && -1 < nz && nz < l && visited[nz][ny][nx] == 0 && graph[nz][ny][nx] != '#') {
				res[nz][ny][nx] = res[z][y][x] + 1;
				visited[nz][ny][nx] = 1;
				q.push({nx, ny, nz});
			}
		}
	}
	return;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	while (true) {
		cin >> l >> r >> c;
		if (l == 0 && r == 0 && c == 0) break;
		vector<vector<vector<int>>> visited(l, vector<vector<int>>(r, vector<int>(c, 0)));
		vector<vector<vector<int>>> res(l, vector<vector<int>>(r, vector<int>(c, 0)));
		vector<vector<vector<char>>> graph(l, vector<vector<char>>(r, vector<char>(c)));
		for (int i = 0; i < l; i++) {
			for (int j = 0; j < r; j++) {
				cin >> tmp;
				for (int k = 0; k < c; k++) {
					graph[i][j][k] = tmp[k];
					if (graph[i][j][k] == 'E') {
						t_c = k;
						t_r = j;
						t_l = i;
					}
					if (graph[i][j][k] == 'S') {
						s_c = k;
						s_r = j;
						s_l = i;
					}
				}
			}
		}

		if (s_l == t_l && s_r == t_r && s_c == t_c) {
			cout << "Escaped in 0 minute(s)." ;
			continue;
		}

		visited[s_l][s_r][s_c] = 1;
		bfs(s_c, s_r, s_l, graph, visited, res);

		if (res[t_l][t_r][t_c] == 0) cout << "Trapped!";
		else cout << "Escaped in " << res[t_l][t_r][t_c] << " minute(s)." ;
		cout << "\n";
	}
}