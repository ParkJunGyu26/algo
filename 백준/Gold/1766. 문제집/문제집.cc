#include <iostream>
#include <vector>
#include <queue>
#include <unordered_map>

using namespace std;

int n, m;
vector<vector<int>> graph;
vector<int> degree, answer;
unordered_map<int, vector<int>> um;

void topologySort() {
	priority_queue<int> pq;
	for (int i = 1; i <= n; i++) if (degree[i] == 0) pq.push(-i);

	while (!pq.empty()) {
		int node = -pq.top();
		pq.pop();

		answer.push_back(node);

		for (int i = 0; i < graph[node].size(); i++) {
			int nNode = graph[node][i];

			if (--degree[nNode] == 0) {
				pq.push(-nNode);
			}
		}
	}

	for (auto ans : answer) cout << ans << " ";
}

int main() {
	ios_base::sync_with_stdio(false);cout.tie(NULL);cin.tie(NULL);

	cin >> n >> m;
	graph.resize(n+1);
	degree.resize(n+1);
	for (int i = 0; i < m; i++) {
		int a, b;
		cin >> a >> b;
		graph[a].push_back(b);
		degree[b]++;
	}

	topologySort();
}