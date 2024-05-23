#include <string>
#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;

vector<int> solution(vector<int> answers) {
    vector<int> answer = {0, 0, 0};
    vector<int> first = {1, 2, 3, 4, 5};
    vector<int> second = {2, 1, 2, 3, 2, 4, 2, 5};
    vector<int> third = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
    
    for (int i = 0; i < answers.size(); i++)
    {
        if (answers[i] == first[i%5]) answer[0]++;
        if (answers[i] == second[i%8]) answer[1]++;
        if (answers[i] == third[i%10]) answer[2]++;
    }
    
    int max_cnt = 0;
    
    max_cnt = *max_element(answer.begin(), answer.end());
    vector<int> ans;
    
    for (int i = 0; i < 3; i++) {
        if (max_cnt == answer[i]) {
            ans.push_back(i+1);
        }
    }
    
    return ans;
}