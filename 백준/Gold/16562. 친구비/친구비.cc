#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int n, m, k;
vector<int> res, graph, friends;

int FIND(int x) {
	if (graph[x] == x) return x;

	return graph[x] = FIND(graph[x]);
}

void UNION(int a, int b) {
	a = FIND(a);
	b = FIND(b);

	if (a == b) return;

	if (res[a] < res[b]) {
		graph[b] = a;
	} else if (res[a] > res[b]) {
		graph[a] = b;
	} else {
		if (a < b) graph[b] = a;
		else graph[a] = b;
	}
}

bool isFind(int a, int b) {
	a = FIND(a);
	b = FIND(b);

	return a == b;
}

bool cmp(int a, int b) {
	return res[a] < res[b];
}

// 유니온 파인드
int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n >> m >> k;
	res.resize(n+1); // 친구 비용
	graph.resize(n+1); // 루트 노드
	for (int i = 1; i <= n; i++) {
		cin >> res[i];
		graph[i] = i;
	}
	
	for (int i = 0; i < m; i++) {
		int v, w;
		cin >> v >> w;

		UNION(v, w);
	}

	for (int i = 1; i < n+1; i++) FIND(i);

	int answer = 0;
	for (int i = 1; i <= n; i++) if (find(friends.begin(), friends.end(), graph[i]) == friends.end()) friends.push_back(graph[i]);
	for (int i = 0; i < friends.size(); i++) answer += res[friends[i]];

	if (answer <= k) cout << answer;
	else cout << "Oh no";
}