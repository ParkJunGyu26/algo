#include <iostream>
#include <algorithm>
#include <vector>
#include <cstring>

using namespace std;

int n;
int graph[500][500];
int dp[500][500];
int dx[4] = {1, -1, 0, 0};
int dy[4] = {0, 0, 1, -1};

int dfs(int x, int y) {
    // 이미 계산된 경우 바로 반환
    if (dp[y][x] != 0) return dp[y][x];

    // 현재 위치는 최소 1칸 방문 가능
    dp[y][x] = 1;

    for (int i = 0; i < 4; i++) {
        int nx = x + dx[i];
        int ny = y + dy[i];

        // 범위 체크 및 대나무 양 조건 확인
        if (nx >= 0 && nx < n && ny >= 0 && ny < n && graph[y][x] < graph[ny][nx]) {
            dp[y][x] = max(dp[y][x], dfs(nx, ny) + 1);
        }
    }

    return dp[y][x];
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> n;
    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++) cin >> graph[i][j];
    
    // 메모이제이션 배열 초기화
    memset(dp, 0, sizeof(dp));

    int answer = 0;
    // 모든 지점에서 시작해서 최대 이동 칸 수 찾기
    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++) 
            answer = max(answer, dfs(j, i));

    cout << answer;

    return 0;
}