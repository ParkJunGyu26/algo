#include <string>
#include <vector>
#include <queue>

using namespace std;

long long sum(vector<int> &vec) {
    int result = 0;
    for (auto v : vec) result += v;
    return result;
}

long long solution(int n, vector<int> works) {
    long long answer = 0;
    if (sum(works) <= n) return answer;
    
    priority_queue<int> pq;
    for (int i = 0; i < works.size(); i++) pq.push(works[i]);
    
    for (int i = 0; i < n; i++) {
        int num = pq.top();
        pq.pop();
        pq.push(num-1);
    }
    
    while(!pq.empty()) {
        answer += (pq.top() * pq.top());
        pq.pop();
    }
    
    return answer;
}