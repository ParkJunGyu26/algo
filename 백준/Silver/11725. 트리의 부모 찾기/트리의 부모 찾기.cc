#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int n, a, b;
int visited[100001] = {0};

void bfs(int node, vector<vector<int>> &graph) {
	queue<int> q;
	q.push(node);
	visited[node] = 1;

	while (!q.empty()) {
		int x = q.front();
		q.pop();

		for (int i = 0; i < graph[x].size(); i++) {
			if (visited[graph[x][i]] == 0) {
				visited[graph[x][i]] = x;
				q.push(graph[x][i]);
			}
		}
	}

	return;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n;
	vector<vector<int>> graph(n+1);
	for (int i = 0; i < n-1; i++) {
		cin >> a >> b;
		graph[a].push_back(b);
		graph[b].push_back(a);
	}

	bfs(1, graph);
	for (int i = 2; i <= n; i++)
		cout << visited[i] << "\n";
}