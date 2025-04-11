#include <string>
#include <vector>
#include <queue>

using namespace std;

int answer = 0;

void bfs(vector<int> &vec, int target) {
    queue<pair<int, int>> q;
    q.push({vec[0], 0});
    q.push({-vec[0], 0});
    
    while (!q.empty()) {
        int total = q.front().first;
        int index = q.front().second;
        q.pop();
        
        if (index == vec.size()-1) {
            if (total == target) answer++;
            continue;
        }
        
        q.push({total + vec[index+1], index+1});
        q.push({total - vec[index+1], index+1});
    }
}

// 2^N
int solution(vector<int> numbers, int target) {
    
    bfs(numbers, target);
    
    return answer;
}