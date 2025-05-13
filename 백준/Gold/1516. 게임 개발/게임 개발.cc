#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

int n;
vector<int> degree, dp, res;
vector<vector<int>> graph;

void topologySort() {
	queue<int> q;
	for (int i = 1; i < n+1; i++) {
		if (degree[i] == 0) {
			q.push(i);
			dp[i] = res[i];
		}
	}

	while(!q.empty()) {
		int node = q.front();
		q.pop();

		for (int i = 0; i < graph[node].size(); i++) {
			int nNode = graph[node][i];
			dp[nNode] = max(dp[nNode], dp[node]);
			if (--degree[nNode] == 0) {
				q.push(nNode);
				dp[nNode] += res[nNode];
			}
		}
	}

	for (int i = 1; i < n+1; i++) cout << dp[i] << "\n";
}

int main() {
	ios_base::sync_with_stdio(false);cin.tie(NULL);cout.tie(NULL);

	cin >> n;
	graph.resize(n+1);
	dp.resize(n+1);
	res.resize(n+1);
	degree.resize(n+1);
	for (int i = 0; i < n; i++) {
		vector<int> tmp;
		int temp;
		while (true) {
			cin >> temp;
			if (temp == -1) break;
			tmp.push_back(temp);
		}

		res[i+1] = tmp[0];
		for (int j = 1; j < tmp.size(); j++) {
			graph[tmp[j]].push_back(i+1);
			degree[i+1]++;
		}
	}

	topologySort();
}