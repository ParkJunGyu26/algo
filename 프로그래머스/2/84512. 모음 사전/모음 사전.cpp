#include <string>
#include <vector>
#include <iostream>

char info[5] = {'A', 'E', 'I', 'O', 'U'};

using namespace std;

void dfs(string target, vector<string>& hubo) {
    if (target.size() == 5) return;
    
    for (int i = 0; i < 5; i++) {
        target += info[i];
        hubo.push_back(target);
        
        dfs(target, hubo);
        
        target.pop_back();
    }
}

int solution(string word) {
    int answer = 1;
    
    string target = "";
    vector<string> hubo;
    dfs(target, hubo);
    
    for (auto h : hubo) {
        if (h == word) return answer;
        answer++;
    }
    
    return answer;
}