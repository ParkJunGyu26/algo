#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int answer;

void dfs(int k, vector<int>& hubo, vector<vector<int>>& graph) {
    if (hubo.size() == graph.size()) {
        int cnt = 0;
        int remain = k; // 남은 k
        for (int i = 0; i < hubo.size(); i++) {
            if (graph[hubo[i]][0] <= remain) {
                remain -= graph[hubo[i]][1];
                cnt++;
            }
        }
        answer = max(answer, cnt);
        return;
    }
    
    for (int i = 0; i < graph.size(); i++) {
        if (find(hubo.begin(), hubo.end(), i) == hubo.end()) {
            hubo.push_back(i);
            dfs(k, hubo, graph);
            hubo.pop_back();
        }
    }
}

int solution(int k, vector<vector<int>> dungeons) {
    vector<int> hubo; // 인덱스 저장
    dfs(k, hubo, dungeons);
    
    return answer;
}