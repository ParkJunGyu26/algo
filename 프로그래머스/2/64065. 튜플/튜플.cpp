#include <string>
#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;

bool cmp(vector<int> &a, vector<int> &b) {
    return a.size() < b.size();
}

vector<int> solution(string s) {
    vector<int> answer;
    vector<vector<int>> hubo;
    vector<int> temp;
    bool check = false;
    string num = "";
    
    for (int i = 0; i < s.size(); i++) {
        if (s[i] == '{') {
            check = true;
            continue;
        }
        
        if (!check) continue;
        
        if (s[i] == '}') {
            check = false;
        
            temp.push_back(stoi(num));
            num = "";
            
            if (temp.size() == 0) continue;
            hubo.push_back(temp);
            temp.clear();
        }
        
        if (s[i] == ',') {
            temp.push_back(stoi(num));
            num = "";
        }
        
        if ('0' <= s[i] && s[i] <= '9') {
            num += s[i];
        }
    }
    
    sort(hubo.begin(), hubo.end(), cmp);
    
    vector<int> visited(100001);
    for (int i = 0; i < hubo.size(); i++) {
        for (int j = 0; j < hubo[i].size(); j++) {
            if (visited[hubo[i][j]] == 1) continue;
            
            answer.push_back(hubo[i][j]);
            visited[hubo[i][j]] = 1;
        }
    }
    
    // for (int i = 0; i < hubo.size(); i++) {
    //     for (int j = 0; j < hubo[i].size(); j++) cout << hubo[i][j] << " ";
    //     cout << "\n";
    // }
    
    return answer;
}