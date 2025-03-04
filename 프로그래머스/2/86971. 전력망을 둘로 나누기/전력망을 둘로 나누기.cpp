#include <string>
#include <vector>
#include <algorithm>
#include <climits>
#include <iostream>

using namespace std;

void dfs(vector<int> &v, vector<vector<int>> &g, vector<int> &hubo, int node) {
    for (int i = 0; i < g[node].size(); i++) {
        if (v[g[node][i]] == 0) {
            v[g[node][i]] = 1;
            hubo.push_back(g[node][i]);
            dfs(v, g, hubo, g[node][i]);
        }
    }
}

int solve(int index, int n, vector<vector<int>> &vec) {
    vector<vector<int>> graph(n+1);
    vector<int> visited(n+1);
    
    for (int i = 0; i < vec.size(); i++) {
        if (i == index) continue;
        
        graph[vec[i][0]].push_back(vec[i][1]);
        graph[vec[i][1]].push_back(vec[i][0]);
    }
    
    // for (int i = 1; i <= n; i++) {
    //     for (int j = 0; j < graph[i].size(); j++) cout << graph[i][j] << " ";
    //     cout << "\n";
    // }
    
    vector<int> count;
    for (int i = 1; i <= n; i++) {
        if (visited[i] == 0) {
            visited[i] = 1;
            vector<int> hubo;
            hubo.push_back(i);
            
            dfs(visited, graph, hubo, i);
            
            count.push_back(hubo.size());
        }
    }
    
    // for (int i = 0; i < count.size(); i++) {
    //     cout << "값 : " << count[i] << "\n";
    // }
    
    if (count.size() == 1) return count[0];
    
    return abs(count[1] - count[0]);
}

int solution(int n, vector<vector<int>> wires) {
    int answer = INT_MAX;
    for (int i = 0; i < n; i++) {
        answer = min(answer, solve(i, n, wires));
        // cout << "----\n";
    }
    return answer;
}