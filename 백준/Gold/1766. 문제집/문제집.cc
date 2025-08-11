#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int n, m;
vector<int> count_vec, answer;
vector<vector<int>> graph;

void topology() {
	priority_queue<int> pq;
	for (int i = 1; i <= n; i++) {
		if (count_vec[i] == 0) {
			pq.push(-i);
		}
	}

	while(!pq.empty()) {
		int node = -pq.top();
		pq.pop();

		for (int nextNode : graph[node]) {

			if(--count_vec[nextNode] == 0) {
				pq.push(-nextNode);
			}
		}

		answer.push_back(node);
	}
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n >> m;
	graph.resize(n+1);
	count_vec.resize(n+1);

	for (int i = 0; i < m; i++) {
		int a, b;
		cin >> a >> b;

		graph[a].push_back(b);
		count_vec[b]++;
	}

	topology();
	for (auto ans : answer) cout << ans << " ";
}