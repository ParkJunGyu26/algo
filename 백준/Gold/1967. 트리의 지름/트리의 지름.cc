#include <iostream>
#include <vector>

using namespace std;

int answer = 0;
int visited[10001] = {0};

int max(int a, int b) {
	if (a > b) return a;
	return b;
}

void dfs(int node, vector<vector<pair<int, int>>> &graph, int cnt) {
	
	for (auto g : graph[node]) {
		if (visited[g.first] == 0) {
			cnt += g.second;
			visited[g.first] = 1;

			answer = max(answer, cnt);

			dfs(g.first, graph, cnt);

			visited[g.first] = 0;
			cnt -= g.second;
		}
	}

	return;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	int n, a, b, w;
	cin >> n;
	vector<vector<pair<int, int>>> graph(n+1);
	for (int i = 0; i < n-1; i++) {
		cin >> a >> b >> w;
		graph[a].push_back({b, w});
		graph[b].push_back({a, w});
	}

	for (int i = 1; i <= n; i++) {
		visited[i] = 1;
		dfs(i, graph, 0);

		visited[i] = 0;
	}

	cout << answer;
}