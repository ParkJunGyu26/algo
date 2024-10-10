#include <string>
#include <vector>
#include <algorithm>
#include <iostream>
#include <set>
#include <unordered_map>

using namespace std;

bool cmp(pair<int, int> a, pair<int, int> b) {
	return a.second < b.second;
}

int solution(int k, vector<int> tangerine) {
    int answer = 0;
    
    unordered_map<int, int> um; // 개수 카운팅
    set<int> s; // 중복없는 숫자 집합
    vector<pair<int, int>> nums; // (숫자, 개수) 쌍
    
    for (auto t : tangerine) {
        if (um.find(t) == um.end()) um[t] = 1;
        else um[t]++;
    }
    
    for (auto t : tangerine)
        s.insert(t);
    
    for (auto ss : s)
        nums.push_back(make_pair(ss, um[ss]));
    
    sort(nums.begin(), nums.end(), cmp);
    
    int cnt;
    while(k > 0) {
        answer++;
        cnt = nums.back().second;
        nums.pop_back();
        k -= cnt;
    }
    
    return answer;
}