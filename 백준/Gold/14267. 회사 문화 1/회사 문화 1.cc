#include <iostream>
#include <vector>

using namespace std;

int n, m;
int dp[100001];
vector<vector<int>> graph;

void dfs(int parent) {
	for (int i = 0; i < graph[parent].size(); i++) {
		dp[graph[parent][i]] += dp[parent];
	}

	for (int i = 0; i < graph[parent].size(); i++) dfs(graph[parent][i]);
}

int main() {
	ios_base::sync_with_stdio(false);cout.tie(NULL);cin.tie(NULL);

	cin >> n >> m;
	graph.resize(n+1);
	for (int i = 1; i <= n; i++) {
		int p;
		cin >> p;
		if (p == -1) continue;

		graph[p].push_back(i);
	}

	for (int i = 0; i < m; i++) {
		int index, w;
		cin >> index >> w;
		dp[index] += w;
	}

	dfs(1);

	for (int i = 1; i <= n; i++) cout << dp[i] << " ";
}