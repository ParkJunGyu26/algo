#include <string>
#include <vector>
#include <iostream>

using namespace std;

string rotate(string s) {
    string answer = "";
    
    for (int i = 1; i < s.size(); i++)
        answer += s[i];
    answer += s[0];
    
    return answer;
}

int checkStack(string s) {
    string target = "";
    
    for (int i = 0; i < s.size(); i++) {
        if (s[i] == '[' || s[i] == '{' || s[i] == '(') {
            target += s[i];
            continue;
        }
        if (s[i] == ']' && target.find('[') == string::npos) return 0;
        
        if (s[i] == '}' && target.find('{') == string::npos) return 0;
        
        if (s[i] == ')' && target.find('(') == string::npos) return 0;
        
        target.pop_back();
    }
    if (!target.empty()) return 0;
    return 1;
}

int solution(string s) {
    int answer = 0;
    
    int len = s.size();
    answer += checkStack(s);
    len--;
    while (len > 0) {
        s = rotate(s);
        answer += checkStack(s);
        len--;
    }
    
    return answer;
}