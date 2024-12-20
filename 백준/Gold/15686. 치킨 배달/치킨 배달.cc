#include <iostream>
#include <vector>
#include <climits>
#include <queue>
#include <cmath>
#include <algorithm>

using namespace std;

int n, m;
int graph[50][50] = {0};
int answer = INT_MAX;

int Min(pair<int, int> dot, vector<pair<int, int>> &hubo) {
	int count = INT_MAX;
	for (auto h : hubo) {
		count = min(count, abs(dot.first - h.first) + abs(dot.second - h.second));
	}

	return count;
}

void dfs(int now, vector<pair<int, int>> &hubo, vector<pair<int, int>> &chicken, vector<pair<int, int>> &house) {
	if (hubo.size() == m) {
		int cnt = 0;
		for (int k = 0; k < house.size(); k++) {
			cnt += Min(house[k], hubo);
		}
		answer = min(cnt, answer);
		return;
	}

	for (int i = now; i < chicken.size(); i++) {
		hubo.push_back(chicken[i]);
		dfs(i+1, hubo, chicken, house);
		hubo.pop_back();
	}
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n >> m;
	for (int i = 0; i < n; i++)
		for (int j = 0; j < n; j++) cin >> graph[i][j];
	
	vector<pair<int, int>> chicken;
	vector<pair<int, int>> house;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (graph[i][j] == 2) chicken.push_back({j, i});
			if (graph[i][j] == 1) house.push_back({j, i});
		}
	}

	vector<pair<int, int>> hubo;
	dfs(0, hubo, chicken, house);
	
	cout << answer;
}