#include <iostream>
#include <queue>
#include <vector>

#define MAX_NUM 100000

using namespace std;

int w, h;
vector<vector<vector<vector<int>>>> dp; 
// i(y), j(x), 어떻게 왔는지, 방향 전환 가능한지
// 0 : 왼쪽 -> 오른쪽 / 1 : 상 -> 하
// 0 : 방향 전환 가능 / 1 : 방향 전환 불가능

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> w >> h;
	dp.resize(h, vector<vector<vector<int>>>(w, vector<vector<int>>(2, vector<int>(2))));

	dp[0][0][0][0] = dp[0][0][1][0] = 1;

	// 첫 행 초기화 (오른쪽으로만 이동 가능)
    for (int j = 1; j < w; j++) {
        dp[0][j][0][0] = 1; // 왼쪽에서 오른쪽으로, 방향 전환 가능
    }
    
    // 첫 열 초기화 (아래로만 이동 가능)
    for (int i = 1; i < h; i++) {
        dp[i][0][1][0] = 1; // 위에서 아래로, 방향 전환 가능
    }

	for (int i = 0; i < h-1; i++) {
		for (int j = 0; j < w-1; j++) {
			// 왼쪽->오른쪽 & 방향 전환 가능
			dp[i+1][j+1][0][0] = (dp[i+1][j][0][0] + dp[i+1][j][0][1]) % MAX_NUM;
			// 왼쪽->오른쪽 & 방향 전환 불가능
			dp[i+1][j+1][0][1] = (dp[i+1][j][1][0]) % MAX_NUM;
			// 상->하 & 방향 전환 가능
			dp[i+1][j+1][1][0] = (dp[i][j+1][1][0] + dp[i][j+1][1][1]) % MAX_NUM;
			// 상->하 & 방향 전환 불가능
			dp[i+1][j+1][1][1] = (dp[i][j+1][0][0]) % MAX_NUM;
		}
	}

	cout << (dp[h-1][w-1][0][0] + dp[h-1][w-1][0][1] + dp[h-1][w-1][1][0] + dp[h-1][w-1][1][1]) % MAX_NUM;
}