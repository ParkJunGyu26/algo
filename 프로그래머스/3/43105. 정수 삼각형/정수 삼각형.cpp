#include <string>
#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;

int solution(vector<vector<int>> triangle) {
    int answer = 0;
    vector<int> dp;
    dp.push_back(triangle[0][0]);
        
    for (int i = 1; i < triangle.size(); i++) {
        vector<int> tmp;
        for (int j = 0; j < triangle[i].size(); j++) {
            if (j == 0) tmp.push_back(dp[j] + triangle[i][j]);
            else if (j == triangle[i].size()-1) tmp.push_back(dp[j-1] + triangle[i][j]);
            else {
                tmp.push_back(max(triangle[i][j] + dp[j-1], triangle[i][j] + dp[j]));
            }
        }
        dp = tmp;
    }
    answer = *max_element(dp.begin(), dp.end());
    
    return answer;
}