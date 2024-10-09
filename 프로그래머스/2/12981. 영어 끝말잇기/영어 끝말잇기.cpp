#include <string>
#include <vector>
#include <iostream>
#include <unordered_map>

using namespace std;

vector<int> solution(int n, vector<string> words) {
    vector<int> answer;
    unordered_map<string, int> um;
    
    int cnt = 0;
    int index = 0;
    bool check = true;
    
    for (int i = 0; i < words.size(); i++) {
        if (i > 0) {
            if (words[i-1].back() != words[i].front()) check = false;
        }
        
        if (um.find(words[i]) == um.end()) {
            um.insert(make_pair(words[i], 1));
        } else {
            check = false;
        }
        
        if (!check) {
            cnt = i / n;
            index = i % n;
            cnt++;
            index++;
            break;
        }
    }
    
    answer.push_back(index);
    answer.push_back(cnt);

    return answer;
}