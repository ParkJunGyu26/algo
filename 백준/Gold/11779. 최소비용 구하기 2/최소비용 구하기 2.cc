#include <iostream>
#include <vector>
#include <queue>
#include <climits>

using namespace std;

int n, m, a, b, c;
pair<int, vector<int>> dist[1001];

void dijkstra(int node, vector<vector<pair<int, int>>> &graph, int target) {
	priority_queue<pair<int, int>> pq;
	for (int i = 1; i <= n; i++) dist[i].first = INT_MAX;
	dist[node] = {0, {node}};
	pq.push({0, node});

	while(!pq.empty()) {
		int now_dist = -pq.top().first;
		int now_node = pq.top().second;
		pq.pop();

		if (now_node == target) {
			cout << now_dist << "\n" << dist[now_node].second.size() << "\n";
			for (auto d : dist[now_node].second)
				cout << d << " ";
			return;
		}

		for (int i = 0; i < graph[now_node].size(); i++) {
			int next_dist = graph[now_node][i].second;
			int next_node = graph[now_node][i].first;
			vector<int> next_track = dist[now_node].second;
			next_track.push_back(next_node);

			if (dist[next_node].first > now_dist + next_dist) {
				dist[next_node] = {now_dist + next_dist, next_track};
				pq.push({-(now_dist + next_dist), next_node});
			}
		}
	}
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	int v1, v2;

	cin >> n >> m;
	vector<vector<pair<int, int>>> graph(n+1);
	for (int i = 0; i < m; i++) {
		cin >> a >> b >> c;
		graph[a].push_back({b, c});
	}

	cin >> v1 >> v2;
	dijkstra(v1, graph, v2);

	// for (int i = 1; i <= n; i++)
	// 	cout << "i : " << i << ", dist[i] : " << dist[i] << "\n";
}