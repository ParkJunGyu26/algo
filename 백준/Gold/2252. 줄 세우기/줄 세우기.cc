#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int n, m;
vector<vector<int>> graph;
vector<int> degree;
vector<int> answer;

void topologySort() {
	queue<int> q;
	for (int i = 1; i < n+1; i++) if (degree[i] == 0) q.push(i);

	while (!q.empty()) {
		int node = q.front();
		q.pop();

		answer.push_back(node);

		for (int i = 0; i < graph[node].size(); i++) {
			degree[graph[node][i]]--;

			if (degree[graph[node][i]] == 0) q.push(graph[node][i]);
		}
	}

	for (auto ans : answer) cout << ans << " ";
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n >> m;
	graph.resize(n+1);
	degree.resize(n+1);
	
	for (int i = 0; i < m; i++) {
		int a, b;
		cin >> a >> b;

		graph[a].push_back(b); // a(선행) -> b(후행)
		degree[b]++; // 진입차수를 카운팅하자!
	}

	topologySort();
}