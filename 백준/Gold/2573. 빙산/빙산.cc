#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int n, m, temp;
vector<vector<int>> graph;
vector<vector<int>> visited;
int dx[4] = {1, -1, 0, 0};
int dy[4] = {0, 0, 1, -1};

int max(int a, int b) {
    if (a > b) return a;
    return b;
}

// 빙산 개수 확인 bfs
int bfs(int xx, int yy, vector<vector<int>> &v) {
    queue<pair<int, int>> q;
    q.push({xx, yy});

    while (!q.empty()) {
        int x = q.front().first;
        int y = q.front().second;
        q.pop();

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < m && ny >= 0 && ny < n && v[ny][nx] == 0 && graph[ny][nx] > 0) {
                v[ny][nx] = 1;
                q.push({nx, ny});
            }
        }
    }
    return 1;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> n >> m;
    for (int i = 0; i < n; i++) {
        vector<int> tmp;
        for (int j = 0; j < m; j++) {
            cin >> temp;
            tmp.push_back(temp);
        }
        graph.push_back(tmp);
    }

    bool check = false;
    int answer = 0;
    
    // 무한 루프로 변경
    while (true) {
        // 빙산 개수 확인
        int cnt = 0;
        vector<vector<int>> visited(n, vector<int>(m, 0));
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (graph[i][j] > 0 && visited[i][j] == 0) {
                    visited[i][j] = 1;
                    cnt += bfs(j, i, visited);
                }
            }
        }

        if (cnt >= 2) {
            check = true;
            break;
        }
        
        // 빙산이 모두 녹았는지 확인
        bool allMelted = true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (graph[i][j] > 0) {
                    allMelted = false;
                    break;
                }
            }
            if (!allMelted) break;
        }
        
        if (allMelted) break;

        // 빙산 녹이기 - 임시 배열 사용
        vector<vector<int>> temp_graph = graph;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (graph[i][j] > 0) {
                    int count = 0;
                    for (int k = 0; k < 4; k++) {
                        int nx = j + dx[k];
                        int ny = i + dy[k];

                        if (nx >= 0 && nx < m && ny >= 0 && ny < n && graph[ny][nx] == 0) {
                            count++;
                        }
                    }
                    temp_graph[i][j] = max(0, graph[i][j] - count);
                }
            }
        }
        
        graph = temp_graph;
        answer++;
    }

    if (check) cout << answer;
    else cout << 0;
    
    return 0;
}