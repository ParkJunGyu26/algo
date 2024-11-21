#include <iostream>
#include <vector>
using namespace std;

int n, answer = 0;
vector<pair<int, int>> eggs; // 내구도, 무게

void dfs(int current) {
    // 마지막 계란까지 확인했으면 깨진 계란 수 계산
    if (current == n) {
        int broken = 0;
        for (auto& egg : eggs) {
            if (egg.first <= 0) broken++;
        }
        answer = max(answer, broken);
        return;
    }
    
    // 현재 계란이 이미 깨졌다면 다음 계란으로 
    if (eggs[current].first <= 0) {
        dfs(current + 1);
        return;
    }
    
    bool hit_any = false;
    for (int i = 0; i < n; i++) {
        // 같은 계란이거나 이미 깨진 계란은 패스
        if (i == current || eggs[i].first <= 0) continue;
        
        hit_any = true;
        
        // 계란 충돌 시뮬레이션
        eggs[current].first -= eggs[i].second;
        eggs[i].first -= eggs[current].second;
        
        dfs(current + 1);
        
        // 상태 복구
        eggs[current].first += eggs[i].second;
        eggs[i].first += eggs[current].second;
    }
    
    // 다른 계란을 치지 못했다면 다음 계란으로
    if (!hit_any) {
        dfs(current + 1);
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    cin >> n;
    for (int i = 0; i < n; i++) {
        int s, w;
        cin >> s >> w;
        eggs.push_back({s, w});
    }
    
    dfs(0);
    cout << answer;
    
    return 0;
}