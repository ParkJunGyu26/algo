#include <iostream>
#include <vector>
#include <climits>
#include <algorithm>

using namespace std;

int answer = INT_MAX;
int n;
vector<vector<int>> graph;
vector<bool> visited;

void dfs(int start, int node, vector<int>& hubo, int res) {
	if (hubo.size() == n) {
		if (graph[node][start] != 0) answer = min(answer, res+graph[node][start]);
		return;
	}

	for (int i = 0; i < n; i++) {
		if (i == node || visited[i] || graph[node][i] == 0) continue;

		visited[i] = true;
		hubo.push_back(i);

		dfs(start, i, hubo, res+graph[node][i]);

		visited[i] = false;
		hubo.pop_back();
	}
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n;
	graph.resize(n, vector<int>(n));
	visited.resize(n);
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) cin >> graph[i][j];
	}

	for (int i = 0; i < n; i++) {
		vector<int> hubo;
		hubo.push_back(i);
		visited[i] = true;
		dfs(i, i, hubo, 0);
		fill(visited.begin(), visited.end(), false);
	}

	cout << answer;
}