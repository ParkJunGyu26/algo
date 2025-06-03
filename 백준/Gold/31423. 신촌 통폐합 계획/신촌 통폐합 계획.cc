#include <iostream>
#include <string>
#include <vector>

using namespace std;

int n;
vector<string> vec;
vector<vector<int>> graph;

void dfs(int index) {
	cout << vec[index];

	for (int i = 0; i < graph[index].size(); i++) dfs(graph[index][i]);
}

int main() {
	ios_base::sync_with_stdio(false);cout.tie(NULL);cin.tie(NULL);
	cin >> n;
	vec.resize(n);
	graph.resize(n);
	for (int i = 0; i < n; i++) cin >> vec[i];

	int root = -1;
	for (int i = 0; i < n-1; i++) {
		int a, b;
		cin >> a >> b;

		graph[a-1].push_back(b-1);

		root = a-1;
	}

	dfs(root);
}