#include <string>
#include <vector>
#include <unordered_map>

using namespace std;

int answer_check(unordered_map<string, int> &um, vector<string> &want, vector<int> &number) {
    for (int i = 0; i < want.size(); i++) {
        if(um[want[i]] != number[i]) return 0;
    }
    
    return 1;
}

int solution(vector<string> want, vector<int> number, vector<string> discount) {
    int answer = 0;
    unordered_map<string, int> um;
    for (int i = 0; i < 10; i++) {
        if (um.find(discount[i]) == um.end()) um[discount[i]] = 1;
        else um[discount[i]]++;
    }
    answer += answer_check(um, want, number);
    
    for (int i = 10; i < discount.size(); i++) {
        um[discount[i-10]]--;
        um[discount[i]]++;
        answer += answer_check(um, want, number);
    }
    
    return answer;
}