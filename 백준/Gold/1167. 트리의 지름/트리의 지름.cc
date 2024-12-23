#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;

int n;

// BFS 함수: 가장 먼 노드와 거리를 반환
pair<int, int> bfs(int start, const vector<vector<pair<int, int>>> &graph) {
    vector<int> distance(n + 1, -1); // 거리 배열, -1로 초기화
    queue<int> q;

    q.push(start);
    distance[start] = 0; // 시작 노드는 거리 0

    int farthest_node = start;
    int max_distance = 0;

    while (!q.empty()) {
        int current = q.front();
        q.pop();

        for (auto &[next, weight] : graph[current]) {
            if (distance[next] == -1) { // 방문하지 않은 노드만 처리
                distance[next] = distance[current] + weight;
                q.push(next);

                if (distance[next] > max_distance) {
                    max_distance = distance[next];
                    farthest_node = next;
                }
            }
        }
    }

    return {farthest_node, max_distance};
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    cin >> n;
    vector<vector<pair<int, int>>> graph(n + 1);

    for (int i = 0; i < n; i++) {
        int node;
        cin >> node;

        while (true) {
            int adj, weight;
            cin >> adj;
            if (adj == -1) break;
            cin >> weight;

            graph[node].emplace_back(adj, weight);
        }
    }

    // 1차 BFS: 루트에서 가장 먼 노드 찾기
    auto result1 = bfs(1, graph);
    int farthest_node = result1.first;

    // 2차 BFS: 가장 먼 노드에서 출발하여 트리의 지름 계산
    auto result2 = bfs(farthest_node, graph);
    int diameter = result2.second;

    cout << diameter << '\n';

    return 0;
}
