#include<vector>
#include<queue>

using namespace std;

int dx[4] = {1, -1, 0, 0};
int dy[4] = {0, 0, 1, -1};

vector<vector<int>> res;

int n, m;

void bfs(vector<vector<int>>& maps) {
    queue<pair<int, int>> q;
    q.push(make_pair(0, 0));
    res[0][0] = 1;
    
    while (!q.empty()) {
        int x = q.front().first;
        int y = q.front().second;
        q.pop();
        
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if (-1 < nx && nx < m && -1 < ny && ny < n && maps[ny][nx] == 1 && res[ny][nx] == 0) {
                res[ny][nx] = res[y][x] + 1;
                q.push(make_pair(nx, ny));
            }
        }
    }
    
    return;
}

int solution(vector<vector<int> > maps)
{
    int answer;
    
    n = maps.size();
    m = maps[0].size();
    
    for (int i = 0; i < n; i++) {
        vector<int> tmp;
        for (int j = 0; j < m; j++)
            tmp.push_back(0);
        res.push_back(tmp);
    }

    bfs(maps);
    
    answer = res[n-1][m-1];
    if (answer == 0) answer = -1;
    
    return answer;
}