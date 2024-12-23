#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int n;
vector<vector<pair<int, int>>> graph;
vector<bool> visited;
int diameter = 0;

// DFS 함수: 각 노드에서 최대 거리 반환
int dfs(int node, int parent) {
    int max1 = 0, max2 = 0;

    for (auto &[next, weight] : graph[node]) {
        if (next == parent) continue;

        int dist = dfs(next, node) + weight;

        if (dist > max1) {
            max2 = max1;
            max1 = dist;
        } else if (dist > max2) {
            max2 = dist;
        }
    }

    diameter = max(diameter, max1 + max2); // 두 경로의 합으로 지름 갱신
    return max1;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    cin >> n;
    graph.resize(n + 1);

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

    visited.assign(n + 1, false);
    dfs(1, -1); // 루트 노드에서 시작
    cout << diameter << '\n';

    return 0;
}
