#include <iostream>
#include <vector>
#include <string>
#include <queue>
#include <tuple>

using namespace std;

int n, m;
vector<vector<char>> graph;
vector<vector<vector<int>>> dist; // [y][x][벽 부순 상태]
int dx[4] = {1, -1, 0, 0};
int dy[4] = {0, 0, 1, -1};

int bfs() {
    queue<tuple<int, int, int>> q; // y, x, 벽 부순 상태
    q.push({0, 0, 0});
    dist[0][0][0] = 1;

    while (!q.empty()) {
        auto [y, x, broken] = q.front();
        q.pop();

        if (y == n-1 && x == m-1) {
            return dist[y][x][broken];
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (ny < 0 || ny >= n || nx < 0 || nx >= m) continue;

            // 다음 칸이 벽이고 아직 벽을 부수지 않은 경우
            if (graph[ny][nx] == '1' && broken == 0) {
                if (dist[ny][nx][1] == 0) {
                    dist[ny][nx][1] = dist[y][x][0] + 1;
                    q.push({ny, nx, 1});
                }
            }
            // 다음 칸이 벽이 아닌 경우
            else if (graph[ny][nx] == '0') {
                if (dist[ny][nx][broken] == 0) {
                    dist[ny][nx][broken] = dist[y][x][broken] + 1;
                    q.push({ny, nx, broken});
                }
            }
        }
    }
    return -1;
}

int main() {
    cin >> n >> m;
    
    graph.resize(n, vector<char>(m));
    dist.resize(n, vector<vector<int>>(m, vector<int>(2, 0)));
    
    for (int i = 0; i < n; i++) {
        string s;
        cin >> s;
        for (int j = 0; j < m; j++) {
            graph[i][j] = s[j];
        }
    }

    cout << bfs();
    return 0;
}