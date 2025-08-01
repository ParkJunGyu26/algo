#include <iostream>
#include <vector>
using namespace std;

const int MAX = 100001;
int n;
int arr[MAX];
bool visited[MAX];
bool finished[MAX];
int cnt;

void dfs(int now) {
    visited[now] = true;
    int next = arr[now];

    if (!visited[next]) {
        dfs(next);
    } else if (!finished[next]) {
		for (int i = next; i != now; i = arr[i]) {
            cnt++;
        }
        cnt++;
    }

    finished[now] = true;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int T;
    cin >> T;
    while (T--) {
        cin >> n;
        cnt = 0;

        for (int i = 1; i <= n; i++) {
            cin >> arr[i];
            visited[i] = finished[i] = false;
        }

        for (int i = 1; i <= n; i++) {
            if (!visited[i]) dfs(i);
        }

        cout << n - cnt << '\n';
    }
}
