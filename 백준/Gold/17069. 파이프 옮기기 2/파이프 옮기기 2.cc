#include <iostream>
#include <vector>

using namespace std;

int n;
vector<vector<int>> house;
long long dp[32][32][3];

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    cin >> n;
    house.assign(n, vector<int>(n));
    for (int y = 0; y < n; y++)
        for (int x = 0; x < n; x++)
            cin >> house[y][x];

    dp[0][1][0] = 1;
    
    for (int y = 0; y < n; y++) {
        for (int x = 2; x < n; x++) {
            if (house[y][x]) continue;

            dp[y][x][0] += dp[y][x - 1][0];

            dp[y][x][0] += dp[y][x - 1][1];

            if (y > 0)
                dp[y][x][2] += dp[y - 1][x][2];

            if (y > 0)
                dp[y][x][2] += dp[y - 1][x][1];

            if (y > 0 && !house[y - 1][x] && !house[y][x - 1]) {
                dp[y][x][1] += dp[y - 1][x - 1][0];
                dp[y][x][1] += dp[y - 1][x - 1][1];
                dp[y][x][1] += dp[y - 1][x - 1][2];
            }
        }
    }

    long long result = dp[n - 1][n - 1][0] + dp[n - 1][n - 1][1] + dp[n - 1][n - 1][2];
    cout << result << '\n';
    return 0;
}
