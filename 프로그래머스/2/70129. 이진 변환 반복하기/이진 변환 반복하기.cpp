#include <string>
#include <vector>
#include <iostream>

using namespace std;

int zeroCnt = 0;

string changeBin(string& s) {
    int binNum = 0;
    for (int i = 0; i < s.size(); i++) {
        if (s[i] == '1') binNum++;
        else zeroCnt++;
    }
    
    string answer = "";
    while (binNum) {
        answer = to_string(binNum % 2) + answer;
        binNum /= 2;
    }
    
    return answer;
}

vector<int> solution(string s) {
    int cnt = 0;
    
    while (s.size() != 1) {
        cnt++;
        s = changeBin(s);
    }
    
    vector<int> answer;
    answer.push_back(cnt);
    answer.push_back(zeroCnt);
    return answer;
}