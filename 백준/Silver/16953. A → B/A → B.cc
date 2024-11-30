#include <iostream>
#include <queue>
#include <unordered_map>

using namespace std;

long long a, b;

int bfs() {
    queue<pair<long long, int>> q;
    unordered_map<long long, int> visited;
    
    q.push({a, 1});
    visited[a] = 1;

    while(!q.empty()) {
        long long x = q.front().first;
        int cnt = q.front().second;
        q.pop();

        if (x == b) return cnt;

        // 2를 곱하는 경우
        long long nx1 = x * 2;
        if (nx1 <= b && visited.count(nx1) == 0) {
            visited[nx1] = 1;
            q.push({nx1, cnt + 1});
        }

        // 1을 추가하는 경우
        long long nx2 = x * 10 + 1;
        if (nx2 <= b && visited.count(nx2) == 0) {
            visited[nx2] = 1;
            q.push({nx2, cnt + 1});
        }
    }

    return -1;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> a >> b;
    cout << bfs();

    return 0;
}