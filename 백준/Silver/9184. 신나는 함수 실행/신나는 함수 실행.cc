#include <iostream>
#include <string>

using namespace std;

string prefix;
int dp[21][21][21];

int dfs(int a, int b, int c) {
	if (a <= 0 || b <= 0 || c <= 0) return 1;

	if (a > 20 || b > 20 || c > 20) return dfs(20, 20, 20);

	if (dp[a][b][c] != 0) return dp[a][b][c];

	if (a < b && b < c) return dp[a][b][c] = (dfs(a, b, c-1) + dfs(a, b-1, c-1) - dfs(a, b-1, c));

	return dp[a][b][c] = (dfs(a-1, b, c) + dfs(a-1, b-1, c) + dfs(a-1, b, c-1) - dfs(a-1, b-1, c-1));
}

int main() {
	ios_base::sync_with_stdio(false);cin.tie(NULL);cout.tie(NULL);

	while (true) {
		int a, b, c;
		cin >> a >> b >> c;
		if (a == -1 && b == -1 && c == -1) break;

		prefix = "w(" + to_string(a) + ", " + to_string(b) + ", " + to_string(c) + ") = ";

		cout << prefix << dfs(a, b, c) << "\n";
	}
}