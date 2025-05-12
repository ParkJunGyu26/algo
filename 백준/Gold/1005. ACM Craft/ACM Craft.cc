#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

int t;

void topologySort(int n, int k, vector<int>& inDegree, vector<int>& res, vector<vector<int>>& graph, int w) {
	queue<int> q;
	vector<int> dp(n+1);
	for (int i = 1; i < n+1; i++) {
		if (inDegree[i] == 0) {
			q.push(i);
			dp[i] = res[i];
		}
	}

	while (!q.empty()) {
		int node = q.front();
		q.pop();

		for (int i = 0; i < graph[node].size(); i++) {
			int nNode = graph[node][i];
			
			inDegree[nNode]--;
			dp[nNode] = max(dp[nNode], dp[node]);
			if (inDegree[nNode] == 0) {
				q.push(nNode);
				dp[nNode] += res[nNode];
			}
		}

		if (inDegree[w] == 0) {
			cout << dp[w] << "\n";

			return;
		}

	}

}

// 사이클 없음
int main() {
	ios_base::sync_with_stdio(false);cin.tie(NULL);cout.tie(NULL);

	cin >> t;
	while(t--) {
		int n, k, w;
		cin >> n >> k;
		vector<int> inDegree(n+1);
		vector<int> res(n+1);
		vector<vector<int>> graph(n+1);
		for (int i = 1; i < n+1; i++) cin >> res[i];
		for (int i = 0; i < k; i++) {
			int x, y;
			cin >> x >> y;

			graph[x].push_back(y);
			inDegree[y]++;
		}

		cin >> w;

		topologySort(n, k, inDegree, res, graph, w);
	}
}