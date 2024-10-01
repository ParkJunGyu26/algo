#include <string>
#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

string solution(string s) {
    vector<int> hubo;
    string num = "";
    for (int i = 0; i < s.size(); i++) {
        if (s[i] == '-') {
            if (num.size() > 0) hubo.push_back(stoi(num));
            num = "";
            num += s[i];
            continue;
        }
        
        if (0 <= s[i]-'0' && s[i]-'0' <= 9) num += s[i];
        else {
            if (num.size() > 0) {
                hubo.push_back(stoi(num));
                num = "";
            }
        }
        
        if (i == s.size()-1 && num.size() > 0) hubo.push_back(stoi(num));
    }
    
    sort(hubo.begin(), hubo.end());
    
    string answer = "";
    
    answer += to_string(hubo[0]) + " " + to_string(hubo[hubo.size()-1]);
    
    return answer;
}