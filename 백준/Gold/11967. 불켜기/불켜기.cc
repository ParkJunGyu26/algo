#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int light = 1;
int n, m;
int dx[4] = {0, 1, 0, -1};
int dy[4] = {-1, 0, 1, 0};
vector<vector<int>> graph;
vector<vector<vector<pair<int, int>>>> info;

void bfs() {
    vector<vector<bool>> visited(n, vector<bool>(n));
    queue<pair<int, int>> q;
    q.push({0, 0});
    visited[0][0] = true;

    while (!q.empty()) {
        int x = q.front().first;
        int y = q.front().second;
        q.pop();

        while (!info[y][x].empty()) {
            int xx = info[y][x].back().first;
            int yy = info[y][x].back().second;
            info[y][x].pop_back();

            if (graph[yy][xx] == 1) continue;

            graph[yy][xx] = 1;
            light++;

            bool isPossible = false;

            for (int i = 0; i < 4; i++) {
                int nx = xx + dx[i];
                int ny = yy + dy[i];

                if (!(-1 < nx && nx < n && -1 < ny && ny < n)) continue;

                if (visited[ny][nx]) {
                    isPossible = true;
                    break;
                }
            }

            if (isPossible) {
                visited[yy][xx] = true;
                q.push({xx, yy});
            }
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (!(-1 < nx && nx < n && -1 < ny && ny < n)) continue;

            if (graph[ny][nx] == 0) continue;

            if (!visited[ny][nx]) {
                q.push({nx, ny});
                visited[ny][nx] = true;
            }
        }
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> n >> m;
    graph.resize(n, vector<int>(n));
    info.resize(n, vector<vector<pair<int, int>>>(n));
    graph[0][0] = 1;
    
    for (int i = 0; i < m; i++) {
        int x, y, a, b;
        cin >> x >> y >> a >> b;
        info[y-1][x-1].push_back({a-1, b-1});
    }

    bfs();
    
    cout << light;
}