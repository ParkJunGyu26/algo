#include <iostream>
#include <vector>

using namespace std;

int n, m;
int answer[2001][2001];

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> n;
    vector<int> vec(n);
    for (int i = 0; i < n; i++) {
        cin >> vec[i];
        answer[i][i] = 1; // 길이가 1인 구간은 항상 팰린드롬
    }

    // 길이가 2인 구간 처리
    for (int i = 0; i < n - 1; i++) {
        if (vec[i] == vec[i + 1]) {
            answer[i][i + 1] = 1;
        }
    }

    // 길이가 3 이상인 구간 처리
    for (int length = 3; length <= n; length++) { // 구간 길이
        for (int i = 0; i <= n - length; i++) {   // 구간 시작점
            int j = i + length - 1;               // 구간 끝점
            if (vec[i] == vec[j] && answer[i + 1][j - 1] == 1) {
                answer[i][j] = 1;
            }
        }
    }

    cin >> m;
    for (int i = 0; i < m; i++) {
        int left, right;
        cin >> left >> right;
        cout << answer[left - 1][right - 1] << "\n";
    }
    return 0;
}
