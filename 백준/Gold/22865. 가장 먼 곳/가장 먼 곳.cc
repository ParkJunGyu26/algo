#include <iostream>
#include <vector>
#include <climits>
#include <queue>

using namespace std;

int n, m;

struct Friend{
int A;
int B;
int C;
};

// graph : {node, cost}
void dijkstra(int node, vector<vector<pair<int, int>>> &graph, int a, int b, int c, vector<Friend> &hubo, int cnt) {
	vector<int> res(n+1, INT_MAX);
	res[node] = 0;
	priority_queue<pair<int, int>> pq; // -거리, 노드
	pq.push({0, node});

	while (!pq.empty()) {
		int now_cost = -pq.top().first;
		int now_node = pq.top().second;
		pq.pop();

		for (int i = 0; i < graph[now_node].size(); i++) {
			if (now_cost + graph[now_node][i].second > res[graph[now_node][i].first]) continue;

			res[graph[now_node][i].first] = now_cost + graph[now_node][i].second;
			
			pq.push({-res[graph[now_node][i].first], graph[now_node][i].first});
		}
	}

	pair<int, int> result = {1, INT_MAX}; // {노드, 비용}

	for (int i = 1; i <= n; i++) {
		if (res[i] == 0 || i == a || i == b || i == c) continue;

		if (cnt == 0) hubo[i].A = res[i];
		else if (cnt == 1) hubo[i].B = res[i];
		else hubo[i].C = res[i];
	}
}

// 다익스트라 3번 돌려서 최소값?
int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n;
	vector<vector<pair<int, int>>> node(n+1);
	vector<int> friends(3);
	for (int i = 0; i < 3; i++) cin >> friends[i];
	
	cin >> m;
	for (int i = 0; i < m; i++) {
		int d, e, l;
		cin >> d >> e >> l;
		node[d].push_back({e, l});
		node[e].push_back({d, l});
	}

	vector<Friend> hubo(n+1);
	for (int i = 0; i < 3; i++) {
		dijkstra(friends[i], node, friends[0], friends[1], friends[2], hubo, i);
	}

	pair<int, int> answer = {1, 0}; // {노드, 비용}
	for (int i = 1; i < n+1; i++) {
		if (i == friends[0] || i == friends[1] || i == friends[2]) continue;

		int target = min(hubo[i].A, min(hubo[i].B, hubo[i].C));

		if (target > answer.second) answer = {i, target};
	}

	cout << answer.first; // 노드 번호를 출력해야됨
}