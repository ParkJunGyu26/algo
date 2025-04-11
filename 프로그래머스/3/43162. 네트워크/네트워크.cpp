#include <string>
#include <vector>
#include <queue>
#include <iostream>

using namespace std;

int bfs(int node, vector<int> &v, vector<vector<int>> &graph) {
    queue<int> q;
    q.push(node);
    
    while (!q.empty()) {
        int now_node = q.front();
        q.pop();
        
        for (int i = 0; i < graph[now_node].size(); i++) {
            if (v[graph[now_node][i]] == 1) continue;
        
            v[graph[now_node][i]] = 1;
            q.push(graph[now_node][i]);
        }
    }
    
    return 1;
}

int solution(int n, vector<vector<int>> computers) {
    int answer = 0;
    vector<int> v(n);
    vector<vector<int>> graph(n);
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (i == j || computers[i][j] == 0) continue;
            
            graph[i].push_back(j);
        }
    }
    
    for (int i = 0; i < n; i++) {
        if (v[i] == 1) continue;
        
        v[i] = 1;
        answer += bfs(i, v, graph);
    }
    
    return answer;
}