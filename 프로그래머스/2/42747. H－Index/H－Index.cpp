#include <string>
#include <vector>
#include <algorithm>
#include <set>
#include <iostream>

using namespace std;

bool check_ans(int target, vector<int>& v) {
    int cnt = 0;
    for (int i = 0; i < v.size(); i++)
        if (v[i] >= target) cnt++;
    
    return cnt >= target;
}

// 매개변수 탐색(제일 오른쪽)
int solution(vector<int> citations) {
    int answer;
    
    int left = 0;
    int right = citations.size();
    
    int mid;
    while (left <= right) {
        mid = left + (right - left) / 2;
    
        if (check_ans(mid, citations)) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    
    return right;
}