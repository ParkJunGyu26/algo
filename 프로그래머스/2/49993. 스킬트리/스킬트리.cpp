#include <string>
#include <vector>
#include <unordered_map>
#include <iostream>

using namespace std;

unordered_map<char, int> um;

int solution(string skill, vector<string> skill_trees) {
    int answer = 0;
    for (int i = 0; i < 26; i++) um[i+'A'] = 0;
    
    for (int i = 0; i < skill.size(); i++) um[skill[i]] = i+1;
    
    for (int i = 0; i < skill_trees.size(); i++) {
        int index = 1;
        bool check = true;
        for (int j = 0; j < skill_trees[i].size(); j++) {
            if (um[skill_trees[i][j]] == 0) continue;
            
            if (um[skill_trees[i][j]] > index) {
                check = false;
                break;
            }
            
            if (um[skill_trees[i][j]] == index) index++;
        }
        
        if (check) answer++;
    }
    
    // for (auto u : um) cout << u.first << " " << u.second << "\n";
    
    
    
    return answer;
}