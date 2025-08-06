#include <string>
#include <queue>
#include <vector>
#include <unordered_set>
#include <iostream>

using namespace std;

int dx[4] = {0, 1, 0, -1};
int dy[4] = {-1, 0, 1, 0};
vector<vector<bool>> visited;
vector<vector<int>> vec;

void bfs(int xx, int yy, vector<vector<int>>& graph, int n, int m) {
    queue<pair<int, int>> q;
    q.push({xx, yy});
    
    vector<pair<int, int>> dots;
    dots.push_back({xx, yy});
    
    int cnt = 1;
    while (!q.empty()) {
        int x = q.front().first;
        int y = q.front().second;
        q.pop();
        
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if (-1 < nx && nx < m && -1 < ny && ny < n) {
                if (!visited[ny][nx] && graph[ny][nx] == 1) {
                    visited[ny][nx] = true;
                    q.push({nx, ny});
                    cnt++;
                    dots.push_back({nx, ny});
                }
            }
        }
    }
    
    unordered_set<int> us;
    for (auto dot : dots) {
        us.insert(dot.first);
    }
    
    for (auto x : us) {
        vec[x].push_back(cnt);
    }
}

int solution(vector<vector<int>> land) {
    int answer = 0;
    int n = land.size();
    int m = land[0].size();
    
    visited.resize(n, vector<bool>(m));
    vec.resize(m);
    
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            if (!visited[i][j] && land[i][j] == 1) {
                visited[i][j] = true;
                bfs(j, i, land, n, m);
            }
        }
    }
    
    int max = 0;
    for (int i = 0; i < m; i++) {
        int cnt = 0;
        for (int j = 0; j < vec[i].size(); j++) {
            cnt += vec[i][j];
        }
        
        if (max < cnt) max = cnt;
    }
    
    return max;
}