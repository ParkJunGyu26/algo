#include <iostream>
#include <vector>
#include <string>
#include <unordered_map>
#include <algorithm>
#include <queue>
#include <climits>

using namespace std;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	int n, h, w;
	cin >> n;
	vector<pair<int, int>> v;
	for (int i = 0; i < n; i++) {
		cin >> w >> h;
		v.push_back({w, h});
	}

	vector<int> answer;
	for (int i = 0; i < n; i++) {
		int cnt = 1;
		for (int j = 0; j < n; j++) {
			if (i == j) continue;

			if (v[i].first < v[j].first) {
				if (v[i].second < v[j].second) cnt++;
			}
		}
		answer.push_back(cnt);
	}

	for (auto a : answer)
		cout << a << " ";
}