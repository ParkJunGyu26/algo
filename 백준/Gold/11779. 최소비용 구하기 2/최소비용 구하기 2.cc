#include <iostream>
#include <vector>
#include <queue>
#include <climits>
#include <algorithm>

using namespace std;

void dijkstra(int start, int end, vector<vector<pair<int, int>>>& graph) {
    int n = graph.size() - 1;
    vector<int> dist(n + 1, INT_MAX);
    vector<int> parent(n + 1, 0);
    
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
    
    dist[start] = 0;
    pq.push({0, start});

    while (!pq.empty()) {
        int now_dist = pq.top().first;
        int now_node = pq.top().second;
        pq.pop();

        // 현재 거리가 이미 기록된 거리보다 크면 스킵
        if (now_dist > dist[now_node]) continue;

        for (auto& edge : graph[now_node]) {
            int next_node = edge.first;
            int next_dist = edge.second;
            
            if (dist[next_node] > dist[now_node] + next_dist) {
                dist[next_node] = dist[now_node] + next_dist;
                parent[next_node] = now_node;
                pq.push({dist[next_node], next_node});
            }
        }
    }

    // 경로 추적 및 출력
    vector<int> path;
    for(int at = end; at != 0; at = parent[at]) {
        path.push_back(at);
    }
    reverse(path.begin(), path.end());
    
    cout << dist[end] << "\n";  // 최소 비용
    cout << path.size() << "\n";  // 도시 개수
    for(int city : path) cout << city << " ";  // 경로
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int n, m, start, end;
    cin >> n >> m;
    
    vector<vector<pair<int, int>>> graph(n + 1);
    
    for (int i = 0; i < m; i++) {
        int a, b, c;
        cin >> a >> b >> c;
        graph[a].push_back({b, c});
    }

    cin >> start >> end;
    dijkstra(start, end, graph);

    return 0;
}