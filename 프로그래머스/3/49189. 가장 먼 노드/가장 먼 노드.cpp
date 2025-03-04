#include <string>
#include <vector>
#include <queue>
#include <iostream>
#include <algorithm>

using namespace std;

void bfs(int n, vector<vector<int>> &graph, vector<int> &visited) {
    queue<pair<int, int>> q; // {node, res}
    q.push({1, 0});
    visited[1] = -1;
    
    while (!q.empty()) {
        int node = q.front().first;
        int res = q.front().second;
        q.pop();
        
        for (int i = 0; i < graph[node].size(); i++) {
            if (visited[graph[node][i]] != 0) continue;
            
            visited[graph[node][i]] = res+1;
            q.push({graph[node][i], res+1});
        }
    }
}

int solution(int n, vector<vector<int>> edge) {
    int answer = 0;
    vector<vector<int>> graph(n+1);
    vector<int> visited(n+1);
    int L = edge.size();
    
    for (int i = 0; i < L; i++) {
        graph[edge[i][0]].push_back(edge[i][1]);
        graph[edge[i][1]].push_back(edge[i][0]);
    }
    
    // for (int i = 1; i <= n; i++) {
    //     for (int j = 0; j < graph[i].size(); j++) cout << graph[i][j] << " ";
    //     cout << "\n";
    // }
    
    bfs(n, graph, visited);
    int target = *max_element(visited.begin(), visited.end());
    for (auto v : visited) if (target == v) answer++;
    return answer;
}