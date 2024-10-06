#include <string>
#include <vector>
#include <sstream>
#include <iostream>

using namespace std;

string solution(string s) {
    for (int i = 0; i < s.size(); i++)
        if (isupper(s[i])) s[i] = tolower(s[i]);
    
    // 첫 글자는 대문자로
    for (int i = 0; i < s.size(); i++) {
        if (i == 0) s[i] = toupper(s[i]);
        else if (s[i] == ' ') s[i+1] = toupper(s[i+1]);
        
    }
    
    string answer = "";
    for (int i = 0; i < s.size(); i++)
        answer += s[i];
    
    return answer;
}