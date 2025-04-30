#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int n, q;
vector<pair<long, long>> menu;

bool cmp(pair<long, long> a, pair<long, long> b) {
	if (a.first == b.first) return a.second < b.second;
	return a.first < b.first;
}

// 10^5 * 5 * 10^3
// 그리디로 최적화 -> 앞에 값들을 정렬(오름차순)해주고, 하나라도 만족안하면  탐색 안하는?
int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n >> q;
	menu.resize(n);
	for (int i = 0; i < n; i++) cin >> menu[i].first >> menu[i].second;
	sort(menu.begin(), menu.end(), cmp);

	for (int i = 0; i < q; i++) {
		int answer = 0;
		long u, v, x, y;
		cin >> u >> v >> x >> y;

		for (int j = 0; j < n; j++) {
			if (u <= menu[j].first && menu[j].first <= v && x <= menu[j].second && menu[j].second <= y) answer++;
		}

		cout << answer << "\n";
	}
}