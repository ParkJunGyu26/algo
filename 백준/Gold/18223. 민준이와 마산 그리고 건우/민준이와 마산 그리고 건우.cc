#include <iostream>
#include <vector>
#include <queue>

#define MAX_VALUE 5000*10000*10000+1

using namespace std;

int v, e, p;
vector<vector<pair<int, int>>> graph;

vector<long long> dijkstra(int startNode) {
	vector<long long> res(v+1, MAX_VALUE);
	res[startNode] = 0;

	priority_queue<pair<long long, int>> pq;
	pq.push({0, startNode});

	while(!pq.empty()) {
		long long dist = -pq.top().first;
		int now_node = pq.top().second;
		pq.pop();

		if (res[now_node] < dist) continue;

		for (int i = 0; i < graph[now_node].size(); i++) {
			int next_node = graph[now_node][i].first;
			long long next_dist = graph[now_node][i].second;

			if (res[next_node] > res[now_node] + next_dist) {
				res[next_node] = res[now_node] + next_dist;
				pq.push({-res[next_node], next_node});
			}
		}
	}

	return res;
}

int main() {
	ios_base::sync_with_stdio(false);cin.tie(NULL);cout.tie(NULL);

	cin >> v >> e >> p;
	graph.resize(v+1);
	for (int i = 0; i < e; i++) {
		int a, b, c;
		cin >> a >> b >> c;
		graph[a].push_back({b ,c});
		graph[b].push_back({a, c});
	}
	// 출발 -> 마산
	vector<long long> start = dijkstra(1);

	// 건우 -> 마산
	vector<long long> gunwoo = dijkstra(p);

	if (start[v] == (start[p] + gunwoo[v])) cout << "SAVE HIM";
	else cout << "GOOD BYE";
}