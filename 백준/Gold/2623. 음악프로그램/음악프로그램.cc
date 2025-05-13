#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int n, m;
vector<vector<int>> graph;
vector<int> degree;

void topologySort() {
	queue<int> q;
	vector<int> answer;
	for (int i = 1; i < n+1; i++) {
		if (degree[i] == 0) {
			q.push(i);
			answer.push_back(i);
		}
	}

	while (!q.empty()) {
		int node = q.front();
		q.pop();

		for (int i = 0; i < graph[node].size(); i++) {
			int nNode = graph[node][i];
			if (--degree[nNode] == 0) {
				q.push(nNode);
				answer.push_back(nNode);
			}
		}
	}

	if (answer.size() != n) cout << 0;
	else for (auto a : answer) cout << a << "\n";
}

int main() {
	ios_base::sync_with_stdio(false);cin.tie(NULL);cout.tie(NULL);

	cin >> n >> m;
	graph.resize(n+1);
	degree.resize(n+1);
	for (int i = 0; i < m; i++) {
		int L;
		cin >> L;
		vector<int> tmp(L);
		
		for (int j = 0; j < L; j++) cin >> tmp[j];
		for (int j = 0; j < L-1; j++) {
			graph[tmp[j]].push_back(tmp[j+1]);
			degree[tmp[j+1]]++;
		}
	}

	topologySort();
}