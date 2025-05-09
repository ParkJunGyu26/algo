#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int answer = 0;
int n;
vector<pair<int, int>> vec;

// status = 0 : 선택
void dfs(int number, int status, int res, int life) {
	answer = max(res, answer);
	if (number == n) return;

	if (life - vec[number].first > 0) dfs(number+1, 0, res+vec[number].second, life-vec[number].first);
	dfs(number+1, 1, res, life);
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n;
	vec.resize(n);
	for (int i = 0; i < n; i++) cin >> vec[i].first;
	for (int i = 0; i < n; i++) cin >> vec[i].second;

	if (vec[0].first != 100) dfs(1, 0, vec[0].second, 100 - vec[0].first);
	dfs(1, 1, 0, 100);

	cout << answer;
}