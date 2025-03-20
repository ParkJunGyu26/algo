#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int n, m;
vector<vector<int>> board;
// dp[i][j][k]: (i,j)위치에서 k방향으로 왔을 때의 최대값
// k = 0: 위에서 아래로
// k = 1: 왼쪽에서 오른쪽으로
// k = 2: 오른쪽에서 왼쪽으로
vector<vector<vector<int>>> dp;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> n >> m;
    board.assign(n, vector<int>(m));
    dp.assign(n, vector<vector<int>>(m, vector<int>(3, -1e9)));

    for(int i = 0; i < n; i++) {
        for(int j = 0; j < m; j++) {
            cin >> board[i][j];
        }
    }

    // 시작점 초기화
    dp[0][0][0] = dp[0][0][1] = dp[0][0][2] = board[0][0];

    // 첫 번째 행은 왼쪽에서 오른쪽으로만 이동 가능
    for(int j = 1; j < m; j++) {
        dp[0][j][1] = dp[0][j-1][1] + board[0][j];
    }

    // 나머지 행 처리
    for(int i = 1; i < n; i++) {
        // 각 행의 왼쪽에서 오른쪽으로
        vector<int> left(m, -1e9);
        left[0] = max(dp[i-1][0][0], dp[i-1][0][1]);
        left[0] = max(left[0], dp[i-1][0][2]) + board[i][0];
        
        for(int j = 1; j < m; j++) {
            left[j] = max(left[j-1], max(dp[i-1][j][0], dp[i-1][j][1]));
            left[j] = max(left[j], dp[i-1][j][2]) + board[i][j];
        }

        // 각 행의 오른쪽에서 왼쪽으로
        vector<int> right(m, -1e9);
        right[m-1] = max(dp[i-1][m-1][0], dp[i-1][m-1][1]);
        right[m-1] = max(right[m-1], dp[i-1][m-1][2]) + board[i][m-1];
        
        for(int j = m-2; j >= 0; j--) {
            right[j] = max(right[j+1], max(dp[i-1][j][0], dp[i-1][j][1]));
            right[j] = max(right[j], dp[i-1][j][2]) + board[i][j];
        }

        // 위에서 아래로
        for(int j = 0; j < m; j++) {
            dp[i][j][0] = max(dp[i-1][j][0], dp[i-1][j][1]);
            dp[i][j][0] = max(dp[i][j][0], dp[i-1][j][2]) + board[i][j];
        }

        // 각 위치의 최댓값 갱신
        for(int j = 0; j < m; j++) {
            dp[i][j][1] = left[j];
            dp[i][j][2] = right[j];
        }
    }

    int answer = max({dp[n-1][m-1][0], dp[n-1][m-1][1], dp[n-1][m-1][2]});
    cout << answer;

    return 0;
}