#include <string>
#include <vector>
#include <iostream>

using namespace std;

vector<vector<int>> solution(vector<vector<int>> arr1, vector<vector<int>> arr2) {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    
    vector<vector<int>> answer;
    
    for (int i = 0; i < arr1.size(); i++) { // 왼쪽 행렬의 행 개수만큼
        vector<int> row;
        for (int j = 0; j < arr2[0].size(); j++) { // 왼쪽 행렬의 열 개수 == 오른쪽 행렬의 행 개수
            int sum = 0;
            for (int k = 0; k < arr2.size(); k++) { // 오른쪽 행렬의 행 개수
                sum += arr1[i][k] * arr2[k][j];
            }
            row.push_back(sum);
        }
        answer.push_back(row);
    }
    
    return answer;
}