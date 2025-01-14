#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int n, m, r;
int dx[4] = {0, 1, 0, -1};
int dy[4] = {1, 0, -1, 0};

void setting(int start_x, int start_y, vector<vector<int>> &grid) {
    int rows = n - 2*start_y;    
    int cols = m - 2*start_x;    
    int total = 2*(rows + cols) - 4;  
    int rotate_cnt = r % total;

    vector<int> temp;  
    
    // 시작점은 왼쪽 위 모서리에서 시작
    int x = start_x;
    int y = start_y;
    int dir = 0;
    
    // 반시계 방향으로 값 저장
    for(int i = 0; i < total; i++) {
        temp.push_back(grid[y][x]);
        
        int nx = x + dx[dir];
        int ny = y + dy[dir];
        
        if(nx < start_x || nx >= m-start_x || ny < start_y || ny >= n-start_y) {
            dir = (dir + 1) % 4;
            nx = x + dx[dir];
            ny = y + dy[dir];
        }
        
        x = nx;
        y = ny;
    }
    
    // 반시계 방향 회전
    rotate(temp.rbegin(), temp.rbegin() + rotate_cnt, temp.rend());
    
    // 회전된 값들을 다시 grid에 넣기
    x = start_x;
    y = start_y;
    dir = 0;
    int idx = 0;
    
    while(idx < total) {
        grid[y][x] = temp[idx++];
        
        int nx = x + dx[dir];
        int ny = y + dy[dir];
        
        if(nx < start_x || nx >= m-start_x || ny < start_y || ny >= n-start_y) {
            dir = (dir + 1) % 4;
            nx = x + dx[dir];
            ny = y + dy[dir];
        }
        
        x = nx;
        y = ny;
    }
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n >> m >> r;
	vector<vector<int>> grid(n, vector<int>(m));
	for (int i = 0; i < n; i++)
		for (int j = 0; j < m; j++) cin >> grid[i][j];

	for (int i = 0; i < min(n, m)/2; i++) {
		setting(i, i, grid);
	}

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) cout << grid[i][j] << " ";
		cout << "\n";
	}
}