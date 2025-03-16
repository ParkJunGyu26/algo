#include <string>
#include <vector>
#include <unordered_map>
#include <queue>
#include <iostream>
#include <algorithm>

using namespace std;

vector<int> solution(vector<string> operations) {
    vector<int> answer;
    vector<int> hubo;
    unordered_map<int, int> visited;
    priority_queue<int> MIN;
    priority_queue<int> MAX;
    
    for (int i = 0; i < operations.size(); i++) {
        string tmp = "";
        for (int j = 0; j < operations[i].size(); j++) {
            if (operations[i][j] == 'D') break;
            
            if ('0' <= operations[i][j] && operations[i][j] <= '9') tmp += operations[i][j];
        }
        
        if (operations[i][0] == 'D') {
            if (operations[i][2] == '-') {
                while(!MIN.empty()) {
                    int target = -MIN.top();
                    MIN.pop();
                    if (visited[target] < 1) continue;
                    
                    visited[target]--;
                    break;
                }
            } else {
                while(!MAX.empty()) {
                    int target = MAX.top();
                    MAX.pop();
                    if (visited[target] < 1) continue;
                    
                    visited[target]--;
                    break;
                }
            }
        } else {
            int temp = stoi(tmp);
            if (operations[i][2] == '-') temp *= -1;
            
            if (visited.find(temp) == visited.end()) visited[temp] = 1;
            else visited[temp]++;
            
            MIN.push(-temp);
            MAX.push(temp);
        }
    }
    
    for (auto v : visited) {
        if (v.second < 1) continue;
        hubo.push_back(v.first);
    }
    
    if (hubo.size() == 0) return {0, 0};
    
    sort(hubo.begin(), hubo.end());
    answer.push_back(hubo.back());
    answer.push_back(hubo.front());
    
    return answer;
}