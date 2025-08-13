#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n;
    cin >> n;
    vector<string> graph(n);
    for (int i = 0; i < n; i++) cin >> graph[i];

    int total = 0;
    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
            if (graph[i][j] == '1') total++;

    vector<vector<pair<int,int>>> dp(n, vector<pair<int,int>>(n));
    dp[0][0] = (graph[0][0] == '1') ? make_pair(1,1) : make_pair(1,0);

    // 첫 행
    for (int j = 1; j < n; j++) {
        dp[0][j].first = dp[0][j-1].first + 1;
        dp[0][j].second = dp[0][j-1].second + ((graph[0][j] == '1') ? 1 : 0);
    }

    int rowTotal = 0;

    for (int i = 1; i < n-1; i++) {
        vector<pair<int,int>> leftToRight(n), rightToLeft(n);

        leftToRight[0] = make_pair(dp[i-1][0].first + 1, dp[i-1][0].second + ((graph[i][0] == '1') ? 1 : 0));
        for (int j = 1; j < n; j++) {
            int cnt = (graph[i][j] == '1') ? 1 : 0;
            rowTotal += cnt;

            if (leftToRight[j-1].second < dp[i-1][j].second) {
                leftToRight[j] = make_pair(dp[i-1][j].first + 1, dp[i-1][j].second + cnt);
            } else if (leftToRight[j-1].second > dp[i-1][j].second) {
                leftToRight[j] = make_pair(leftToRight[j-1].first + 1, leftToRight[j-1].second + cnt);
            } else {
                if (leftToRight[j-1].first > dp[i-1][j].first)
                    leftToRight[j] = make_pair(dp[i-1][j].first + 1, dp[i-1][j].second + cnt);
                else
                    leftToRight[j] = make_pair(leftToRight[j-1].first + 1, leftToRight[j-1].second + cnt);
            }
        }

        rightToLeft[n-1] = make_pair(dp[i-1][n-1].first + 1, dp[i-1][n-1].second + ((graph[i][n-1] == '1') ? 1 : 0));
        for (int j = n-2; j >= 0; j--) {
            int cnt = (graph[i][j] == '1') ? 1 : 0;

            if (rightToLeft[j+1].second < dp[i-1][j].second) {
                rightToLeft[j] = make_pair(dp[i-1][j].first + 1, dp[i-1][j].second + cnt);
            } else if (rightToLeft[j+1].second > dp[i-1][j].second) {
                rightToLeft[j] = make_pair(rightToLeft[j+1].first + 1, rightToLeft[j+1].second + cnt);
            } else {
                if (rightToLeft[j+1].first > dp[i-1][j].first)
                    rightToLeft[j] = make_pair(dp[i-1][j].first + 1, dp[i-1][j].second + cnt);
                else
                    rightToLeft[j] = make_pair(rightToLeft[j+1].first + 1, rightToLeft[j+1].second + cnt);
            }
        }

        // 선택
        for (int j = 0; j < n; j++) {
            if (leftToRight[j].second > rightToLeft[j].second ||
                (leftToRight[j].second == rightToLeft[j].second && leftToRight[j].first <= rightToLeft[j].first)) {
                dp[i][j] = leftToRight[j];
            } else {
                dp[i][j] = rightToLeft[j];
            }
        }
    }

    // 마지막 행
    dp[n-1][0] = make_pair(dp[n-2][0].first + 1, dp[n-2][0].second + (graph[n-1][0] == '1' ? 1 : 0));
    for (int j = 1; j < n; j++) {
        int cnt = (graph[n-1][j] == '1') ? 1 : 0;

        if (dp[n-1][j-1].second < dp[n-2][j].second)
            dp[n-1][j] = make_pair(dp[n-2][j].first + 1, dp[n-2][j].second + cnt);
        else if (dp[n-1][j-1].second > dp[n-2][j].second)
            dp[n-1][j] = make_pair(dp[n-1][j-1].first + 1, dp[n-1][j-1].second + cnt);
        else {
            if (dp[n-1][j-1].first < dp[n-2][j].first)
                dp[n-1][j] = make_pair(dp[n-1][j-1].first + 1, dp[n-1][j-1].second + cnt);
            else
                dp[n-1][j] = make_pair(dp[n-2][j].first + 1, dp[n-2][j].second + cnt);
        }
    }

    cout << (dp[n-1][n-1].second == total ? dp[n-1][n-1].first : -1) << "\n";

    return 0;
}
