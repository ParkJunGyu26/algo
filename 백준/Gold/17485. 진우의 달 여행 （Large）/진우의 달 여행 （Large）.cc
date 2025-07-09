#include <iostream>
#include <vector>
#include <climits>
#include <algorithm>

using namespace std;

int n, m;
vector<vector<int>> graph, dp;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n >> m;
	dp.resize(m, vector<int>(3, 201));
	graph.resize(n, vector<int>(m));
	for (int i = 0; i < n; i++)
		for (int j = 0; j < m; j++) cin >> graph[i][j];
	
	for (int i = 0; i < m; i++) {
		for (int j = -1; j <= 1; j++) {
			if (i + j < 0 || i + j >= m) continue;

			dp[i][(j+1)%3] = graph[1][i] + graph[0][i+j];
		}
	}

	for (int i = 2; i < n; i++) {
		vector<vector<int>> newDp(m, vector<int>(3, INT_MAX));

		for (int j = 0; j < m; j++) {
			for (int k = -1; k <= 1; k++) {
				if (j + k < 0 || j + k >= m) continue;

				newDp[j][k+1] = min(dp[j+k][(k+2)%3], dp[j+k][(k+3)%3]) + graph[i][j];
			}
		}

		dp = newDp;
	}

	int answer = INT_MAX;
	for (int i = 0; i < m; i++) {
		for (int j = 0; j < 3; j++) answer = min(answer, dp[i][j]);
	}
	cout << answer;
}