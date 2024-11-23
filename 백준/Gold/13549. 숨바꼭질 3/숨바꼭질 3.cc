#include <iostream>
#include <deque>
#include <climits>

#define MAX_SIZE 100001

using namespace std;

int n, k;
int res[MAX_SIZE];

void bfs(int start) {
    deque<int> dq;
    
    // 모든 위치의 초기 거리를 INT_MAX로 설정
    fill(res, res + MAX_SIZE, INT_MAX);
    
    // 시작점 초기화
    dq.push_front(start);
    res[start] = 0;
    
    while (!dq.empty()) {
        int x = dq.front();
        dq.pop_front();
        
        if (x == k) return;  // 목표 지점 도달
        
        // 순간이동 (가중치 0)
        if (x * 2 < MAX_SIZE && res[x * 2] > res[x]) {
            res[x * 2] = res[x];
            dq.push_front(x * 2);
        }
        
        // 걷기 (가중치 1)
        for (int nx : {x - 1, x + 1}) {
            if (nx >= 0 && nx < MAX_SIZE && res[nx] > res[x] + 1) {
                res[nx] = res[x] + 1;
                dq.push_back(nx);
            }
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    
    cin >> n >> k;
    
    bfs(n);
    cout << res[k];
    
    return 0;
}