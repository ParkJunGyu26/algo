#include <iostream>
#include<string>
#include <vector>

using namespace std;

int solution(string s)
{
    int answer = 0;
    vector<char> stack;
    
    for (int i = 0; i < s.size(); i++) {
        stack.push_back(s[i]);
        while (stack.size() > 1) {
            char first = stack[stack.size()-1];
            char second = stack[stack.size()-2];
            stack.pop_back();
            stack.pop_back();
            if (first != second) {
                stack.push_back(second);
                stack.push_back(first);
                break;
            }
        }
    }
    
    if (stack.size() == 0) answer = 1;

    return answer;
}