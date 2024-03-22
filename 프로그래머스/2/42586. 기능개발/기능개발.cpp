#include <string>
#include <vector>
#include <queue>

using namespace std;

vector<int> solution(vector<int> progresses, vector<int> speeds) {
    vector<int> answer;
    queue<int> q;
    
    int size = progresses.size();
    
    // q에 인덱스 번호를 기입
    for(int i = 0; i < size; i++) {
        q.push(i);
    }
    
    // 조건을 만족하면, 제거해주기
    while(!q.empty()) {
        int cnt = 0;
        // 100이 될 때까지 계속 더하기
        for (int i = 0; i < size; i++) {
            progresses[i] += speeds[i];
        }
        
        // q가 존재하고, 100 이상이 된다면, 제거 및 cnt 추가
        while(!q.empty() && progresses[q.front()] >= 100) {
            q.pop();
            cnt++;
        }
        
        if(cnt != 0) {
            answer.push_back(cnt);
        }
    }
    
    return answer;
}