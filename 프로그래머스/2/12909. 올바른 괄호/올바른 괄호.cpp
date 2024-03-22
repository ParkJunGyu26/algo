#include<string>
#include <iostream>
#include <queue>

using namespace std;

bool solution(string s)
{
    bool answer = true;
    
    queue<char> q;
    
    for (auto chr : s) {
        // 시작부터 이상할 경우
        if (chr == ')' && q.empty()) {
            return false;
        }
        
        // 짝을 맞을 때마다, 제거해주기
        if (chr == '(') {
            q.push(chr);
        } else {
            q.pop();
        }
    }
    
    if (q.empty()) {
        answer = true;
    } else {
        answer = false;
    }
    
    return answer;
}