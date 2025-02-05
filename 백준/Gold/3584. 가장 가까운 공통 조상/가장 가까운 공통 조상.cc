#include <iostream>
#include <vector>

using namespace std;

int t, n;

void dfs(vector<vector<int>> &graph, vector<int> &visited, int node) {
	if (visited[node] >= 2) {
		cout << node << "\n";
		return;
	}

	for (int i = 0; i < graph[node].size(); i++) {
		visited[graph[node][i]]++;
		if (visited[graph[node][i]] > 1) {
			cout << graph[node][i] << "\n";
			return;
		}
		dfs(graph, visited, graph[node][i]);
	}
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> t;
	for (int _ = 0; _ < t; _++) {
		cin >> n;
		vector<vector<int>> graph(n+1);
		vector<int> visited(n+1);
		int a, b;
		for (int i = 0; i < n-1; i++) {
			cin >> a >> b;
			graph[b].push_back(a);
		}
		cin >> a >> b;
		visited[a]++;
		dfs(graph, visited, a);
		visited[b]++;
		dfs(graph, visited, b);
	}
}