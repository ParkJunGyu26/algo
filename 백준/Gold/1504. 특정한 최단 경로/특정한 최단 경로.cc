#include <iostream>
#include <queue>
#include <vector>
#include <climits>

using namespace std;

int n, e, a, b, c, v1, v2;
int dist[801];

void dijkstra(int node, vector<vector<pair<int ,int>>> &graph) {
	priority_queue<pair<int, int>> pq;
	fill(dist, dist+n+1, INT_MAX);
	dist[node] = 0;
	pq.push({0, node});

	while (!pq.empty()) {
		int current_dist = -pq.top().first;
		int currnet_node = pq.top().second;
		pq.pop();

		for (int i = 0; i < graph[currnet_node].size(); i++) {
			int next_node = graph[currnet_node][i].first;
			int next_dist = current_dist + graph[currnet_node][i].second;

			if (next_dist < dist[next_node]) {
				dist[next_node] = next_dist;
				pq.push({-next_dist, next_node});
			}
		}
	}
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> n >> e;
    vector<vector<pair<int ,int>>> graph(n+1);
    for (int i = 0; i < e; i++) {
        cin >> a >> b >> c;
        graph[a].push_back({b, c});
        graph[b].push_back({a, c});
    }
    cin >> v1 >> v2;

    long long route1 = 0, route2 = 0;
    
    // 첫 번째 경로: 1 -> v1 -> v2 -> N
    dijkstra(1, graph);
    long long dist_1_to_v1 = dist[v1];
    
    dijkstra(v1, graph);
    long long dist_v1_to_v2 = dist[v2];
    
    dijkstra(v2, graph);
    long long dist_v2_to_n = dist[n];
    
    route1 = (dist_1_to_v1 < INT_MAX && dist_v1_to_v2 < INT_MAX && dist_v2_to_n < INT_MAX) 
             ? dist_1_to_v1 + dist_v1_to_v2 + dist_v2_to_n 
             : INT_MAX;

    // 두 번째 경로: 1 -> v2 -> v1 -> N
    dijkstra(1, graph);
    long long dist_1_to_v2 = dist[v2];
    
    dijkstra(v2, graph);
    long long dist_v2_to_v1 = dist[v1];
    
    dijkstra(v1, graph);
    long long dist_v1_to_n = dist[n];
    
    route2 = (dist_1_to_v2 < INT_MAX && dist_v2_to_v1 < INT_MAX && dist_v1_to_n < INT_MAX) 
             ? dist_1_to_v2 + dist_v2_to_v1 + dist_v1_to_n 
             : INT_MAX;

    if (min(route1, route2) == INT_MAX) cout << -1;
    else cout << min(route1, route2);
}