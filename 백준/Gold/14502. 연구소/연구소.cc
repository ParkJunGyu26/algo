#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

int n, m, answer = 0;
int dx[4] = {1, -1, 0, 0};
int dy[4] = {0, 0, 1, -1};
vector<pair<int, int>> empty_spaces, viruses;

void spread_virus(vector<vector<int>>& graph) {
    vector<vector<int>> temp_graph = graph;
    queue<pair<int, int>> q;
    
    for (auto& virus : viruses) {
        q.push(virus);
    }

    while (!q.empty()) {
        int x = q.front().first;
        int y = q.front().second;
        q.pop();

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < m && ny >= 0 && ny < n && temp_graph[ny][nx] == 0) {
                temp_graph[ny][nx] = 2;
                q.push({nx, ny});
            }
        }
    }

    // 안전 영역 크기 계산
    int safe_area = 0;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            if (temp_graph[i][j] == 0) {
                safe_area++;
            }
        }
    }
    answer = max(answer, safe_area);
}

void place_walls(int start, vector<vector<int>>& graph, int walls_placed) {
    if (walls_placed == 3) {
        spread_virus(graph);
        return;
    }

    for (int i = start; i < empty_spaces.size(); i++) {
        int x = empty_spaces[i].first;
        int y = empty_spaces[i].second;
        
        graph[x][y] = 1;
        place_walls(i + 1, graph, walls_placed + 1);
        graph[x][y] = 0;
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    cin >> n >> m;
    vector<vector<int>> graph(n, vector<int>(m));

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            cin >> graph[i][j];
            if (graph[i][j] == 0) {
                empty_spaces.push_back({i, j});
            }
            if (graph[i][j] == 2) {
                viruses.push_back({j, i});
            }
        }
    }

    place_walls(0, graph, 0);
    cout << answer;

    return 0;
}