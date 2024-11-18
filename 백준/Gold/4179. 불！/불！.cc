#include <iostream>
#include <stack>
#include <string>
#include <queue>
#include <vector>
#include <climits>

#define Y second
#define X first

using namespace std;

int r, c;
char g[1000][1000];
int dx[4] = {1, -1, 0, 0};
int dy[4] = {0, 0, 1, -1};

void bfs(queue<pair<int, int>>& q, vector<vector<int>>& v) {
    while (!q.empty()) {
        int x = q.front().X;
        int y = q.front().Y;
        q.pop();

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= c || ny < 0 || ny >= r) continue; // 경계값 처리 개선
            if (v[ny][nx] > 0 || g[ny][nx] == '#') continue; // 방문 여부와 벽 체크

            q.push({nx, ny});
            v[ny][nx] = v[y][x] + 1;
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int j_x = -1, j_y = -1;
    string tmp;
    queue<pair<int, int>> jihoon;
    queue<pair<int, int>> fire;
    vector<vector<int>> Jihoon(1000, vector<int>(1000, 0)); 
    vector<vector<int>> Fire(1000, vector<int>(1000, 0)); 
    vector<pair<int, int>> fir;
    
    cin >> r >> c;
    for (int i = 0; i < r; i++) {
        cin >> tmp;
        for (int j = 0; j < c; j++) {
            g[i][j] = tmp[j];
            if (g[i][j] == 'J') {
                jihoon.push({j, i});
                j_x = j;
                j_y = i;
                Jihoon[i][j] = 1; // 시작점 초기화
            }
            if (g[i][j] == 'F') {
                fire.push({j, i});
                fir.push_back({j, i});
                Fire[i][j] = 1; // 시작점 초기화
            }
        }
    }
    
    bfs(jihoon, Jihoon); // 지훈
    
    // 모든 불의 시작점 초기화
    for (auto f : fir) {
        Fire[f.Y][f.X] = 1;
    }
    bfs(fire, Fire); // 불

    int answer = INT_MAX;
    bool possible = false;
    
    // 경계값에서 탈출 조건 확인
    for (int i = 0; i < r; i++) {
        for (int j = 0; j < c; j++) {
            // 경계 확인
            if (i == 0 || i == r-1 || j == 0 || j == c-1) {
                // 지훈이가 해당 위치에 도달했는지 확인
                if (Jihoon[i][j] == 0) continue;
                
                // 불보다 먼저 도착했는지 확인
                if (Fire[i][j] == 0 || Jihoon[i][j] < Fire[i][j]) {
                    possible = true;
                    answer = min(answer, Jihoon[i][j]);
                }
            }
        }
    }

    if (possible) cout << answer;
    else cout << "IMPOSSIBLE";
}